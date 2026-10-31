package RMIServer;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class WelcomeServer {

    public static void main(String[] args) {
        try {
            WelcomeImpl rmiObj = new WelcomeImpl("Welcome to everyone!");

            Registry registry = LocateRegistry.createRegistry(1233);
            Naming.rebind("rmi://localhost:1233/Welcome", rmiObj);

            System.out.println("RMI server is ready!");
        } catch (Exception e) {
            System.out.println("RMI Server is failed because " + e);
        }
    }

}
