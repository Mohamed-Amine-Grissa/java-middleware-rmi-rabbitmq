package RMIServer;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface StudentGradeInterface extends Remote {

    public double getStudentGrade(int StudentID, String SubjectName) throws RemoteException;

}
