package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import main.Customer;

public class CustomerTest {

    @Test
    public void testCustomerOwnsAccount() {
        Customer customer = new Customer("Melissa");
        customer.addAccountNumber("1001");
        assertTrue(customer.ownsAccount("1001"));
    }

    @Test
    public void testRemoveAccount() {
        Customer customer = new Customer("Melissa");
        customer.addAccountNumber("1001");
        customer.removeAccountNumber("1001");
        assertFalse(customer.ownsAccount("1001"));
    }

    @Test
    public void testGetAccountNumbers() {
        Customer customer = new Customer("Melissa");
        customer.addAccountNumber("1001");
        customer.addAccountNumber("1002");

        assertTrue(customer.getAccountNumbers().contains("1001"));
        assertTrue(customer.getAccountNumbers().contains("1002"));
    }
}