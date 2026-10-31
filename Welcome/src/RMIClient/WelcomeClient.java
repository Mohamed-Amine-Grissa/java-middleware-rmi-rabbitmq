package RMIClient;

import java.rmi.Naming;

import RMIServer.WelcomeInterface;

public class WelcomeClient {

    public static void main(String[] args) {
        try {
            WelcomeInterface welcomeInt = (WelcomeInterface) Naming.lookup("rmi://localhost:1233/Welcome");
            System.out.println("RMI client is ready!");
            System.out.println(welcomeInt.sayWelcome());
        } catch (Exception e) {
            System.out.println("RMI Client is failed because " + e);
        }
    }

}
