package bus;

 
public class CompteEpargne extends BankAccount {
 
	private static final  long serialVersionUID = 1L;
 
	private double tauxInteret;
 
 
	public CompteEpargne(double tauxInteret)
	{
		super();
		this.tauxInteret=tauxInteret;
	}
 
	public CompteEpargne(int i, double d) {
		// TODO Auto-generated constructor stub
	}

	//getter
	public double getTauxInteret()
	{
		return tauxInteret;
	}
 
	//setter
	public void setTauxInteret(double tauxInteret )
	{
		this.tauxInteret=tauxInteret;
	}
 
 
 
    //methode pour calcluer les interets
    public void appliquerInterets() {
        Double interets = getBalance() * (tauxInteret / 100);
        setBalance(getBalance() + interets);
        System.out.println(" Intérêts appliqués : " + interets);
    }
 
    @Override
    public String toString() {
        return "CompteEpargne [ID=" + getID() + ", Solde=" + getBalance() + 
               ", Taux d'intérêt=" + tauxInteret + "%]";
    }

	public double getAccountType() {
		// TODO Auto-generated method stub
		return 0;
	}

	

	

 
 
 
}

 
