package RMIClient;

import java.rmi.Naming;

import RMIServer.StudentGradeInterface;

public class RMIClient {

    public static void main(String[] args) {
        try {
            StudentGradeInterface gradeInt = (StudentGradeInterface) Naming.lookup("rmi://localhost:1234/StudentGrade");
            System.out.println("RMI client is ready!");

            int studentId = 1001;
            String subject = "Middlewares";
            double grade = gradeInt.getStudentGrade(studentId, subject);
            System.out.println("Student " + studentId + " grade in " + subject + ": " + grade + "/20");
        } catch (Exception e) {
            System.out.println("RMI Client is failed because " + e);
        }
    }

}
