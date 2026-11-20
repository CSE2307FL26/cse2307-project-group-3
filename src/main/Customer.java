package main;

import java.util.HashSet;
import java.util.Set;

public class Customer {

    private final String customerName;
    private final String username;
    private final String password;
    private final Set<String> accountNumbers;

    public Customer(String customerName, String username, String password) {
        this.customerName = customerName;
        this.username = username;
        this.password = password;
        this.accountNumbers = new HashSet<String>();
    }

    public void addAccountNumber(String accountNumber) {
        accountNumbers.add(accountNumber);
    }

    public boolean ownsAccount(String accountNumber) {
        return accountNumbers.contains(accountNumber);
    }

    public boolean removeAccountNumber(String accountNumber) {
        return accountNumbers.remove(accountNumber);
    }

    public int getNumberOfAccounts() {
        return accountNumbers.size();
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getUsername() {
        return username;
    }

    public Set<String> getAccountNumbers() {
        return new HashSet<String>(accountNumbers);
    }

    public boolean authenticate(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
}