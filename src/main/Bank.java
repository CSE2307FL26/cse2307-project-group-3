package main;

import java.util.HashMap;

public class Bank {
    private HashMap<String, BankAccount> accounts;

    public Bank() {
        accounts = new HashMap<>();
    }

    public void addAccount(String accountNumber, BankAccount account) {
        accounts.put(accountNumber, account);
    }

    public boolean closeAccount(String accountNumber) {
        BankAccount account = accounts.get(accountNumber);

        if (account == null) {
            return false;
        }

        if (account.getBalance() != 0) {
            return false;
        }

        accounts.remove(accountNumber);
        return true;
    }

    public BankAccount getAccount(String accountNumber) {
    return accounts.get(accountNumber);
}
}