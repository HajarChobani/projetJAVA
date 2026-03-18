package interfaces;

public interface ICompte {
    void deposer(double montant);
    boolean retirer(double montant);
    boolean virement(double montant, ICompte compteDestinataire);
    double getSolde();
}
