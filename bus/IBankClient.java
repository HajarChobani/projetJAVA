package bus;

import java.util.List;

public interface IBankClient {
	Integer getID();
	void setID(Integer id);
	
	String getFirstname();
	void setFirstname(String name);
	
	String getLastname();
	void setLastname(String name);
	
	String getPIN();
	void setPIN(String pin);
	
	String getAddress();
	void setAddress(String address);
	
	String getTelephoneNumber();
	void setTelephoneNumber(String number);
	
	String getEmail();
	void setEmail(String email);
	
	List<BankAccount> getBankAccounts();
	void setBankAccounts(List<BankAccount> list);
	void addBankAccount(BankAccount account);
	void removeBankAccount(BankAccount account);
	BankAccount getBankAccount(int accountID);
	
	void makeTransaction(Transaction transact, BankAccount... account);
	Double checkBalance(BankAccount account);
	List<Transaction> checkTransactionsHistory();
	List<Transaction> checkTransactionsHistory(BankAccount... accounts);
}
