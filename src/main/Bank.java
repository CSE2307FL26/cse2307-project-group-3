package main;

import java.util.HashMap;

public class Bank {

    private HashMap<String, BankAccount> accounts;
    private int nextAccountNumber;
    private static String AdminPassword = "admin123";

    public Bank() {
        this.accounts = new HashMap<String, BankAccount>();
        this.nextAccountNumber = 1001;
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

    public void transferMoney(String senderAccount, String receiverAccount, double amount) {
        BankAccount sender = accounts.get(senderAccount);
        BankAccount receiver = accounts.get(receiverAccount);

        if (sender == null || receiver == null) {
            return;
    }

        if (sender.getBalance() < amount) {
            return;
    }

        if (amount <= 0) {
            return;
    }

        if (senderAccount.equals(receiverAccount)) {
            return;
    }

        sender.withdraw(amount);
        receiver.deposit(amount);
    }

    public String createAdditionalAccount(Customer customer) {
        String accountNumber = String.valueOf(nextAccountNumber);

        // Check to see if account number is valid to be added
        while (accounts.containsKey(accountNumber)) {
            nextAccountNumber++;
            accountNumber = String.valueOf(nextAccountNumber);
        }

        BankAccount account = new BankAccount();

        accounts.put(accountNumber, account);
        customer.addAccountNumber(accountNumber);

        nextAccountNumber++;

        return accountNumber;
    }

    public boolean authenticateAdminLogin(String password) {
        return AdminPassword.equals(password);
    }

    public boolean collectFees(String adminPassword, String accountNumber, double feeAmount) {
        if (!authenticateAdminLogin(adminPassword) || feeAmount <= 0) {
            return false;
        }

        BankAccount account = getAccount(accountNumber);
        if (account == null || feeAmount > account.getBalance()) {
            return false;
        }
        else account.withdraw(feeAmount);

        return true;
    }


    public boolean depositInterest(String adminPassword, String accountNumber, double interestAmount) {
        if (!authenticateAdminLogin(adminPassword) || interestAmount <= 0) {
            return false;
        }

        BankAccount account = getAccount(accountNumber);
        if (account == null) {
            return false;
        }
        else {
            account.deposit(interestAmount);
        }
       
        return true;
    }


    private BankAccount getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    
}