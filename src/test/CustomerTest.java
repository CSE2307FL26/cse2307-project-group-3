package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import main.Customer;

public class CustomerTest {

    @Test
    public void testCustomerOwnsAddedAccount() {
        Customer customer = new Customer("Melissa");

        customer.addAccountNumber("12345");

        assertTrue(customer.ownsAccount("12345"));
    }

    @Test
    public void testCustomerDoesNotOwnMissingAccount() {
        Customer customer = new Customer("Melissa");

        assertFalse(customer.ownsAccount("12345"));
    }

    @Test
    public void testRemovingAccountRemovesOwnership() {
        Customer customer = new Customer("Melissa");
        customer.addAccountNumber("12345");

        boolean accountWasRemoved =
                customer.removeAccountNumber("12345");

        assertTrue(accountWasRemoved);
        assertFalse(customer.ownsAccount("12345"));
    }

    @Test
    public void testAddingAccountIncreasesAccountCount() {
        Customer customer = new Customer("Melissa");

        customer.addAccountNumber("12345");

        assertEquals(1, customer.getNumberOfAccounts());
    }
}