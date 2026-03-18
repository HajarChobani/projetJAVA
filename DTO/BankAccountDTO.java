package DTO;

import java.time.LocalDate;

public class BankAccountDTO {
    private int accountId;
    private int clientId;
    private double balance;
    private LocalDate creationDate;
    private String accountName;  // Nom du compte (par exemple, "Chèque", "Crédit", etc.)

    // Constructeur (accountName, balance, clientId, accountId et creationDate)
    public BankAccountDTO(String accountName, double balance, int clientId) {
        this.accountName = accountName;
        this.balance = balance;
        this.clientId = clientId;
        this.accountId = 0;  // L'ID du compte peut être auto-généré par la base de données
        this.creationDate = LocalDate.now();  // Initialisation de la date de création à la date actuelle
    }

    // Constructeur (accountId, clientId, balance)
    public BankAccountDTO(int accountId, int clientId, double balance) {
        this.accountId = accountId;
        this.clientId = clientId;
        this.balance = balance;
        this.creationDate = LocalDate.now();  // Par défaut, on initialise à la date actuelle
    }

    // Getter et Setter pour chaque champ
    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "BankAccountDTO{" +
                "accountId=" + accountId +
                ", clientId=" + clientId +
                ", balance=" + balance +
                ", creationDate=" + creationDate +
                ", accountName='" + accountName + '\'' +
                '}';
    }
}
