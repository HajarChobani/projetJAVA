package DTO;

public class NotificationDTO {
    private int notificationId;
    private int accountId;
    private int clientId;
    private String message;
    private String status;

    // Constructeur
    public NotificationDTO(int notificationId, int accountId, int clientId, String message, String status) {
        this.notificationId = notificationId;
        this.accountId = accountId;
        this.clientId = clientId;
        this.message = message;
        this.status = status;
    }

    // Getters et Setters
    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Méthode toString() pour afficher les informations de la notification
    @Override
    public String toString() {
        return "NotificationDTO [notificationId=" + notificationId + ", accountId=" + accountId + ", clientId=" + clientId + ", message=" + message + ", status=" + status + "]";
    }
}
