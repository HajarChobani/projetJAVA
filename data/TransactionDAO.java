package data;

import DTO.TransactionDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {

    // Méthode pour récupérer toutes les transactions depuis la base de données
    public static List<TransactionDTO> getAllTransactions() {
        List<TransactionDTO> transactions = new ArrayList<>();
        String sql = "SELECT * FROM TRANSACTION";

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int transactionId = rs.getInt("transactionId");
                int accountId = rs.getInt("accountId");
                String type = rs.getString("type");
                double amount = rs.getDouble("amount");
                Date date = rs.getDate("transactionDate");

                transactions.add(new TransactionDTO(transactionId, accountId, type, amount, date.toLocalDate().atStartOfDay()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}
