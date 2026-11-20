
package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import main.Bank;
import main.BankAccount;
import main.Customer;

public class BankTest {

    @Test
    public void testCloseEmptyAccount() {
        Bank bank = new Bank();
        Customer customer = new Customer("John Doe", "jdoe1001", "password123");
        BankAccount account = new BankAccount();

        bank.registerExistingAccount("1001", account);
        customer.addAccountNumber("1001");

        assertTrue(bank.closeAccount(customer, "1001"));
        assertFalse(customer.ownsAccount("1001"));
    }

    @Test
    public void testCannotCloseAccountWithMoney() {
        Bank bank = new Bank();
        Customer customer = new Customer("John Doe", "jdoe1001", "password123");
        BankAccount account = new BankAccount();

        account.deposit(50);
        bank.registerExistingAccount("1001", account);
        customer.addAccountNumber("1001");

        assertFalse(bank.closeAccount(customer, "1001"));
    }

    @Test
    public void testTransferMoney() {
        Bank bank = new Bank();
        Customer customer = new Customer("John Doe", "jdoe1001", "password123");
        BankAccount sender = new BankAccount();
        BankAccount receiver = new BankAccount();

        bank.registerExistingAccount("1001", sender);
        bank.registerExistingAccount("1002", receiver);
        customer.addAccountNumber("1001");
        customer.addAccountNumber("1002");
        sender.deposit(100);

        assertTrue(bank.transferMoney(customer, "1001", "1002", 50));
        assertEquals(50, sender.getBalance(), 0.01);
        assertEquals(50, receiver.getBalance(), 0.01);
    }

    @Test
    public void testTransferFailsWithInsufficientFunds() {
        Bank bank = new Bank();
        Customer customer = new Customer("John Doe", "jdoe1001", "password123");
        BankAccount sender = new BankAccount();
        BankAccount receiver = new BankAccount();

        bank.registerExistingAccount("1001", sender);
        bank.registerExistingAccount("1002", receiver);
        customer.addAccountNumber("1001");
        customer.addAccountNumber("1002");
        sender.deposit(100);

        assertFalse(bank.transferMoney(customer, "1001", "1002", 150));
        assertEquals(100, sender.getBalance(), 0.01);
        assertEquals(0, receiver.getBalance(), 0.01);
    }

    @Test
    public void testTransferFailsWithNegativeAmount() {
        Bank bank = new Bank();
        Customer customer = new Customer("John Doe", "jdoe1001", "password123");
        BankAccount sender = new BankAccount();
        BankAccount receiver = new BankAccount();

        bank.registerExistingAccount("1001", sender);
        bank.registerExistingAccount("1002", receiver);
        customer.addAccountNumber("1001");
        customer.addAccountNumber("1002");
        sender.deposit(100);

        assertFalse(bank.transferMoney(customer, "1001", "1002", -50));
    }

    @Test
    public void testTransferFailsWithNonexistentReceiver() {
        Bank bank = new Bank();
        Customer customer = new Customer("John Doe", "jdoe1001", "password123");
        BankAccount sender = new BankAccount();

        bank.registerExistingAccount("1001", sender);
        customer.addAccountNumber("1001");
        sender.deposit(100);

        assertFalse(bank.transferMoney(customer, "1001", "9999", 50));
    }

    @Test
    public void testTransferFailsWithSameAccount() {
        Bank bank = new Bank();
        Customer customer = new Customer("John Doe", "jdoe1001", "password123");
        BankAccount sender = new BankAccount();

        bank.registerExistingAccount("1001", sender);
        customer.addAccountNumber("1001");
        sender.deposit(100);

        assertFalse(bank.transferMoney(customer, "1001", "1001", 50));
    }

    @Test
    public void testCreateAdditionalAccount() {
        Bank bank = new Bank();
        Customer customer = new Customer("John Doe", "jdoe1001", "password123");

        String accountNumber = bank.createAdditionalAccount(customer);
        BankAccount account = bank.getAccountForCustomer(customer, accountNumber);

        assertNotNull(account);
        assertEquals(1, customer.getNumberOfAccounts());
        assertEquals(0, account.getBalance(), 0.01);
    }

    @Test
    public void testAdditionalAccountsHaveUniqueNumbers() {
        Bank bank = new Bank();
        Customer customer = new Customer("John Doe", "jdoe1001", "password123");

        String firstAccountNumber = bank.createAdditionalAccount(customer);
        String secondAccountNumber = bank.createAdditionalAccount(customer);

        assertNotEquals(firstAccountNumber, secondAccountNumber);
    }

    @Test
    public void testAdminLogin() {
        Bank bank = new Bank();

        assertTrue(bank.authenticateAdminLogin("admin123"));
        assertFalse(bank.authenticateAdminLogin("wrongpassword"));
    }

    @Test
    public void testFeeCollection() {
        Bank bank = new Bank();
        BankAccount account = new BankAccount();

        bank.registerExistingAccount("1001", account);
        account.deposit(50);
        bank.authenticateAdminLogin("admin123");

        assertTrue(bank.collectFees("1001", 10));
        assertEquals(40, account.getBalance(), 0.01);
    }

    @Test
    public void testInvalidFeeCollection() {
        Bank bank = new Bank();
        BankAccount account = new BankAccount();

        bank.registerExistingAccount("1001", account);
        account.deposit(50);
        bank.authenticateAdminLogin("admin123");

        assertFalse(bank.collectFees("1001", -10));
        assertFalse(bank.collectFees("1001", 100));
        assertEquals(50, account.getBalance(), 0.01);
    }

    @Test
    public void testFeeCollectionWithoutAdminLogin() {
        Bank bank = new Bank();
        BankAccount account = new BankAccount();

        bank.registerExistingAccount("1001", account);
        account.deposit(50);

        assertFalse(bank.collectFees("1001", 10));
        assertEquals(50, account.getBalance(), 0.01);
    }

    @Test
    public void testInterestDeposit() {
        Bank bank = new Bank();
        BankAccount account = new BankAccount();

        bank.registerExistingAccount("1001", account);
        account.deposit(50);
        bank.authenticateAdminLogin("admin123");

        assertTrue(bank.depositInterest("1001", 10));
        assertEquals(60, account.getBalance(), 0.01);
    }

    @Test
    public void testInvalidInterestDeposit() {
        Bank bank = new Bank();
        BankAccount account = new BankAccount();

        bank.registerExistingAccount("1001", account);
        account.deposit(50);
        bank.authenticateAdminLogin("admin123");

        assertFalse(bank.depositInterest("1001", -5));
        assertEquals(50, account.getBalance(), 0.01);
    }

    @Test
    public void testAdminCannotCollectFeesAfterLogout() {
        Bank bank = new Bank();
        BankAccount account = new BankAccount();

        bank.registerExistingAccount("1001", account);
        account.deposit(50);

        bank.authenticateAdminLogin("admin123");
        bank.logoutAdmin();

        assertFalse(bank.collectFees("1001", 10));
        assertEquals(50, account.getBalance(), 0.01);
    }

    @Test
    public void testAdminAddsNewCustomer() {
        Bank bank = new Bank();
        bank.authenticateAdminLogin("admin123");

        Customer customer = bank.addCustomer("John Doe", "jdoe1001", "password123");

        assertNotNull(customer);
        assertEquals("John Doe", customer.getCustomerName());
        assertEquals(1, customer.getNumberOfAccounts());
        assertEquals(1, bank.getNumberOfCustomers());
    }

    @Test
    public void testNonAdminCannotAddNewCustomer() {
        Bank bank = new Bank();

        assertNull(bank.addCustomer("John Doe", "jdoe1001", "password123"));
    }

    @Test
    public void testAdminCanViewCustomers() {
        Bank bank = new Bank();
        bank.authenticateAdminLogin("admin123");

        Customer customer = bank.addCustomer("John Doe", "jdoe1001", "password123");

        assertTrue(bank.getCustomers().contains(customer));
    }

    @Test
    public void testNonAdminCannotViewCustomers() {
        Bank bank = new Bank();

        assertNull(bank.getCustomers());
    }

    @Test
    public void testBankAuthenticatesRegisteredCustomer() {
        Bank bank = new Bank();
        Customer customer = new Customer("John Doe", "jdoe1001", "password123");

        bank.registerExistingCustomer(customer);

        assertSame(customer,
                bank.authenticateCustomer("jdoe1001", "password123"));
        assertNull(bank.authenticateCustomer("jdoe1001", "wrongpassword"));
    }

    @Test
    public void testCannotAddCustomerWithDuplicateUsername() {
        Bank bank = new Bank();
        bank.authenticateAdminLogin("admin123");

        assertNotNull(bank.addCustomer("John Doe", "jdoe1001", "password123"));
        assertNull(bank.addCustomer("John Doe", "jdoe1001", "differentpassword"));

        assertEquals(1, bank.getNumberOfCustomers());
    }

    @Test
    public void testNewCustomerCanLogin() {
        Bank bank = new Bank();
        bank.authenticateAdminLogin("admin123");

        Customer customer = bank.addCustomer("John Doe", "jdoe1001", "password123");

        assertSame(customer,
                bank.authenticateCustomer("jdoe1001", "password123"));
    }

    @Test
    public void testCustomerCannotAccessAnotherCustomersAccount() {
        Bank bank = new Bank();

        Customer john = new Customer("John Doe", "jdoe1001", "password123");
        Customer jane = new Customer("Jane Doe", "jdoe1002", "password123");

        String accountNumber = bank.createAdditionalAccount(jane);

        assertNull(bank.getAccountForCustomer(john, accountNumber));
    }

    @Test
    public void testTransferToAnotherCustomer() {
        Bank bank = new Bank();

        Customer john = new Customer("John Doe", "jdoe1001", "password123");
        Customer jane = new Customer("Jane Doe", "janedoe1002", "password456");

        String johnAccount = bank.createAdditionalAccount(john);
        String janeAccount = bank.createAdditionalAccount(jane);

        BankAccount sender = bank.getAccountForCustomer(john, johnAccount);
        BankAccount receiver = bank.getAccountForCustomer(jane, janeAccount);

        sender.deposit(100);

        assertTrue(bank.transferMoney(john, johnAccount, janeAccount, 40));
        assertEquals(60, sender.getBalance(), 0.01);
        assertEquals(40, receiver.getBalance(), 0.01);
    }

    @Test
    public void testCannotTransferFromAnotherCustomersAccount() {
        Bank bank = new Bank();

        Customer john = new Customer("John Doe", "jdoe1001", "password123");
        Customer jane = new Customer("Jane Doe", "janedoe1002", "password456");

        String johnAccount = bank.createAdditionalAccount(john);
        String janeAccount = bank.createAdditionalAccount(jane);

        BankAccount johnBankAccount =
                bank.getAccountForCustomer(john, johnAccount);

        johnBankAccount.deposit(100);

        assertFalse(bank.transferMoney(jane, johnAccount, janeAccount, 40));
        assertEquals(100, johnBankAccount.getBalance(), 0.01);
    }
}
