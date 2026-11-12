package test;

import main.BankAccount;
import main.Bank;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

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

    public class BankTest {

    @Test
    public void testCloseAccount() {
        Bank testBank = new Bank();
        assertTrue(testBank.closeAccount("accountNumber"));
    }

    @Test
    public void testCloseAccountWithNonZeroBalance() {
        BankAccount testAccount = new BankAccount();
        Bank testBank = new Bank();
        testAccount.deposit(50);
        assertFalse(testBank.closeAccount("accountNumber"));
    }

    @Test
    public void testCloseAccountWithInvalidAccountNumber() {
        Bank testBank = new Bank();
        testBank.closeAccount("999");
        assertFalse(testBank.closeAccount("accountNumber"));
}
    }
}
