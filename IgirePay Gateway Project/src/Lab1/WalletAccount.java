package Lab1;

import Lab2.PaymentException;

public class WalletAccount extends Account {
    public WalletAccount(int id, int customerId, double balance, String pin) {
        super(id, customerId, "WALLET", balance, pin);
    }

    @Override
    public void withdraw(double amount) throws PaymentException {
        if (amount <= 0) throw new PaymentException("Amount must be positive.");
        if (balance < amount) throw new PaymentException("Insufficient balance in Wallet.");
        this.balance -= amount;
    }

    @Override
    public void deposit(double amount) throws PaymentException {
        if (amount <= 0) throw new PaymentException("Amount must be positive.");
        this.balance += amount;
    }
}
