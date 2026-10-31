package RMIServer;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {

    public static void main(String[] args) {
        try {
            BankAccountImpl rmiObj = new BankAccountImpl();

            // Keep a reference to the registry so it is not garbage collected.
            Registry registry = LocateRegistry.createRegistry(1235);
            Naming.rebind("rmi://localhost:1235/BankAccount", rmiObj);

            System.out.println("RMI bank server is ready! (accounts: 1001, 1002, 1003)");
        } catch (Exception e) {
            System.out.println("RMI Server is failed because " + e);
        }
    }

}
