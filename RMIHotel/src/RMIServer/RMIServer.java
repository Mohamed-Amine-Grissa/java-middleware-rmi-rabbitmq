package RMIServer;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class RMIServer {

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1237);
            Naming.rebind("rmi://localhost:1237/Hotel", new HotelImpl());
            System.out.println("RMI Hotel server is ready: remote object is available!");
        } catch (Exception e) {
            System.out.println("RMI Server is failed because " + e);
        }
    }

}
