package data;

import DTO.BankManagerDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankManagerDAO {

    // Récupérer tous les responsables bancaires depuis la base de données
    public List<BankManagerDTO> getAllManagers() {
        List<BankManagerDTO> managers = new ArrayList<>();
        String sql = "SELECT * FROM BANKMANAGER";

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int managerId = rs.getInt("MANAGERID");
                String firstName = rs.getString("FIRSTNAME");
                String lastName = rs.getString("LASTNAME");
                int clientId = rs.getInt("CLIENTID");

                managers.add(new BankManagerDTO(managerId, firstName, lastName, clientId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return managers;
    }

    // Sauvegarder un responsable bancaire dans la base de données
    public void save(BankManagerDTO manager) throws SQLException {
        String sql = "INSERT INTO BANKMANAGER (MANAGERID, FIRSTNAME, LASTNAME, CLIENTID) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, manager.getManagerId());   
            stmt.setString(2, manager.getFirstName());
            stmt.setString(3, manager.getLastName());
            stmt.setInt(4, manager.getClientId());    

            stmt.executeUpdate();
            System.out.println("Responsable bancaire sauvegardé avec succès.");
        }
    }
}
