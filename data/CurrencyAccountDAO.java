package data;

import DTO.BankAccountDTO;
import java.sql.*;

public class CurrencyAccountDAO {
    public void save(BankAccountDTO account) throws SQLException {
        String sql = "INSERT INTO CURRENCYCCOUNT (type, balance) VALUES (?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "Devise");
            stmt.setDouble(2, account.getBalance());

            stmt.executeUpdate();
            System.out.println("Compte devise sauvegardé.");
        }
    }
}
