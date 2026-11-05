package main;

import java.util.Scanner;

public class MainMenu {
<<<<<<< HEAD

    private static final int EXIT_SELECTION = 2;
	private static final int MAX_SELECTION = 2;
=======
    
    private static final int DEPOSIT_SELECTION = 1;
    private static final int WITHDRAWAL_SELECTION = 2;
    private static final int EXIT_SELECTION = 3;
    private static final int MAX_SELECTION = 3;
>>>>>>> 0b0f5abacd5feb2344270eebb2ba8d66b72daa8f

	private BankAccount userAccount;
    private Scanner keyboardInput;

    public MainMenu() {
        this.userAccount = new BankAccount();
        this.keyboardInput = new Scanner(System.in);
    }

    public void displayOptions() {
<<<<<<< HEAD
        System.out.println("Welcome to the 237 Bank App!");
        
        System.out.println("1. Make a deposit");
        System.out.println("2. Exit the app");
=======
        System.out.println("1. Make a deposit");
        System.out.println("2. Make a withdrawal");
        System.out.println("3. Exit the app");
>>>>>>> 0b0f5abacd5feb2344270eebb2ba8d66b72daa8f

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
<<<<<<< HEAD
=======
                break;
            case 2:
                performWithdrawal();
                break;
            case 3: 
                break;
            default:
                System.out.println("Invalid selection, please try again.");
>>>>>>> 0b0f5abacd5feb2344270eebb2ba8d66b72daa8f
        }
    }

    public void performDeposit() {
<<<<<<< HEAD
        double depositAmount = -1;
        while(depositAmount < 0) {
            System.out.print("How much would you like to deposit: ");
            depositAmount = keyboardInput.nextInt();
        }
        userAccount.deposit(depositAmount);
    }

    public void run() {
=======
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

    public void run() {
        System.out.println("Welcome to the 2307 Bank App!");

>>>>>>> 0b0f5abacd5feb2344270eebb2ba8d66b72daa8f
        int selection = -1;
        while(selection != EXIT_SELECTION) {
            displayOptions();
            selection = getUserSelection(MAX_SELECTION);
            processInput(selection);
        }
<<<<<<< HEAD
=======
        System.out.println("Thank you for using the 2307 Bank App!");
>>>>>>> 0b0f5abacd5feb2344270eebb2ba8d66b72daa8f
    }

    public static void main(String[] args) {
        MainMenu bankApp = new MainMenu();
        bankApp.run();
    }

<<<<<<< HEAD
}
=======
}
>>>>>>> 0b0f5abacd5feb2344270eebb2ba8d66b72daa8f
