package main;

import java.util.Scanner;

public class MainMenu {

    private static final int DEPOSIT_SELECTION = 1;
    private static final int WITHDRAWAL_SELECTION = 2;
    private static final int CLOSE_ACCOUNT_SELECTION = 3;
    private static final int CHECK_BALANCE_SELECTION = 4;
    private static final int TRANSACTION_HISTORY_SELECTION = 5;
    private static final int EXIT_SELECTION = 6;
    private static final int MAX_SELECTION = 6; 

    private Customer customer;
    private Bank bank;
    private Scanner keyboardInput;

    public MainMenu() {
        this.customer = new Customer("John Doe");
        this.bank = new Bank();
        this.keyboardInput = new Scanner(System.in);

        BankAccount demoAccount = new BankAccount();
        bank.registerExistingAccount("1001", demoAccount);
        customer.addAccountNumber("1001");
    }

    public void displayOptions() {
        System.out.println("1. Make a deposit");
        System.out.println("2. Make a withdrawal");
        System.out.println("3. Close an account");
        System.out.println("4. Check balance");
        System.out.println("5. View transaction history");
        System.out.println("6. Exit the app");

    }

    public int getUserSelection(int max) {
        int selection = -1;

        while (selection < 1 || selection > max) {
            System.out.print("Please make a selection: ");
            selection = keyboardInput.nextInt();
        }

        return selection;
    }

    public void processInput(int selection) {
        switch (selection) {
            case DEPOSIT_SELECTION:
                performDeposit();
                break;
            case WITHDRAWAL_SELECTION:
                performWithdrawal();
                break;
            case CLOSE_ACCOUNT_SELECTION:
                closeAccount();
                break;
            case CHECK_BALANCE_SELECTION:
                checkBalance();
                break;
            case TRANSACTION_HISTORY_SELECTION:
                viewTransactionHistory();
                break;
            case EXIT_SELECTION:
                System.out.println("Exiting the app...");
                break;
            default:
                System.out.println("Invalid selection, please try again.");
        }
    }

    private void performDeposit() {
        System.out.print("Enter your account number: ");
        String accountNumber = keyboardInput.next();

        BankAccount account =
                bank.getAccountForCustomer(customer, accountNumber);

        if (account == null) {
            System.out.println("Invalid account number.");
            return;
        }

        System.out.print("How much would you like to deposit: ");
        double depositAmount = keyboardInput.nextDouble();

        try {
            account.deposit(depositAmount);
            System.out.println("Deposit successful.");
        } catch (IllegalArgumentException exception) {
            System.out.println("Deposit could not be completed.");
        }
    }

    private void performWithdrawal() {
        System.out.print("Enter your account number: ");
        String accountNumber = keyboardInput.next();

        BankAccount account =
                bank.getAccountForCustomer(customer, accountNumber);

        if (account == null) {
            System.out.println("Invalid account number.");
            return;
        }

        System.out.print("How much would you like to withdraw: ");
        double withdrawalAmount = keyboardInput.nextDouble();

        try {
            account.withdraw(withdrawalAmount);
            System.out.println("Withdrawal successful.");
        } catch (IllegalArgumentException exception) {
            System.out.println("Withdrawal could not be completed.");
        }
    }

    private void closeAccount() {
        System.out.print("Enter the account number to close: ");
        String accountNumber = keyboardInput.next();

        boolean isClosed = bank.closeAccount(customer, accountNumber);

        if (isClosed) {
            System.out.println("Account closed successfully.");
        } else {
            System.out.println(
                    "Account could not be closed. "
                    + "The account may not exist or may have money in it.");
        }
    }

    private void checkBalance() {
        System.out.print("Enter your account number: ");
        String accountNumber = keyboardInput.next();

        BankAccount account =
                bank.getAccountForCustomer(customer, accountNumber);

        if (account == null) {
            System.out.println("Invalid account number.");
            return;
        }

        System.out.println("Your current balance is: $" + account.getBalance());
    }

    private void viewTransactionHistory() {
        System.out.print("Enter your account number: ");
        String accountNumber = keyboardInput.next();

        BankAccount account = bank.getAccountForCustomer(customer, accountNumber);

        if (account == null) {
            System.out.println("Invalid account number.");
            return;
        }

        System.out.println("Transaction History for Account " + accountNumber + ":");

        if (account.getTransactionHistory().isEmpty()) {
            System.out.println("No transactions recorded.");
            return;
        }

        for (String transaction : account.getTransactionHistory()) {
            System.out.println(transaction);
        }
        
    }

    public void run() {
        System.out.println("Welcome to the 2307 Bank App!");

        int selection = -1;

        while (selection != EXIT_SELECTION) {
            displayOptions();
            selection = getUserSelection(MAX_SELECTION);
            processInput(selection);
        }

        System.out.println("Thank you for using the 2307 Bank App!");
    }

    public static void main(String[] args) {
        MainMenu bankApp = new MainMenu();
        bankApp.run();
    }
}
