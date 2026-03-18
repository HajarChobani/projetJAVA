package bus;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Transaction implements Serializable {

 private static final long serialVersionUID = 2923352023714175172L;
 
 private Integer ID;
 private double amount;

 public Transaction() {
  // TODO Auto-generated constructor stub
 }

 public Integer getID() {
  return ID;
 }

 public void setID(Integer iD) {
  ID = iD;
 }

 public double getAmount() {
  return amount;
 }

 public void setAmount(double amount) {
  this.amount = amount;
 }
 
 protected abstract void updateBalance(BankAccount account) throws Exception;
 
 public void applyTo(BankAccount account) throws Exception {
  if (getAmount() <= 0) {
   throw new Exception("Invalide amount. Cannot deposit %f $ into account".formatted(getAmount()));
  }
  var oldBalance = account.getBalance();
  updateBalance(account);
  
  account.addTransactionToHistory(
    new TransactionRecord(
      account.getBalance(), 
      account.getBalance() - oldBalance, 
      LocalDate.now()
      ));
  
 }
 public abstract void sendNotification();

}
 