package RMIClient;

import java.rmi.Naming;
import java.time.LocalDate;
import java.util.Map;

import RMIServer.HotelInterface;
import RMIServer.Reservation;

public class HotelClient {

    public static void main(String[] args) {
        try {
            HotelInterface hotel = (HotelInterface) Naming.lookup("rmi://localhost:1237/Hotel");

            int id1 = hotel.reserveChambre("Client1", 101, LocalDate.of(2026, 10, 1), LocalDate.of(2026, 10, 5), 400.0);
            int id2 = hotel.reserveChambre("Client2", 202, LocalDate.of(2026, 10, 3), LocalDate.of(2026, 10, 4), 150.0);
            int id3 = hotel.reserveChambre("Client3", 303, LocalDate.of(2026, 11, 10), LocalDate.of(2026, 11, 15), 900.0);
            System.out.println("Reservations created: " + id1 + ", " + id2 + ", " + id3);

            System.out.println("\n--- Reservations ---");
            for (Reservation r : hotel.listeReservations().values()) {
                System.out.println(r);
            }

            boolean ok = hotel.annuleReservation("Client2", 202);
            System.out.println("\nCancel Client2 / room 202: " + ok);

            System.out.println("\n--- Reservations ---");
            for (Reservation r : hotel.listeReservations().values()) {
                System.out.println(r);
            }
        } catch (Exception e) {
            System.out.println("RMI Client is failed because " + e);
        }
    }

}
