package data;

import DTO.BankAccountDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankAccountDAO {

    // Méthode pour récupérer tous les comptes bancaires depuis la base de données
    public static List<BankAccountDTO> getAllBankAccounts() {
        List<BankAccountDTO> bankAccounts = new ArrayList<>();  // Liste de BankAccountDTO
        String sql = "SELECT * FROM BANKACCOUNT";  // La requête pour récupérer tous les comptes bancaires

        try (Connection conn = ConnectionFactory.getConnection();  // Créer la connexion à la base de données
             Statement stmt = conn.createStatement();  // Créer un objet Statement pour exécuter la requête
             ResultSet rs = stmt.executeQuery(sql)) {  // Exécuter la requête et récupérer les résultats

            // Parcourir les résultats ligne par ligne
            while (rs.next()) {
                int accountId = rs.getInt("accountId");  // Récupérer l'ID du compte bancaire
                int clientId = rs.getInt("clientId");  // Récupérer l'ID du client
                double balance = rs.getDouble("balance");  // Récupérer le solde du compte

                // Créer un objet BankAccountDTO et l'ajouter à la liste
                bankAccounts.add(new BankAccountDTO(accountId, clientId, balance));
            }
        } catch (SQLException e) {
            e.printStackTrace();  // En cas d'erreur, afficher l'exception
        }
        return bankAccounts;  // Retourner la liste des comptes bancaires
    }

    // Méthode pour sauvegarder un compte bancaire dans la base de données
    public void save(BankAccountDTO account) throws SQLException {
        String sql = "INSERT INTO BANKACCOUNT (accountId, clientId, balance, creation_date) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, getNextAccountId(conn));  // Obtenir l'ID suivant de la séquence
            stmt.setInt(2, account.getClientId());  // ID du client
            stmt.setDouble(3, account.getBalance());  // Solde du compte
            stmt.setDate(4, new java.sql.Date(System.currentTimeMillis()));  // Date de création du compte

            stmt.executeUpdate();  // Exécuter la requête
            System.out.println("Compte sauvegardé dans la base de données.");
        }
    }

    // Méthode pour obtenir le prochain ID de compte en utilisant une séquence
    private int getNextAccountId(Connection conn) throws SQLException {
        String sql = "SELECT BANKACCOUNT_SEQ.NEXTVAL FROM DUAL";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);  // Retourner la prochaine valeur de la séquence
            } else {
                throw new SQLException("Impossible de récupérer le prochain ID de compte.");
            }
        }
    }
}
