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

}
