package main;

public class BankAccount {

    private double balance;
    public BankAccount() {
        this.balance = 0;
    }

    public void deposit(double amount) {
        if(amount > 0) {
            this.balance += amount;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public double getBalance() {
        return this.balance;
    }

    public boolean closeAccount(String accountNumber) {
    
        if(customer.ownsAccount(accountNumber)) {

            if(this.balance == 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

        BankAccount account = accountNumbers.get(accountNumber);

        if (account == null) {
            return false;
    }
    
        if (account.getBalance() != 0) {
        return false;
    }

    accountNumbers.remove(accountNumber);
    return true;
}

    public BankAccount getAccount(String accountNumber) {
        return accountNumbers.get(accountNumber);
    }
}
}