package RMIServer;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface WelcomeInterface extends Remote {

    public String sayWelcome() throws RemoteException;

}
