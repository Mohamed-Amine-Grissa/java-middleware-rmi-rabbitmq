package RMIClient;

import java.rmi.Naming;
import java.util.Scanner;

import RMIServer.BankAccountInterface;

public class RMIClient {

    public static void main(String[] args) {
        try {
            BankAccountInterface bank =
                    (BankAccountInterface) Naming.lookup("rmi://localhost:1235/BankAccount");
            System.out.println("RMI client is ready!");

            Scanner sc = new Scanner(System.in);
            boolean running = true;
            while (running) {
                System.out.println();
                System.out.println("===== Menu de La Banque =====");
                System.out.println("1. Deposit");
                System.out.println("2. Withdraw");
                System.out.println("3. Check balance");
                System.out.println("0. Quit");
                System.out.print("Choix: ");

                String choice = sc.nextLine().trim();
                switch (choice) {
                    case "1": {
                        Integer iban = readInt(sc, "IBAN: ");
                        Double amount = readDouble(sc, "Amount: ");
                        if (iban != null && amount != null) {
                            System.out.println(bank.depot(iban, amount));
                        }
                        break;
                    }
                    case "2": {
                        Integer iban = readInt(sc, "IBAN: ");
                        Double amount = readDouble(sc, "Amount: ");
                        if (iban != null && amount != null) {
                            System.out.println(bank.retrait(iban, amount));
                        }
                        break;
                    }
                    case "3": {
                        Integer iban = readInt(sc, "IBAN: ");
                        if (iban != null) {
                            double balance = bank.consultation(iban);
                            if (balance < 0) {
                                System.out.println("Error: unknown IBAN " + iban + ".");
                            } else {
                                System.out.println("Balance of " + iban + ": " + balance);
                            }
                        }
                        break;
                    }
                    case "0":
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
            sc.close();
            System.out.println("Goodbye.");
        } catch (Exception e) {
            System.out.println("RMI Client is failed because " + e);
        }
    }

    private static Integer readInt(Scanner sc, String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number.");
            return null;
        }
    }

    private static Double readDouble(Scanner sc, String prompt) {
        System.out.print(prompt);
        try {
            return Double.parseDouble(sc.nextLine().trim().replace(',', '.'));
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount.");
            return null;
        }
    }

}
