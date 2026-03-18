package data;

import DTO.BankAccountDTO;
import java.sql.*;

public class CheckingAccountDAO {
    public void save(BankAccountDTO account) throws SQLException {
        String sql = "INSERT INTO CHECKINGACCOUNT (type, balance) VALUES (?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "Chèque");
            stmt.setDouble(2, account.getBalance());

            stmt.executeUpdate();
            System.out.println("Compte chèque sauvegardé.");
        }
    }
}
