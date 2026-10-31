package RMIServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class WelcomeImpl extends UnicastRemoteObject implements WelcomeInterface {

    private String message;

    public WelcomeImpl(String message) throws RemoteException {
        this.message = message;
    }

    public String sayWelcome() throws RemoteException {
        System.out.println("I am asked to say '" + message + "'");
        return message;
    }

}
