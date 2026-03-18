# Application Bancaire en Java – Architecture DAO/DTO & Oracle

## Description

Ce projet est une application bancaire développée en **Java**, connectée à une base de données **Oracle** via **JDBC**.

Elle permet de gérer :
- les clients  
- les comptes bancaires  
- les transactions  
- les notifications  
- les gestionnaires  

L’architecture est basée sur le modèle **DAO/DTO**, garantissant une bonne séparation entre :
- la logique métier  
- l’accès aux données  
- les objets de transfert  

---

## Objectifs

- Gérer différents types de comptes :
  - Compte courant  
  - Compte épargne  
  - Compte crédit  
  - Compte en devise  

- Réaliser les opérations bancaires :
  - Dépôt  
  - Retrait  
  - Transfert  

- Assurer le suivi des transactions et notifications  

- Concevoir une architecture modulaire et maintenable  

- Se connecter à Oracle via JDBC  

---

## Architecture du Projet

### Packages

| Package | Rôle |
|--------|------|
| `bus` | Logique métier |
| `data` | Accès aux données (DAO) |
| `DTO` | Objets de transfert |
| `interfaces` | Interfaces des services |
| `ui` | Interface utilisateur |

---

## Base de Données

| Table | Description |
|-------|-------------|
| `Client` | Informations clients |
| `BankManager` | Gestionnaires |
| `BankAccount` | Compte parent |
| `CheckingAccount` | Compte courant |
| `SavingsAccount` | Compte épargne |
| `CreditAccount` | Compte crédit |
| `CurrencyAccount` | Compte devise |
| `Transaction` | Historique |
| `Notification` | Notifications |

📄 Le script SQL est disponible dans le fichier `Account.sql`.

---

## Diagrammes UML

Les diagrammes UML du projet sont disponibles dans le dossier `Diagramme` :
- Diagramme de classes  
- Diagramme d’architecture  

---

## Fonctionnalités

- Création de comptes bancaires  
- Gestion des clients (ajout, modification, recherche)  
- Dépôts, retraits et transferts  
- Historique des transactions  
- Génération automatique de notifications  
- Calcul des intérêts pour les comptes épargne  

---

## Exemple d’Utilisation

```java
ClientDAO clientDAO = new ClientDAO();
List<ClientDTO> clients = clientDAO.getAllClients();

for (ClientDTO client : clients) {
    System.out.println(client.getFirstName() + " " + client.getLastName());
}
