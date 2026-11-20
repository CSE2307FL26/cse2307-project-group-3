package main;

import java.util.HashMap;
import java.util.ArrayList; 

public class Bank {

    private HashMap<String, BankAccount> accounts;
    private int nextAccountNumber;
    private static final String ADMIN_PASSWORD = "admin123";
    private boolean adminLoggedIn;
    private ArrayList<Customer> customers;

    public Bank() {
        this.accounts = new HashMap<String, BankAccount>();
        this.nextAccountNumber = 1001;
        this.adminLoggedIn = false;
        this.customers = new ArrayList<Customer>();
    }

    public void registerExistingAccount(String accountNumber, BankAccount account) {
        accounts.put(accountNumber, account);
    }

    public void registerExistingCustomer(Customer customer) {
        customers.add(customer);
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

    public boolean transferMoney(String senderAccount, String receiverAccount, double amount) {
        BankAccount sender = accounts.get(senderAccount);
        BankAccount receiver = accounts.get(receiverAccount);

        if (sender == null || receiver == null
                || amount <= 0 || !Double.isFinite(amount)
                || senderAccount.equals(receiverAccount)
                || sender.getBalance() < amount) {
            return false;
        }
        sender.withdraw(amount);
        receiver.deposit(amount);

        return true;
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
        adminLoggedIn = ADMIN_PASSWORD.equals(password);
        return adminLoggedIn;
    }

    public boolean collectFees(String accountNumber, double feeAmount) {
        if (!adminLoggedIn || feeAmount <= 0) {
            return false;
        }
        BankAccount account = accounts.get(accountNumber);

        if (account == null || feeAmount > account.getBalance()) {
            return false;
        }
        account.withdraw(feeAmount);
        return true;
    }


    public boolean depositInterest(String accountNumber, double interestAmount) {
        if (!adminLoggedIn || interestAmount <= 0) {
            return false;
        }
        BankAccount account = accounts.get(accountNumber);

        if (account == null) {
            return false;
        }
        account.deposit(interestAmount);
        return true;
    }

    public void logoutAdmin() {
        adminLoggedIn = false;
    }

    public Customer addCustomer(String customerName, String username, String password) {
        if (!adminLoggedIn || customerName.trim().isEmpty()
                || username.trim().isEmpty() || password.isEmpty()
                || usernameExists(username)) {
            return null;
        }
        Customer customer = new Customer(customerName, username, password);
        customers.add(customer);
        createAdditionalAccount(customer);

        return customer;
    }

    public int getNumberOfCustomers() {
        return customers.size();
    }

    public ArrayList<Customer> getCustomers() {
        if (!adminLoggedIn) {
            return null;
        }

        return new ArrayList<Customer>(customers);
    }

    public Customer authenticateCustomer(String username, String password) {
        for (Customer customer : customers) {
            if (customer.authenticate(username, password)) {
                return customer;
            }
        }
        return null;
    }

    public boolean usernameExists(String username) {
        for (Customer customer : customers) {
            if (customer.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}

