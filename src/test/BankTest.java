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
        Customer customer = new Customer("Melissa");
        BankAccount account = new BankAccount();
        bank.registerExistingAccount("1001", account);
        customer.addAccountNumber("1001");
        assertTrue(bank.closeAccount(customer, "1001"));
        assertFalse(customer.ownsAccount("1001"));
    }

    @Test
    public void testCannotCloseAccountWithMoney() {
        Bank bank = new Bank();
        Customer customer = new Customer("Melissa");
        BankAccount account = new BankAccount();
        account.deposit(50);
        bank.registerExistingAccount("1001", account);
        customer.addAccountNumber("1001");
        assertFalse(bank.closeAccount(customer, "1001"));
    }

    @Test
    public void testTransferDescreasesSenderBalance() {
        Bank bank = new Bank();
        Customer customer = new Customer("Melissa");
        BankAccount sender = new BankAccount();
        BankAccount receiver = new BankAccount();
        bank.registerExistingAccount("1001", sender);
        bank.registerExistingAccount("1002", receiver);
        customer.addAccountNumber("1001");
        customer.addAccountNumber("1002");
        sender.deposit(100);
        bank.transferMoney("1001", "1002", 50);
        assertEquals(50, sender.getBalance(), 0.01);
    }

    @Test
    public void testTransferIncreasesReceiverBalance() {
        Bank bank = new Bank();
        Customer customer = new Customer("Melissa");
        BankAccount sender = new BankAccount();
        BankAccount receiver = new BankAccount();
        bank.registerExistingAccount("1001", sender);
        bank.registerExistingAccount("1002", receiver);
        customer.addAccountNumber("1001");
        customer.addAccountNumber("1002");
        sender.deposit(100);
        bank.transferMoney("1001", "1002", 50);
        assertEquals(50, receiver.getBalance(), 0.01);
    }

    @Test
    public void testTransferFailsWithInsufficientFunds() {
        Bank bank = new Bank();
        Customer customer = new Customer("Melissa");
        BankAccount sender = new BankAccount();
        BankAccount receiver = new BankAccount();
        bank.registerExistingAccount("1001", sender);
        bank.registerExistingAccount("1002", receiver);
        customer.addAccountNumber("1001");
        customer.addAccountNumber("1002");
        sender.deposit(100);
        bank.transferMoney("1001", "1002", 150);
        assertEquals(100, sender.getBalance(), 0.01);
        assertEquals(0, receiver.getBalance(), 0.01);
}


    @Test
    public void testTransferFailsWithNegativeAmount() {
        Bank bank = new Bank();
        Customer customer = new Customer("Melissa");
        BankAccount sender = new BankAccount();
        BankAccount receiver = new BankAccount();
        bank.registerExistingAccount("1001", sender);
        bank.registerExistingAccount("1002", receiver);
        customer.addAccountNumber("1001");
        customer.addAccountNumber("1002");
        sender.deposit(100);
        bank.transferMoney("1001", "1002", -50);
        assertEquals(100, sender.getBalance(), 0.01);
        assertEquals(0, receiver.getBalance(), 0.01);
    }

    @Test
    public void testTransferFailswithNonexistentReceiver() {
        Bank bank = new Bank();
        Customer customer = new Customer("Melissa");
        BankAccount sender = new BankAccount();
        bank.registerExistingAccount("1001", sender);
        customer.addAccountNumber("1001");
        sender.deposit(100);
        bank.transferMoney("1001", "9999", 50);
        assertEquals(100, sender.getBalance(), 0.01);
    }

    @Test
    public void testTransferFailswithSameAccount() {
        Bank bank = new Bank();
        Customer customer = new Customer("Melissa");
        BankAccount sender = new BankAccount();
        bank.registerExistingAccount("1001", sender);
        customer.addAccountNumber("1001");
        sender.deposit(100);
        bank.transferMoney("1001", "1001", 50);
        assertEquals(100, sender.getBalance(), 0.01);
    }

    @Test 
    public void testCreateAdditionalAccount() {
        Bank bank = new Bank();
        Customer customer = new Customer("Melissa");
        BankAccount account = new BankAccount();
        
        bank.registerExistingAccount("1001",  account);
        customer.addAccountNumber("1001");

        String newAccountNumber = bank.createAdditionalAccount(customer);
        
        // Check that new account can be retrieved
        assertNotNull(bank.getAccountForCustomer(customer, newAccountNumber));

    }

    @Test 
    public void testCreateAdditionalAccountIncreasesCustomerAccountCount() {
        Bank bank = new Bank();
        Customer customer = new Customer("Melissa");
        BankAccount account = new BankAccount();

        bank.registerExistingAccount("1001", account);
        customer.addAccountNumber("1001");

        bank.createAdditionalAccount(customer);

        assertEquals(2, customer.getNumberOfAccounts());
    }

    @Test 
    public void testAdditionalAccountsHaveUniqueNumbers() {
        Bank bank = new Bank();
        Customer customer = new Customer("Melissa");
        
        String firstAccountNumber = bank.createAdditionalAccount(customer);
        String secondAccountNumber = bank.createAdditionalAccount(customer);

        assertNotEquals(firstAccountNumber, secondAccountNumber);
    }

    @Test 
    public void testAdditionalAccountsStartsWithZeroBalance() {
        Bank bank = new Bank();
        Customer customer = new Customer("Melissa");

        String accountNumber = bank.createAdditionalAccount(customer);
        BankAccount account = bank.getAccountForCustomer(customer, accountNumber);

        assertEquals(0.0, account.getBalance(), 0.01);

    }

    @Test
    public void testCorrectAdminLogin() {
        Bank bank = new Bank();
        assertTrue(bank.authenticateAdminLogin("admin123"));
    }

    @Test
    public void testIncorrectAdminLogin() {
        Bank bank = new Bank();
        assertFalse(bank.authenticateAdminLogin("wrongpassword"));
    }

    @Test
    public void testFeeCollection() {
        Bank bank = new Bank();
        BankAccount account = new BankAccount();
        bank.registerExistingAccount("1001", account);
        account.deposit(50.0);
        bank.collectFees("admin123", "1001", 10.0);
        assertEquals(40, account.getBalance(), 0.01);
    }

    @Test
    public void testNegativeFeeCollection() {
        //should remove 0, not add 10
        Bank bank = new Bank();
        BankAccount account = new BankAccount();
        bank.registerExistingAccount("1001", account);
        account.deposit(50.0);
        bank.collectFees("admin123", "1001", -10.0);
        assertEquals(50, account.getBalance(), 0.01);
    }

    @Test
    public void testInsufficientFundsFeeCollection() {
        Bank bank = new Bank();
        BankAccount account = new BankAccount();

        bank.registerExistingAccount("1001", account);
        account.deposit(50);

        assertFalse(bank.collectFees("admin123", "1001", 100));
    }

    @Test
    public void testImposterFeeCollection() {
        Bank bank = new Bank();
        BankAccount account = new BankAccount();
        bank.registerExistingAccount("1001", account);
        account.deposit(50.0);
        bank.collectFees("wrongpassword", "1001", 10.0);
        assertEquals(50, account.getBalance(), 0.01);
    }


    @Test
    public void testInterestDeposit() {
        Bank bank = new Bank();
        BankAccount account = new BankAccount();
        bank.registerExistingAccount("1001", account);
        account.deposit(50.0);
        bank.depositInterest("admin123", "1001", 10.0);
        assertEquals(60, account.getBalance(), 0.01);
    }

    @Test
    public void testImposterInterestDeposit() {
        Bank bank = new Bank();
        BankAccount account = new BankAccount();
        bank.registerExistingAccount("1001", account);
        account.deposit(50.0);
        bank.depositInterest("wrongpassword", "1001", 10.0);
        assertEquals(50, account.getBalance(), 0.01);
    }

    @Test
    public void testNegativeInterestDeposit() {
        //should add 0 not 10
        Bank bank = new Bank();
        BankAccount account = new BankAccount();
        bank.registerExistingAccount("1001", account);
        account.deposit(50.0);
        bank.depositInterest("admin123", "1001", -5.0);
        assertEquals(50, account.getBalance(), 0.01);
    }
}