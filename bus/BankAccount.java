package bus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public abstract class BankAccount implements Serializable {

 private static final long serialVersionUID = 3753515648420331637L;
 
 private Integer ID;
 private double balance;
 private List<TransactionRecord> transactionsHistory;

 public BankAccount() {
  setBalance(0.0);
  setTransactionsHistory(
    new ArrayList<TransactionRecord>()
    );
 }

 public Integer getID() {
  return ID;
 }
 public void setID(Integer id) {
  ID = id;
 }

 public double getBalance() {
  return balance;
 }
 public void setBalance(double d) {
  this.balance = d;
 }
 

 public List<TransactionRecord> getTransactionsHistory() {
  return transactionsHistory;
 }
 public void setTransactionsHistory(List<TransactionRecord> transactionsHistory) {
  this.transactionsHistory = transactionsHistory;
 }
 public List<TransactionRecord> getSortedTransaction(Comparator<TransactionRecord> comparator) {
  transactionsHistory.sort(comparator);
  return transactionsHistory;
 }

 public void doTransaction(Transaction transaction) throws Exception {
  transaction.applyTo(this);
 }
 
 public void addTransactionToHistory(TransactionRecord transaction) {
  this.transactionsHistory.add(transaction);
 }
 
 public void deposit(Double amount) 
   throws Exception {
  var transaction = new Deposit();
  transaction.setAmount(amount);
  doTransaction(transaction);
 }
 public void withdraw(Double amount) 
   throws Exception {
  Transaction transaction = new Withdraw(); // TODO: assign new withdraw instance
  transaction.setAmount(amount);
  doTransaction(transaction);
  
 }
 public void transfer(BankAccount destination, Double amount) 
   throws Exception {
  Transaction transaction = new Transfer(destination);
  transaction.setAmount(amount);
  doTransaction(transaction);
 }
}
 