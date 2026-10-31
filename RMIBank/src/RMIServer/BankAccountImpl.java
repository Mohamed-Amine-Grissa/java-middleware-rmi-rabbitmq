package RMIServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class BankAccountImpl extends UnicastRemoteObject implements BankAccountInterface {

    private final HashMap<Integer, Double> accounts = new HashMap<Integer, Double>();

    public BankAccountImpl() throws RemoteException {
        super();
        accounts.put(1001, 500.0);
        accounts.put(1002, 1200.0);
        accounts.put(1003, 0.0);
    }

    public synchronized String retrait(int iban, double montant) throws RemoteException {
        if (!accounts.containsKey(iban)) {
            return "Error: unknown IBAN " + iban + ".";
        }
        if (montant <= 0 || Double.isNaN(montant) || Double.isInfinite(montant)) {
            return "Error: amount must be positive.";
        }
        double balance = accounts.get(iban);
        if (montant > balance) {
            return "Error: insufficient funds. Balance is " + balance + ", requested " + montant + ".";
        }
        accounts.put(iban, balance - montant);
        System.out.println("Withdrawal of " + montant + " on " + iban);
        return "Withdrawal of " + montant + " done. New balance: " + (balance - montant);
    }

    public synchronized String depot(int iban, double montant) throws RemoteException {
        if (!accounts.containsKey(iban)) {
            return "Error: unknown IBAN " + iban + ".";
        }
        if (montant <= 0 || Double.isNaN(montant) || Double.isInfinite(montant)) {
            return "Error: amount must be positive.";
        }
        double balance = accounts.get(iban);
        accounts.put(iban, balance + montant);
        System.out.println("Deposit of " + montant + " on " + iban);
        return "Deposit of " + montant + " done. New balance: " + (balance + montant);
    }

    public synchronized double consultation(int iban) throws RemoteException {
        Double balance = accounts.get(iban);
        if (balance == null) {
            return -1;
        }
        System.out.println("Balance requested on " + iban);
        return balance;
    }

}
