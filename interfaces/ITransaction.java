package interfaces;

import java.util.Date;

public interface ITransaction {
    void effectuerTransaction();
    void envoyerNotification();
    double getMontant();
    Date getDate();
}
