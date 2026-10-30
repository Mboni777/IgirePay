package model;

public class SavingsAccount extends Account {

    private static final double WITHDRAWAL_FEE = 100.0;

    public SavingsAccount() {
        super();
    }

    public SavingsAccount(int id, int customerId, double balance, String pin) {
        super(id, customerId, "SAVINGS", balance, pin);
    }

    @Override
    public void deposit(double amount) {
        super.deposit(amount);
        System.out.println("Savings deposit successful: " + amount);
    }

    @Override
    public void withdraw(double amount) {

        double total = amount + WITHDRAWAL_FEE;

        if (total <= getBalance()) {
            super.withdraw(total);
            System.out.println(
                "Savings withdrawal successful. Fee applied: "
                        + WITHDRAWAL_FEE
            );
        } else {
            System.out.println("Insufficient savings balance.");
        }
    }

    public void processTransaction() {
        System.out.println("Processing savings transaction...");
    }
}