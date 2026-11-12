# project26

# Team Members:

* Melissa Jiang
* Jaimely De Leon
* Zander Smutz-Ulmer
* Brianna Guerrero

## User stories

1. A bank customer should be able to deposit into an existing account. (Shook)
2. A bank customer should be able to withdraw from an account. 
3. A bank customer should be able to check their account balance for any account that they currently have. 
4. A bank customer should be able to view their transaction history for an account. 
5. A bank customer should be able to create an additional account with the bank. 
6. A bank customer should be able to close an existing account.
7. A bank customer should be able to transfer money from one account to another. 
8. A bank adminstrator should be able to collect fees from existing accounts when necessary.
9. A bank adminstrator should be able to add an interest payment to an existing account when necessary.

## What user stories do you intend to complete next iteration?
1. A bank customer should be able to log in with a username and password.
2. A bank customer should be able to export their transaction history to a file.
3. A bank customer should be able to set a low-balance alert amount for an account. 
4. A bank customer should be able to see a low-balance warning when their balance falls below their chosen alert amount.
5. A bank administrator should be able to add a new customer to the bank.
6. A bank administrator should be able to view all customers and their accounts.
7. A bank administrator should be able to freeze an existing customer account, preventing withdrawals and transfers.
8. A bank administrator should be able to unfreeze a frozen customer account, allowing normal account activity again.

## Is there anything that you implemented but doesn't currently work?
The transfer method cannot actually transfer anything to an outside account, as all we have implemented in this iteration is based on the current customer’s current accounts. 

## What commands are needed to compile and run your code from the command line?
javac src/main/*.java
java -cp src main.MainMenu
