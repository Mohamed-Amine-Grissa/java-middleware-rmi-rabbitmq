package RMIClient;

import java.rmi.Naming;
import java.util.Scanner;

import RMIServer.PatientInterface;

public class MedecinClient {

    public static void main(String[] args) {
        try {
            PatientInterface patientInt = (PatientInterface) Naming.lookup("rmi://localhost:1236/Patient");
            Scanner sc = new Scanner(System.in);
            String choix = "";

            while (!choix.equals("0")) {
                System.out.print("\n1. Ajouter un patient\n0. * Quitter app * \nChoix: ");
                choix = sc.nextLine().trim();

                if (choix.equals("1")) {
                    try {
                        System.out.print("Id: ");
                        int id = Integer.parseInt(sc.nextLine().trim());

                        System.out.print("Nom: ");
                        String nom = sc.nextLine().trim();

                        System.out.print("Prenom : ");
                        String prenom = sc.nextLine().trim();

                        System.out.print("Age: ");
                        int age = Integer.parseInt(sc.nextLine().trim());

                        System.out.print("Historique Medical : ");
                        String historique = sc.nextLine().trim();

                        System.out.println(patientInt.addPatient(id, nom + " " + prenom + " : " + age + " , Historique Medical :  " + historique));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number.");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("RMI Client is failed because " + e);
        }
    }

}
