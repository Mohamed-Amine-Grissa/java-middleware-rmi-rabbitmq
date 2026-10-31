package RMIServer;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BankAccountInterface extends Remote {

    public String retrait(int iban, double montant) throws RemoteException;

    public String depot(int iban, double montant) throws RemoteException;

    public double consultation(int iban) throws RemoteException;

}
