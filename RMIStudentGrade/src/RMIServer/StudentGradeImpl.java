package RMIServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class StudentGradeImpl extends UnicastRemoteObject implements StudentGradeInterface {

    public StudentGradeImpl() throws RemoteException {
        super();
    }

    public double getStudentGrade(int StudentID, String SubjectName) throws RemoteException {
        double grade = Math.round(Math.random() * 20 * 100) / 100.0;
        System.out.println("Grade requested for the student " + StudentID + " in '" + SubjectName + "' -> " + grade);
        return grade;
    }

}
