package bus;

import java.time.LocalDate;

public class Deposit extends Transaction {

    private static final long serialVersionUID = -3624812258527696873L;

    public Deposit() {
    }

    public Deposit(double amount) {
        this.setAmount(amount);
    }

    @Override
    public void applyTo(BankAccount accounts) throws Exception {
        if (getAmount() <= 0) {
            throw new Exception(String.format("Invalid amount. Cannot deposit %.2f $ into account", getAmount()));
        }

        accounts.setBalance(accounts.getBalance() + getAmount());

        accounts.addTransactionToHistory(
            new TransactionRecord(
                accounts.getBalance(),
                this.getAmount(),
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
