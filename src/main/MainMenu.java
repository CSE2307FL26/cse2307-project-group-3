package main;

import java.util.Scanner;

public class MainMenu {
    
    private static final int DEPOSIT_SELECTION = 1;
    private static final int WITHDRAWAL_SELECTION = 2;
    private static int TRANSFER_SELECTION = 3;
    private static final int EXIT_SELECTION = 4;
    private static final int MAX_SELECTION = 4;

	private BankAccount userAccount;
    private Bank bank;
    private Scanner keyboardInput;

    public MainMenu() {
        this.userAccount = new BankAccount();
        this.keyboardInput = new Scanner(System.in);
    }

    public void displayOptions() {
        System.out.println("1. Make a deposit");
        System.out.println("2. Make a withdrawal");
        System.out.println("3. Initiate a transfer");
        System.out.println("4. Exit the app");

    }

    public int getUserSelection(int max) {
        int selection = -1;
        while(selection < 1 || selection > max) {
            System.out.print("Please make a selection: ");
            selection = keyboardInput.nextInt();
        }
        return selection;
    }

    public void processInput(int selection) {
        switch (selection) {
            case 1:
                performDeposit();
                break;
            case 2:
                performWithdrawal();
                break;
            case 3:
                performTransfer();
                break;
            case 4:
            default:
                System.out.println("Invalid selection, please try again.");
        }
    }

    public void performDeposit() {
        System.out.print("How much would you like to deposit: ");
        double depositAmount = keyboardInput.nextInt();
        try {
            userAccount.deposit(depositAmount);
            System.out.println("Deposit successful.");
        } catch (IllegalArgumentException exception) {
            System.out.println("Deposit could not be completed.");
        }
    }

    private void performWithdrawal() {
        System.out.print("How much would you like to withdraw: ");
        double withdrawalAmount = keyboardInput.nextDouble();
        try {
            userAccount.withdraw(withdrawalAmount);
            System.out.println("Withdrawal successful.");
        } catch (IllegalArgumentException exception) {
            System.out.println("Withdrawal could not be completed.");
        }
    }
    private void performTransfer() {
        System.out.print("How much would you like to transfer: ");
        double transferAmount = keyboardInput.nextDouble();
        try {
            userAccount.withdraw(transferAmount);
            System.out.println("Transfer successful.");
        } catch (IllegalArgumentException exception) {
            System.out.println("Transfer could not be completed.");
        }
    }
    public void run() {
        System.out.println("Welcome to the 2307 Bank App!");

        int selection = -1;
        while(selection != EXIT_SELECTION) {
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
