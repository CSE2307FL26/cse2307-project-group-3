package main;

import java.util.HashMap;

public class Bank {
    private HashMap<String, BankAccount> accounts = new HashMap<>();

    
    public boolean closeAccount(String accountNumber) {
    BankAccount account = accounts.get(accountNumber);

    if (account == null) {
        System.out.print("Invalid account number.");
        return false;
    }

    if (account.getBalance() != 0) {
        System.out.print("Account balance must be zero to close the account.");
        return false;
    }

    accounts.remove(accountNumber);
    System.out.print("Account closed successfully.");
    return true;
}
}