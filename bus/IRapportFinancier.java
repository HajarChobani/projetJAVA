package bus;

import java.util.Dictionary;
import java.util.List;
import java.util.function.Predicate;

public interface IRapportFinancier {
	void checkClientActivity();
	List<Transaction> checkTransactionDetails();
	List<Transaction> checkTransactionDetails(Predicate<Transaction> filter);
	
	Dictionary<BankAccount, Double> checkAcountsBanlances();
	Dictionary<BankAccount, Double> checkAcountsBanlances(Predicate<BankAccount> filter);
}
