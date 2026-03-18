package data;

import DTO.NotificationDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAO {

    // Méthode pour récupérer toutes les notifications depuis la base de données
    public static List<NotificationDTO> getAllNotifications() {
        List<NotificationDTO> notifications = new ArrayList<>();
        String sql = "SELECT * FROM NOTIFICATION";  // La requête SQL pour récupérer toutes les notifications

        try (Connection conn = ConnectionFactory.getConnection();  // Obtenir une connexion avec la base de données
             Statement stmt = conn.createStatement();  // Créer un objet Statement pour exécuter la requête
             ResultSet rs = stmt.executeQuery(sql)) {  // Exécuter la requête et récupérer les résultats

            // Parcourir les résultats ligne par ligne
            while (rs.next()) {
                int notificationId = rs.getInt("NOTIFICATIONID"); 
                int accountId = rs.getInt("ACCOUNTID"); 
                int clientId = rs.getInt("CLIENTID"); 
                String message = rs.getString("MESSAGE");  
                String status = rs.getString("STATUS");  

                // Ajouter un objet NotificationDTO à la liste
                notifications.add(new NotificationDTO(notificationId, accountId, clientId, message, status));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des notifications : " + e.getMessage());
            e.printStackTrace();  // En cas d'erreur, afficher l'exception
        }
        return notifications;  // Retourner la liste des notifications
    }
}
