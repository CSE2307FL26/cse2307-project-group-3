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
}