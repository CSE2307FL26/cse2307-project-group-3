package test;

import main.Bank;;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;

public class BankTest {

    @Test
    public void testTransferDecreasesSenderBalance() {
    Bank bank = new Bank();

    BankAccount sender = bank.addAccount();
    BankAccount receiver = bank.addAccount();

    sender.deposit(100);

    bank.transfer(sender.getAccountNumber(), receiver.getAccountNumber(), 50);
    assertEquals(50, sender.getBalance(), 0.01);
}
   @Test
    public void testTransferDecreasesSenderBalance() {
    Bank bank = new Bank();
    BankAccount sender = bank.createAccount();
    BankAccount receiver = bank.createAccount();

    sender.deposit(100);

    bank.transfer(sender.getAccountNumber(), receiver.getAccountNumber(), 50);

    assertEquals(50, sender.getBalance(), 0.01);
}

    @Test
    public void testTransferIncreasesReceiverBalance() {
    Bank bank = new Bank();
    BankAccount sender = bank.createAccount();
    BankAccount receiver = bank.createAccount();

    sender.deposit(100);

    bank.transfer(sender.getAccountNumber(), receiver.getAccountNumber(), 50);

    assertEquals(50, receiver.getBalance(), 0.01);
}

    @Test
    public void testTransferFailsWithInsufficientFunds() {
    Bank bank = new Bank();
    BankAccount sender = bank.createAccount();
    BankAccount receiver = bank.createAccount();

    sender.deposit(100);

    try {
        bank.transfer(sender.getAccountNumber(), receiver.getAccountNumber(), 150);
        fail();
    } catch (IllegalArgumentException e) {
        // Test passes
    }
}

    @Test
    public void testTransferFailsWithNegativeAmount() {
    Bank bank = new Bank();
    BankAccount sender = bank.createAccount();
    BankAccount receiver = bank.createAccount();

    sender.deposit(100);

    try {
        bank.transfer(sender.getAccountNumber(), receiver.getAccountNumber(), -50);
        fail();
    } catch (IllegalArgumentException e) {
        // Test passes
    }
}

    @Test
    public void testTransferFailsWithSameAccount() {
    Bank bank = new Bank();
    BankAccount sender = bank.createAccount();

    sender.deposit(100);

    try {
        bank.transfer(sender.getAccountNumber(), sender.getAccountNumber(), 50);
        fail();
    } catch (IllegalArgumentException e) {
        // Test passes
    }
}

    @Test
    public void testTransferFailsWithNullReceiver() {
    Bank bank = new Bank();
    BankAccount sender = bank.createAccount();

    sender.deposit(100);

    try {
        bank.transfer(sender.getAccountNumber(), null, 50);
        fail();
    } catch (IllegalArgumentException e) {
        // Test passes
    }
}

    @Test
    public void testTransferFailsWithNullSender() {
    Bank bank = new Bank();
    BankAccount receiver = bank.createAccount();

    try {
        bank.transfer(null, receiver.getAccountNumber(), 50);
        fail();
    } catch (IllegalArgumentException e) {
        // Test passes
    }
}