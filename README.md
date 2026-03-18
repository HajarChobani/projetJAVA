# Application Bancaire en Java – Architecture DAO/DTO & Oracle

## Description

Ce projet consiste à développer une application bancaire en **Java** connectée à une base de données **Oracle** via **JDBC**.

L’application permet de gérer :
- les clients  
- les comptes bancaires  
- les transactions  
- les notifications  
- les gestionnaires de clients  

L’architecture repose sur le modèle **DAO/DTO**, garantissant une séparation claire entre :
- la logique métier  
- l’accès aux données  
- les objets de transfert  

---

## Objectifs du Projet

- Gérer plusieurs types de comptes :
  - Compte courant  
  - Compte épargne  
  - Compte crédit  
  - Compte en devise  

- Effectuer les opérations bancaires :
  - Dépôts  
  - Retraits  
  - Transferts  

- Assurer le suivi des transactions et des notifications  

- Implémenter une architecture modulaire et maintenable  

- Communiquer avec une base de données **Oracle via JDBC**

---

## Architecture Globale

### Organisation des Packages

| Package | Rôle |
|--------|------|
| `bus` | Logique métier (services, règles de gestion, opérations bancaires) |
| `data` | Accès aux données via JDBC (DAO) |
| `DTO` | Objets de transfert entre les couches |

> Les diagrammes UML (classes et architecture) sont disponibles dans le dépôt.

---

## Base de Données Oracle

| Table | Description |
|-------|-------------|
| `Client` | Informations sur les clients |
| `BankManager` | Gestionnaires associés aux clients |
| `BankAccount` | Classe parent des comptes |
| `CheckingAccount` | Compte courant |
| `SavingsAccount` | Compte épargne |
| `CreditAccount` | Compte de crédit |
| `CurrencyAccount` | Compte en devise |
| `Transaction` | Historique des opérations |
| `Notification` | Messages envoyés aux clients |

---

## Fonctionnalités Clés

| Fonction | Description |
|----------|-------------|
| Création de comptes | Selon le type (courant, épargne, crédit, devise) |
| Gestion des clients | Ajout, modification, recherche |
| Transactions | Dépôts, retraits, transferts |
| Notifications | Générées automatiquement |
| Historique | Suivi via `TransactionDAO` |
| Intérêts | Application automatique pour les comptes épargne |

---

## Exemple de Code

### Classe `Main`

```java
ClientDAO clientDAO = new ClientDAO();
List<ClientDTO> clients = clientDAO.getAllClients();

for (ClientDTO client : clients) {
    System.out.println(client.getFirstName() + " " + client.getLastName());
}
