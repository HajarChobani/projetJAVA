package bus;

import java.time.LocalDate;

public class CheckingAccount extends BankAccount {

    private static final long serialVersionUID = 1L;

    // Nombre de transactions gratuites restantes
    private int transactionsGratuites;
    
    // Limite de transactions gratuites par mois
    private static final int LIMIT_TRANSACTIONS_GRATUITES = 2;
    
    // Frais supplémentaires appliqués après la limite de transactions gratuites
    private static final double FRAIS_TRANSACTION_SUPPLEMENTAIRE = 5.0;

    
    public CheckingAccount(Integer ID) {
        super();
        this.transactionsGratuites = 0;
    }

    // Méthode pour effectuer une transaction
    @Override
    public void doTransaction(Transaction transaction) throws Exception {
        transaction.applyTo(this);  // Applique la transaction au compte courant
    }

    // Méthode pour effectuer un dépôt sur le compte
    @Override
    public void deposit(Double amount) throws Exception {
        // Vérifie que le montant est positif
        if (amount <= 0) {
            throw new Exception("Le montant doit être positif pour un dépôt.");
        }

        // Crée la transaction de dépôt
        Transaction depositTransaction = new Deposit();
        depositTransaction.setAmount(amount);  // Définit le montant de la transaction
        depositTransaction.applyTo(this);  // Applique le dépôt au compte

        // Enregistre la transaction dans l'historique
        TransactionRecord transaction = new TransactionRecord(getBalance(), amount, LocalDate.now());
        addTransactionToHistory(transaction);
    }

    // Méthode pour effectuer un retrait du compte
    @Override
    public void withdraw(Double amount) throws Exception {
        // Vérifie que le montant est positif
        if (amount <= 0) {
            throw new Exception("Le montant du retrait doit être positif.");
        }
        // Vérifie que le solde est suffisant pour effectuer le retrait
        if (getBalance() < amount) {
            throw new Exception("Fonds insuffisants pour le retrait.");
        }

        // Crée la transaction de retrait
        Transaction withdrawTransaction = new Withdraw();
        withdrawTransaction.setAmount(amount);  // Définit le montant de la transaction

        // Si le nombre de transactions gratuites est inférieur à la limite, on applique un retrait normal
        if (transactionsGratuites < LIMIT_TRANSACTIONS_GRATUITES) {
            withdrawTransaction.applyTo(this);  // Applique le retrait
            transactionsGratuites++;  // Incrémente le nombre de transactions gratuites utilisées
        } else {
            // Si la limite est atteinte, on applique des frais supplémentaires
            withdrawTransaction.setAmount(amount + FRAIS_TRANSACTION_SUPPLEMENTAIRE);
            withdrawTransaction.applyTo(this);  // Applique le retrait avec les frais supplémentaires
        }
    }

    // Méthode pour effectuer un virement d'un compte à un autre
    @Override
    public void transfer(BankAccount destination, Double amount) throws Exception {
        // Vérifie que le montant du virement est positif
        if (amount <= 0) {
            throw new Exception("Le montant du virement doit être positif.");
        }
        // Vérifie que le solde est suffisant pour effectuer le virement
        if (getBalance() < amount) {
            throw new Exception("Fonds insuffisants pour le virement.");
        }

        // Effectue un retrait du compte source
        withdraw(amount);
        
        // Effectue un dépôt sur le compte destination
        destination.deposit(amount);
    }

    public double getAccountType() {
		// TODO Auto-generated method stub
		return 0;
	}
}
