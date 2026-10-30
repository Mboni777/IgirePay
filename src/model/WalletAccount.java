package model;

public class WalletAccount extends Account {

    public WalletAccount() {
        super();
    }

    public WalletAccount(int id, int customerId, double balance, String pin) {
        super(id, customerId, "WALLET", balance, pin);
    }

    @Override
    public void deposit(double amount) {
        super.deposit(amount);
        System.out.println("Wallet deposit successful: " + amount);
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= getBalance()) {
            super.withdraw(amount);
            System.out.println("Wallet withdrawal successful: " + amount);
        } else {
            System.out.println("Insufficient wallet balance.");
        }
    }

    public void processTransaction() {
        System.out.println("Processing instant wallet transaction...");
    }
}