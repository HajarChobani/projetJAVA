package bus;

public class Transfer extends Transaction {

    private static final long serialVersionUID = 8994320345258149975L;

    private BankAccount destinationAccount;

    public Transfer(BankAccount destination) {
        this.destinationAccount = destination;
    }

    @Override
    protected void updateBalance(BankAccount sourceAccount) throws Exception {
        if (sourceAccount == destinationAccount) {
            throw new Exception("Le compte source et destination doivent être différents.");
        }

        double amount = getAmount();

        // Vérification du montant
        if (amount <= 0) {
            throw new Exception("Le montant du transfert doit être positif.");
        }

        // Retirer du compte source
        sourceAccount.withdraw(amount);

        // Déposer dans le compte destination
        destinationAccount.deposit(amount);
    }

    @Override
    public void sendNotification() {
        System.out.println("Transfert de " + getAmount() + " $ effectué avec succès !");
    }
}
