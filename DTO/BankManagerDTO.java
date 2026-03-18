package DTO;

public class BankManagerDTO {
    private int managerId;
    private String firstName;
    private String lastName;
    private int clientId;

    // Constructeur
    public BankManagerDTO(int managerId, String firstName, String lastName, int clientId) {
        this.managerId = managerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.clientId = clientId;
    }

    // Getters et Setters
    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    // Méthode toString() pour afficher les informations du manager
    @Override
    public String toString() {
        return "Manager [ID=" + managerId + ", Name=" + firstName + " " + lastName + ", ClientID=" + clientId + "]";
    }
}
