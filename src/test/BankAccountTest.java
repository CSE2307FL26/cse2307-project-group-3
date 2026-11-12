package test;

import main.BankAccount;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;

public class BankAccountTest {

    @Test
    public void testDeposit() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(50);
        assertEquals(50, testAccount.getBalance(), 0.01);
    }

    @Test
    public void testInvalidDeposit() {
        BankAccount testAccount = new BankAccount();
        try {
            testAccount.deposit(-50);
            fail();
        } catch (IllegalArgumentException e) {
            //do nothing, test passes
        }
    }

    @Test
    public void testWithdrawalDecreasesBalance() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(100);
        testAccount.withdraw(30);
        assertEquals(70.0, testAccount.getBalance(), 0.01);
    }

    @Test
    public void testWithdrawalLargerThanBalanceFails() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(100.0);
        try {
            testAccount.withdraw(150.0);
            fail();
        } catch (IllegalArgumentException e) {
            // do nothing, test passes
        }
    }

    @Test
    public void testNegativeWithdrawalFails() {
        BankAccount testAccount = new BankAccount();
        try {
            testAccount.withdraw(-50);
            fail();
        } catch (IllegalArgumentException e) {
        }
    }
    
    @Test
    public void testCheckEmptyAccountBalance() {
        BankAccount account = new BankAccount();
        assertEquals(0.0, account.getBalance(), 0.01);
    }

    @Test 
    public void testNewAccountHasEmptyTransactionHistory() {
        BankAccount account = new BankAccount();

        assertEquals(0, account.getTransactionHistory().size());
    }

    @Test 
    public void testDepositInTransactionHistory() {
        BankAccount account = new BankAccount();

        account.deposit(100);

        assertEquals("Deposit: $100.0", account.getTransactionHistory().get(0));
    }

    @Test
    public void testWithdrawalInTransactionHistory() {
        BankAccount account = new BankAccount();
        
        account.deposit(100);
        account.withdraw(20);

        assertEquals("Withdrawal: $20.0", account.getTransactionHistory().get(1));

    }

}
