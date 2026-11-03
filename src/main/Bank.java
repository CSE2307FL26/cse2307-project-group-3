public boolean closeAccount(String accountNuumber) {
    BankAccount account = accounts.get(accountNuumber);

    if (account == null) {
        System.out.print("Invalid account number.");
        return false;
    }

    if (account.getBalance() != 0) {
        System.out.print("Account balance must be zero to close the account.");
        return false;
    }

    accounts.remove(accountNuumber);
    System.out.print("Account closed successfully.")
    return true;
}