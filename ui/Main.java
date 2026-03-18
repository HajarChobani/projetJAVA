package ui;

import bus.*;
import data.*;
import DTO.BankAccountDTO;
import DTO.TransactionDTO;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Création des comptes
        CheckingAccount checkingAccount = new CheckingAccount(500);
        CreditAccount creditAccount = new CreditAccount(2000, 0.05);
        CurrencyAccount currencyAccount = new CurrencyAccount(500, Currency.CAD);
        CompteEpargne compteEpargne = new CompteEpargne(3.5);

        System.out.println("Solde initial des comptes :");
        System.out.println("Compte Chèque : " + checkingAccount.getBalance());
        System.out.println("Compte Crédit : " + creditAccount.getBalance());
        System.out.println("Compte Devise : " + currencyAccount.getBalance());
        System.out.println("Compte Épargne : " + compteEpargne.getBalance());

        // Dépôt sur le compte chèque
        depositMoney(scanner, checkingAccount, "chèque");

        // Retrait du compte chèque
        withdrawMoney(scanner, checkingAccount, "chèque");

        // Dépôt sur le compte épargne
        depositMoney(scanner, compteEpargne, "épargne");

        // Application des intérêts
        System.out.println("\nApplication des intérêts sur le compte épargne...");
        compteEpargne.appliquerInterets();
        System.out.println("Nouveau solde du compte épargne après application des intérêts : " + compteEpargne.getBalance());

        // Retrait du compte de crédit
        withdrawMoney(scanner, creditAccount, "crédit");

        // Dépôt dans le compte devise
        depositMoney(scanner, currencyAccount, "devise (USD)");

        // Transfert entre comptes
        transferMoney(scanner, checkingAccount, compteEpargne);

        System.out.println("\nFin des transactions !");

        // Sauvegarde des comptes dans la base de données
        saveAccounts(checkingAccount, creditAccount, currencyAccount, compteEpargne);

        // Affichage des transactions
        displayTransactions();

        scanner.close();
    }

    private static void depositMoney(Scanner scanner, BankAccount account, String accountName) {
        while (true) {
            try {
                System.out.print("\nSaisir un montant à déposer dans le compte " + accountName + " : ");
                double montant = scanner.nextDouble();
                if (montant <= 0) throw new Exception("Le montant doit être positif.");

                Deposit deposit = new Deposit(montant);
                deposit.applyTo(account);
                System.out.println("Nouveau solde du compte " + accountName + " : " + account.getBalance());
                break;
            } catch (InputMismatchException e) {
                System.out.println("Erreur : Veuillez entrer un nombre valide.");
                scanner.next();
            } catch (Exception e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }
    }

    private static void withdrawMoney(Scanner scanner, BankAccount account, String accountName) {
        while (true) {
            try {
                System.out.print("\nSaisir un montant à retirer du compte " + accountName + " : ");
                double montant = scanner.nextDouble();

                Withdraw withdraw = new Withdraw(montant);
                withdraw.applyTo(account);
                System.out.println("Nouveau solde du compte " + accountName + " : " + account.getBalance());
                break;
            } catch (InputMismatchException e) {
                System.out.println("Erreur : Veuillez entrer un nombre valide.");
                scanner.next();
            } catch (Exception e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }
    }

    private static void transferMoney(Scanner scanner, BankAccount from, BankAccount to) {
        while (true) {
            try {
                System.out.print("\nSaisir un montant à transférer du compte chèque vers le compte épargne : ");
                double montant = scanner.nextDouble();
                if (montant <= 0) throw new Exception("Le montant doit être positif.");

                Transfer transfer = new Transfer(to);
                transfer.setAmount(montant);
                transfer.applyTo(from);

                System.out.println("Transfert réussi !");
                System.out.println("Nouveau solde du compte chèque : " + from.getBalance());
                System.out.println("Nouveau solde du compte épargne : " + to.getBalance());
                break;
            } catch (InputMismatchException e) {
                System.out.println("Erreur : Veuillez entrer un nombre valide.");
                scanner.next();
            } catch (Exception e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }
    }

    private static void saveAccounts(CheckingAccount checking, CreditAccount credit, CurrencyAccount currency, CompteEpargne epargne) {
        try {
            BankAccountDAO bankAccountDAO = new BankAccountDAO();

            // Sauvegarde des comptes dans la base de données
            bankAccountDAO.save(new BankAccountDTO("Chèque", checking.getBalance(), 1)); 
            bankAccountDAO.save(new BankAccountDTO("Crédit", credit.getBalance(), 1)); 
            bankAccountDAO.save(new BankAccountDTO("Devise", currency.getBalance(), 1)); 
            bankAccountDAO.save(new BankAccountDTO("Épargne", epargne.getBalance(), 1));

        } catch (Exception e) {
            System.out.println("Erreur lors de la sauvegarde des comptes : " + e.getMessage());
        }
    }

    private static void displayTransactions() {
        System.out.println("\nHistorique des transactions :");
        List<TransactionDTO> transactions = TransactionDAO.getAllTransactions();
        for (TransactionDTO transaction : transactions) {
            System.out.println(transaction);
        }
    }
}
