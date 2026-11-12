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
}