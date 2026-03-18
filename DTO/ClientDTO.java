package DTO;

public class ClientDTO {

    private int clientId;
    private String firstName;
    private String lastName;
    private String email;

    // Constructeur
    public ClientDTO(int clientId, String firstName, String lastName, String email) {
        this.clientId = clientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // Getters et Setters
    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ClientDTO{clientId=" + clientId + ", firstName='" + firstName + "', lastName='" + lastName + "', email='" + email + "'}";
    }
}
