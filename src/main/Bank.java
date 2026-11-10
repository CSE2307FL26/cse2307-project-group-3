package main;

import java.util.HashMap;

public class Bank {

    private HashMap<String, BankAccount> accounts;

    public Bank() {
        this.accounts = new HashMap<String, BankAccount>();
    }

    public void registerExistingAccount(String accountNumber, BankAccount account) {
        accounts.put(accountNumber, account);
    }

    public BankAccount getAccountForCustomer(
            Customer customer, String accountNumber) {
        if (!customer.ownsAccount(accountNumber)) {
            return null;
        }
        return accounts.get(accountNumber);
    }

    public boolean closeAccount(Customer customer, String accountNumber) {
        BankAccount account = getAccountForCustomer(customer, accountNumber);
        if (account == null || account.getBalance() != 0) {
            return false;
        }
        accounts.remove(accountNumber);
        customer.removeAccountNumber(accountNumber);
        return true;
    }
}