package Lab1;

import Lab2.PaymentException;

public abstract class Account {
    private int id;
    private int customerId;
    private String accountType;
    protected double balance;
    private String pin;

    public Account(int id, int customerId, String accountType, double balance, String pin) {
        this.id = id;
        this.customerId = customerId;
        this.accountType = accountType;
        this.balance = balance;
        this.pin = pin;
    }

    public int getId() { return id; }
    public int getCustomerId() { return customerId; }
    public String getAccountType() { return accountType; }
    public double getBalance() { return balance; }
    public String getPin() { return pin; }
    public void setPin(String pin) { this.pin = pin; }

    public abstract void withdraw(double amount) throws PaymentException;
    public abstract void deposit(double amount) throws PaymentException;

    @Override
    public String toString() {
        return "Account [ID=" + id + ", Type=" + accountType + ", Balance=" + balance + "]";
    }
}
