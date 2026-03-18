package bus;

public interface ISystem {
	
	// tirer du diagramme mais ca ne semble pas a sa place
	Double computeInterest(BankAccount account);	//
	void applyInterest(BankAccount account);		//
	void sendNotification();						//
	//////////////////////////////////////////////////
	
	void genererRapportFinancier();
}
