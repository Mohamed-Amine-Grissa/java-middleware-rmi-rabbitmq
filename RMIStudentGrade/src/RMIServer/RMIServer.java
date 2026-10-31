package RMIServer;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {

    public static void main(String[] args) {
        try {
            StudentGradeImpl rmiObj = new StudentGradeImpl();

            Registry registry = LocateRegistry.createRegistry(1234);
            Naming.rebind("rmi://localhost:1234/StudentGrade", rmiObj);

            System.out.println("RMI server is ready!");
        } catch (Exception e) {
            System.out.println("RMI Server is failed because " + e);
        }
    }

}
