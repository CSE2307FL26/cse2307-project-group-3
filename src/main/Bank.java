package main;

import java.util.HashMap;

public class Bank {

    private HashMap<String, BankAccount> accounts = new HashMap<>();

    public boolean closeAccount(Customer customer, String accountNumber) {
        if (!customer.ownsAccount(accountNumber)) {
            return false;
        }

        BankAccount account = accounts.get(accountNumber);

        if (account == null || account.getBalance() != 0.0) {
            return false;
        }

        accounts.remove(accountNumber);
        customer.removeAccountNumber(accountNumber);

        return true;
    }

    public BankAccount getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }
}