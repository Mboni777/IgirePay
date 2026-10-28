package Lab1;

import java.sql.Timestamp;

public class Transaction {
    private int id;
    private int accountId;
    private String referenceId;
    private String transactionType;
    private double amount;
    private Timestamp createdAt;

    public Transaction(int id, int accountId, String referenceId, String transactionType, double amount, Timestamp createdAt) {
        this.id = id;
        this.accountId = accountId;
        this.referenceId = referenceId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    public int getId() { return id; }
    public int getAccountId() { return accountId; }
    public String getReferenceId() { return referenceId; }
    public String getTransactionType() { return transactionType; }
    public double getAmount() { return amount; }
    public Timestamp getCreatedAt() { return createdAt; }

    @Override
    public String toString() {
        return "Transaction [ID=" + id + ", Ref=" + referenceId + ", Type=" + transactionType + ", Amount=" + amount + ", Date=" + createdAt + "]";
    }
}
