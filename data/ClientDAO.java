package data;

import DTO.ClientDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {

    // Méthode pour récupérer tous les clients depuis la base de données
    public static List<ClientDTO> getAllClients() {
        List<ClientDTO> clients = new ArrayList<>();
        String sql = "SELECT * FROM CLIENT";  // La requête SQL pour récupérer tous les clients

        try (Connection conn = ConnectionFactory.getConnection();  // Obtenir une connexion avec la base de données
             Statement stmt = conn.createStatement();  // Créer un objet Statement pour exécuter la requête
             ResultSet rs = stmt.executeQuery(sql)) {  // Exécuter la requête et récupérer les résultats

            // Parcourir les résultats ligne par ligne
            while (rs.next()) {
                
                int clientId = rs.getInt("CLIENTID");  
                String firstName = rs.getString("FIRSTNAME");  
                String lastName = rs.getString("LASTNAME");  
                String email = rs.getString("EMAIL");  

                // Ajouter un objet ClientDTO à la liste
                clients.add(new ClientDTO(clientId, firstName, lastName, email));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des clients : " + e.getMessage());
            e.printStackTrace();  // En cas d'erreur, afficher l'exception
        }
        return clients;  // Retourner la liste des clients
    }
}
