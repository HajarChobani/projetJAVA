package bus;

public class CreditAccount extends BankAccount {
    private static final long serialVersionUID = 1L;
    private double creditLimit;
    private double interestRate;

    public CreditAccount(double creditLimit, double interestRate) {
        super();
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
    }
    public double getCreditLimit() {
        return creditLimit;
    }

    public CreditAccount(int i, int j, double d) {
		// TODO Auto-generated constructor stub
	}

	public void applyInterest() {
        if (getBalance() < 0) {
            double interest = Math.abs(getBalance()) * interestRate;
            setBalance(getBalance() - interest);

            try {
                getTransactionsHistory().add(new TransactionRecord(getBalance(), interest, java.time.LocalDate.now()));
            } catch (Exception e) {
                System.out.println("Erreur lors de l'application des intérêts : " + e.getMessage());
            }
        }
    }

    @Override
    public void deposit(Double amount) throws Exception {
        setBalance(getBalance() + amount);
        getTransactionsHistory().add(new TransactionRecord(getBalance(), amount, java.time.LocalDate.now()));
    }

    @Override
    public void withdraw(Double amount) throws Exception {
        if (amount <= 0)
            throw new Exception("Le montant du retrait doit être positif !");

        if (getBalance() - amount < -creditLimit)
            throw new Exception("Dépassement de la limite de crédit !");

        setBalance(getBalance() - amount);

        getTransactionsHistory().add(new TransactionRecord(getBalance(), -amount, java.time.LocalDate.now()));
    }

    @Override
    public void doTransaction(Transaction transaction) throws Exception {
        transaction.applyTo(this);
        // Ajoute la transaction à l'historique après l'avoir appliquée
        getTransactionsHistory().add(new TransactionRecord(getBalance(), transaction.getAmount(), java.time.LocalDate.now()));
    }
	public double getAccountType() {
		// TODO Auto-generated method stub
		return 0;
	}
}
