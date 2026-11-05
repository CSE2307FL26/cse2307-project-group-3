package main;

import java.util.HashMap;

public class Customer {
    private HashMap<String, BankAccount> accountNumbers = new HashMap<>();
    private String customerName;

    public Customer(String customerName) {
        this.customerName = customerName;
        this.accountNumbers = new HashMap<>();
    }

    public void addAccount(String accountNumber, BankAccount account) {
        accountNumbers.put(accountNumber, account);
        }

    public boolean ownsAccount(String accountNumber) {
        return accountNumbers.containsKey(accountNumber);
    }

    public boolean removeAccountNumber(String accountNumber) {
        accountNumbers.remove(accountNumber);
        return accountNumbers.containsKey(accountNumber);
    }

    public int getNumberOfAccounts() {
        return accountNumbers.size();
    }

    public String getCustomerName() {
        return customerName;
    }

    public BankAccount getAccount(String accountNumber) {
        return accountNumbers.get(accountNumber);
    }
}