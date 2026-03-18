package DTO;

import java.time.LocalDateTime;

public class TransactionDTO {
    private int transactionId;
    private int accountId;
    private String type;
    private double amount;
    private LocalDateTime transactionDate;

    // Constructeur
    public TransactionDTO(int transactionId, int accountId, String type, double amount, LocalDateTime transactionDate) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.type = type;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    // Getters et Setters
    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    // Méthode toString() pour afficher les informations de la transaction
    @Override
    public String toString() {
        return "TransactionDTO [transactionId=" + transactionId + ", accountId=" + accountId + ", type=" + type + ", amount=" + amount + ", transactionDate=" + transactionDate + "]";
    }
}
