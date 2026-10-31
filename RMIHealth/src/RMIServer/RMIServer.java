package RMIServer;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class RMIServer {

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1236);
            Naming.rebind("rmi://localhost:1236/Patient", new PatientImpl());
            System.out.println("RMI Health Center server is ready!");
        } catch (Exception e) {
            System.out.println("RMI Server is failed because " + e);
        }
    }

}
