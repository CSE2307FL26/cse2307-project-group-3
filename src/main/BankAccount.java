package main;

import java.util.ArrayList;

public class BankAccount {

    private double balance;
    private ArrayList<String> transactionHistory;
    private double threshold;

    public BankAccount() {
        this.balance = 0;
        this.transactionHistory = new ArrayList<String>();
        this.threshold = threshold;
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
        // Add to transactionHistory
        transactionHistory.add("Withdrawal: $" + amount);   
    }

    public ArrayList<String> getTransactionHistory() {
        return new ArrayList<String>(transactionHistory);
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public double getThreshold() {
        return threshold;
    }

    public boolean checkIfBelowThreshold() {
        if (balance < threshold) {
            return true;
        } else {
            return false;
        }
    }
}
