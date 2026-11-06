package main;

import java.util.HashMap;

public class Bank {
    private HashMap<String, BankAccount> accountNumbers = new HashMap<>();

    public Bank () {
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
git 
    public void transferMoney(String senderAccount, String receiverAccount, double amount) {
        BankAccount sender = accountNumbers.get(senderAccount);
        BankAccount receiver = accountNumbers.get(receiverAccount);

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

    public int getNumberOfAccounts() {
        return accountNumbers.size();
    }

    public BankAccount getAccount(String accountNumber) {
        return accountNumbers.get(accountNumber);
    }
}

