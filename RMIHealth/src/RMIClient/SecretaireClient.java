package RMIClient;

import java.rmi.Naming;
import java.util.Scanner;

import RMIServer.PatientInterface;

public class SecretaireClient {

    public static void main(String[] args) {
        try {
            PatientInterface patientInt = (PatientInterface) Naming.lookup("rmi://localhost:1236/Patient");
            Scanner sc = new Scanner(System.in);
            String choix = "";

            while (!choix.equals("0")) {
                System.out.print("\n1. Consult a patient\n2. List all patients\n0. Quit\nChoice: ");
                choix = sc.nextLine().trim();

                if (choix.equals("1")) {
                    System.out.print("Patient id: ");
                    try {
                        System.out.println(patientInt.getPatientById(Integer.parseInt(sc.nextLine().trim())));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid id.");
                    }
                } else if (choix.equals("2")) {
                    for (String p : patientInt.getAllPatients()) {
                        System.out.println(p);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("RMI Client is failed because " + e);
        }
    }

}
