package main;

import java.util.ArrayList;

public class BankAccount {

    private double balance;
    private ArrayList<String> transactionHistory;

    public BankAccount() {
        this.balance = 0;
        this.transactionHistory = new ArrayList<String>();
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException();
        }

        balance += amount;

        // Add to transactionHistory
        transactionHistory.add("Deposit: $" + amount);
    }

    public double getBalance() {
        return balance;
    }

    public void withdraw(double amount) {
        if (amount <= 0 || amount > balance) {
            throw new IllegalArgumentException();
        }

        balance -= amount;

        // Add to transactionHistory
        transactionHistory.add("Withdrawal: $" + amount);   
    }

    public ArrayList<String> getTransactionHistory() {
        return transactionHistory;
    }

}
