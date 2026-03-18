CREATE USER c##hajar_chobani IDENTIFIED BY 1234;
GRANT CONNECT, RESOURCE TO c##hajar_chobani;
CONNECT c##hajar_chobani/1234;
-- Table Client
CREATE TABLE Client (
    clientId NUMBER PRIMARY KEY NOT NULL,
    firstName VARCHAR2(60) NOT NULL,
    lastName VARCHAR2(60) NOT NULL,
    ID VARCHAR2(4) NOT NULL,
    address VARCHAR2(100),
    email VARCHAR2(100),
    phoneNumber VARCHAR2(20)
);

-- Table BankManager
CREATE TABLE BankManager (
    managerId NUMBER PRIMARY KEY NOT NULL,
    firstName VARCHAR2(60) NOT NULL,
    lastName VARCHAR2(60) NOT NULL,
    clientId NUMBER NOT NULL,
    FOREIGN KEY (clientId) REFERENCES Client(clientId)
);

-- Table BankAccount (générique)
CREATE TABLE BankAccount (
    accountId NUMBER PRIMARY KEY NOT NULL,
    clientId NUMBER NOT NULL,
    balance NUMBER(18,2) DEFAULT 0,
    creation_date DATE DEFAULT SYSDATE,
    FOREIGN KEY (clientId) REFERENCES Client(clientId)
);

-- Table Transaction
CREATE TABLE Transaction (
    transactionId NUMBER PRIMARY KEY NOT NULL,
    accountId NUMBER NOT NULL,
    type VARCHAR2(30) NOT NULL,
    amount NUMBER(18,2) NOT NULL,
    transactionDate DATE DEFAULT SYSDATE,
    FOREIGN KEY (accountId) REFERENCES BankAccount(accountId)
);

-- Table Notification
CREATE TABLE Notification (
    notificationId NUMBER PRIMARY KEY NOT NULL,
    accountId NUMBER NOT NULL,
    clientId NUMBER NOT NULL,
    message VARCHAR2(255),
    status VARCHAR2(20),
    FOREIGN KEY (accountId) REFERENCES BankAccount(accountId),
    FOREIGN KEY (clientId) REFERENCES Client(clientId)
);

-- Table CheckingAccount (sous-type)
CREATE TABLE CheckingAccount (
    accountId NUMBER PRIMARY KEY NOT NULL,
    free_transactions NUMBER DEFAULT 2,
    FOREIGN KEY (accountId) REFERENCES BankAccount(accountId)
);

-- Table SavingsAccount (sous-type)
CREATE TABLE SavingsAccount (
    accountId NUMBER PRIMARY KEY NOT NULL,
    interestRate NUMBER(8,6),
    free_transactions NUMBER DEFAULT 2,
    FOREIGN KEY (accountId) REFERENCES BankAccount(accountId)
);

-- Table CreditAccount (sous-type)
CREATE TABLE CreditAccount (
    accountId NUMBER PRIMARY KEY NOT NULL,
    creditLimit NUMBER(18,2),
    interestRate NUMBER(8,6),
    FOREIGN KEY (accountId) REFERENCES BankAccount(accountId)
);

-- Table CurrencyAccount (sous-type)
CREATE TABLE CurrencyAccount (
    accountId NUMBER PRIMARY KEY NOT NULL,
    currency VARCHAR2(10),
    exchangeRate NUMBER(10,4),
    FOREIGN KEY (accountId) REFERENCES BankAccount(accountId)
);
-- Insertion de clients
INSERT INTO Client (clientId, firstName, lastName, ID, address, email, phoneNumber) VALUES
(1, 'Hajar', 'Chobani', 'ID01', '123 rue des Fleurs', 'hajar@gmail.com', '0611223344'),
(2, 'Amine', 'Zahiri', 'ID02', '456 avenue Atlas', 'amine@gmail.com', '0622334455');

-- Insertion de managers
INSERT INTO BankManager (managerId, firstName, lastName, clientId) VALUES
(1, 'Sara', 'Benali', 1),
(2, 'Youssef', 'Mouline', 2);

-- Insertion de comptes bancaires
INSERT INTO BankAccount (accountId, clientId, balance) VALUES
(101, 1, 500.00),
(102, 1, 1000.00),
(103, 2, 750.00),
(104, 2, 2000.00);

-- Insertion dans CheckingAccount
INSERT INTO CheckingAccount (accountId, free_transactions) VALUES
(102, 2);

-- Insertion dans SavingsAccount
INSERT INTO SavingsAccount (accountId, interestRate, free_transactions) VALUES
(101, 1.5, 2);

-- Insertion dans CreditAccount
INSERT INTO CreditAccount (accountId, creditLimit, interestRate) VALUES
(104, 5000.00, 3.5);

-- Insertion dans CurrencyAccount
INSERT INTO CurrencyAccount (accountId, currency, exchangeRate) VALUES
(103, 'USD', 1.1200);

-- Insertion de transactions
INSERT INTO Transaction (transactionId, accountId, type, amount, transactionDate) VALUES
(1, 101, 'Deposit', 200.00, TO_DATE('2025-04-01', 'YYYY-MM-DD')),
(2, 102, 'Withdrawal', 150.00, TO_DATE('2025-04-02', 'YYYY-MM-DD')),
(3, 103, 'Transfer', 300.00, TO_DATE('2025-04-03', 'YYYY-MM-DD')),
(4, 104, 'Purchase', 1000.00, TO_DATE('2025-04-04', 'YYYY-MM-DD'));

-- Insertion de notifications
INSERT INTO Notification (notificationId, accountId, clientId, message, status) VALUES
(1, 101, 1, 'Votre dépôt a été effectué avec succès.', 'envoyée'),
(2, 102, 1, 'Retrait de 150 MAD effectué.', 'vue'),
(3, 104, 2, 'Achat confirmé.', 'envoyée');
