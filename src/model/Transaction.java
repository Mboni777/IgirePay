package model;

import java.time.LocalDateTime;

public class Transaction {

    private int id;
    private int accountId;
    private String referenceId;
    private double amount;
    private String transactionType;
    private LocalDateTime timestamp;

    public Transaction() {
    }

    public Transaction(
            int id,
            int accountId,
            String referenceId,
            double amount,
            String transactionType,
            LocalDateTime timestamp) {

        this.id = id;
        this.accountId = accountId;
        this.referenceId = referenceId;
        this.amount = amount;
        this.transactionType = transactionType;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", accountId=" + accountId +
                ", referenceId='" + referenceId + '\'' +
                ", amount=" + amount +
                ", transactionType='" + transactionType + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}