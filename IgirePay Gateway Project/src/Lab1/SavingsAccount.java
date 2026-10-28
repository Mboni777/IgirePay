package Lab1;

import Lab2.PaymentException;

public class SavingsAccount extends Account {
    private static final double WITHDRAWAL_FEE = 200.0;

    public SavingsAccount(int id, int customerId, double balance, String pin) {
        super(id, customerId, "SAVINGS", balance, pin);
    }

    @Override
    public void withdraw(double amount) throws PaymentException {
        if (amount <= 0) throw new PaymentException("Amount must be positive.");
        double totalDeduction = amount + WITHDRAWAL_FEE;
        if (balance < totalDeduction) {
            throw new PaymentException("Insufficient balance to cover withdrawal and fee (Fee: RWF " + WITHDRAWAL_FEE + ").");
        }
        this.balance -= totalDeduction;
    }

    @Override
    public void deposit(double amount) throws PaymentException {
        if (amount <= 0) throw new PaymentException("Amount must be positive.");
        this.balance += amount;
    }
}
