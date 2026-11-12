package test;

import main.BankAccount;
import main.Bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AdminTest {
    
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
