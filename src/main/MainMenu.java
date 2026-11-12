package main;

import java.util.Scanner;
import java.util.Set;

public class MainMenu {

    private static final int DEPOSIT_SELECTION = 1;
    private static final int WITHDRAWAL_SELECTION = 2;
    private static final int CLOSE_ACCOUNT_SELECTION = 3;
    private static final int CHECK_BALANCE_SELECTION = 4;
    private static final int TRANSACTION_HISTORY_SELECTION = 5;
    private static final int TRANSFER_SELECTION = 6;
    private static final int CREATE_ACCOUNT_SELECTION = 7;
    private static final int ADMIN_LOGIN_SELECTION = 8;
    private static final int EXIT_SELECTION = 9;
    private static final int MAX_SELECTION = 9;

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
        System.out.println("\nWelcome, " + customer.getCustomerName() + "!\n");
        System.out.println("Your Accounts: ");
        Set<String> accountNumbers = customer.getAccountNumbers();
        
        if (accountNumbers.isEmpty()) {
            System.out.println("No accounts found.");
        } else {
            for (String accountNumber : accountNumbers) {
                System.out.println("Account " + accountNumber);
            }
        }

        System.out.println("\n1. Make a deposit");
        System.out.println("2. Make a withdrawal");
        System.out.println("3. Close an account");
        System.out.println("4. Check balance");
        System.out.println("5. View transaction history");
        System.out.println("6. Transfer to another account");
        System.out.println("7. Create additional account");
        System.out.println("8. Admin login");
        System.out.println("9. Exit the app");
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
            case TRANSFER_SELECTION:
                performTransfer();
                break;
            case CREATE_ACCOUNT_SELECTION:
                createAdditionalAccount();
                break;
            case ADMIN_LOGIN_SELECTION:
                loginAsAdmin();
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

        System.out.println("\nTransaction History for Account " + accountNumber + ":");

        if (account.getTransactionHistory().isEmpty()) {
            System.out.println("No transactions recorded.");
            return;
        }

        for (String transaction : account.getTransactionHistory()) {
            System.out.println(transaction);
        }
        
    }

    private void createAdditionalAccount() {
        String accountNumber = bank.createAdditionalAccount(customer);

        System.out.println("Account create successfully.");
        System.out.println("Your new account number is: " + accountNumber);
    }

    private void performTransfer() {
        System.out.print("Enter your account number: ");
        String senderAccountNumber = keyboardInput.next();

        BankAccount senderAccount = bank.getAccountForCustomer(customer, senderAccountNumber);

        if (senderAccount == null) {
            System.out.println("Invalid account number.");
            return;
        }

        System.out.print("Enter the recipient's account number: ");
        String receiverAccountNumber = keyboardInput.next();

        BankAccount receiverAccount = bank.getAccountForCustomer(customer, receiverAccountNumber);

        if (receiverAccount == null) {
            System.out.println("Invalid recipient account number.");
            return;
        }

        System.out.print("How much would you like to transfer: ");
        double transferAmount = keyboardInput.nextDouble();

        try {
            bank.transferMoney(senderAccountNumber, receiverAccountNumber, transferAmount);
            System.out.println("Transfer successful.");
        } catch (IllegalArgumentException exception) {
            System.out.println("Transfer could not be completed.");
        }
    }

    private void adminInterestDeposit(String password) {
        System.out.print("Enter the account number to deposit interest: ");
        String accountNumber = keyboardInput.next();
        System.out.print("Enter the interest amount: ");
        double interestAmount = keyboardInput.nextDouble();
        boolean interestDeposited = bank.depositInterest(password, accountNumber, interestAmount);
        if (interestDeposited) {
            System.out.println("Interest deposited successfully.");
        } else {
            System.out.println("Failed to deposit interest.");
        }
    }

    private void adminFeeCollection(String password) {
        System.out.print("Enter the account number to collect fees from: ");
        String accountNumber = keyboardInput.next();
        System.out.print("Enter the fee amount: ");
        double feeAmount = keyboardInput.nextDouble();
        boolean feesCollected = bank.collectFees(password, accountNumber, feeAmount);
        if (feesCollected) {
            System.out.println("Fees collected successfully.");
        } else {
            System.out.println("Failed to collect fees.");
        }
    }

    private void adminOptions(String password) {
        System.out.println("Admin login successful.");
        System.out.println("1. Deposit interest to an account");
        System.out.println("2. Collect fees from an account");
        System.out.println("3. Exit the app");
        System.out.print("Please make a selection: ");

        String selection = keyboardInput.next();
        switch (selection) {
            case "1":
                //deposit interest to an account
                adminInterestDeposit(password);
                break;
            case "2":
                //collect fees from an account
                adminFeeCollection(password);
                break;
            case "3":
                System.out.println("Exiting the app...");
                break;
            default:
                System.out.println("Invalid selection, please try again.");
        }
    }

    private void loginAsAdmin() {
        System.out.print("Please enter the admin password(admin123): ");
        String password = keyboardInput.next();
        if (bank.authenticateAdminLogin(password)) {
            adminOptions(password);
        } else {
            System.out.println("Invalid admin password.");
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
