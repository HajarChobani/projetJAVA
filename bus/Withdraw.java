package bus;

import java.time.LocalDate;

public class Withdraw extends Transaction {

    private static final long serialVersionUID = -4520018792360739988L;

    public Withdraw() {
    }

    public Withdraw(double amount) {
        this.setAmount(amount);
    }

    @Override
    public void applyTo(BankAccount accounts) throws Exception {
        if (getAmount() <= 0) {
            throw new Exception(String.format("Invalid amount. Cannot withdraw %.2f $ from account", getAmount()));
        }

        // Si c'est un CreditAccount, on vérifie la limite de crédit
        if (accounts instanceof CreditAccount) {
            CreditAccount creditAcc = (CreditAccount) accounts;
            if (creditAcc.getBalance() - getAmount() < -creditAcc.getCreditLimit()) {
                throw new Exception("Dépassement de la limite de crédit !");
            }
        } else {
            // Si c'est un autre type de compte, vérifier le solde habituel
            if (accounts.getBalance() < getAmount()) {
                throw new Exception("Insufficient funds to complete the withdrawal.");
            }
        }

        // Appliquer le retrait
        accounts.setBalance(accounts.getBalance() - getAmount());

        // Enregistrer la transaction
        accounts.addTransactionToHistory(
            new TransactionRecord(
                accounts.getBalance(),
                -this.getAmount(),
                LocalDate.now()
            )
        );
    }

    @Override
    public void sendNotification() {
        
    }

	@Override
	protected void updateBalance(BankAccount account) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
