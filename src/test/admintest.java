package test;
import main.BankAccount;
import main.Bank;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
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
        bank.deposit("1001", 50.0);
        bank.collectFees("admin123", "1001", 10.0);
        assertEquals(40, account.getBalance(), 0.01);
    }

    @Test
    public void testImposterFeeCollection() {
        Bank bank = new Bank();
        BankAccount account = new BankAccount();
        bank.registerExistingAccount("1001", account);
        bank.deposit("1001", 50.0);
        bank.collectFees("wrongpassword", "1001", 10.0);
        assertEquals(50, account.getBalance(), 0.01);
    }

}
