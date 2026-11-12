package main;

import java.util.HashSet;
import java.util.Set;

public class Customer {

    private String customerName;
    private Set<String> accountNumbers;

    public Customer(String customerName) {
        this.customerName = customerName;
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
    
    public Set<String> getAccountNumbers() {
        return new HashSet<String>(accountNumbers);
    }
}