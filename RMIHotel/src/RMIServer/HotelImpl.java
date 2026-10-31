package RMIServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class HotelImpl extends UnicastRemoteObject implements HotelInterface {
    private final HashMap<Integer, Reservation> reservations = new HashMap<Integer, Reservation>();
    private int nextId = 1;

    public HotelImpl() throws RemoteException {
    }

    public synchronized int reserveChambre(String nomClient, int numeroChambre, LocalDate dateDebut, LocalDate dateFin, double prixTotal) throws RemoteException {
        int id = nextId++;
        reservations.put(id, new Reservation(id, nomClient, numeroChambre, dateDebut, dateFin, prixTotal));
        System.out.println("Reservation " + id + " created for " + nomClient);
        return id;
    }

    public synchronized boolean annuleReservation(String nomClient, int numeroChambre) throws RemoteException {
        for (Reservation r : reservations.values()) {
            if (r.getNomClient().equals(nomClient) && r.getNumeroChambre() == numeroChambre) {
                reservations.remove(r.getIDReservation());
                System.out.println("Reservation " + r.getIDReservation() + " cancelled");
                return true;
            }
        }
        return false;
    }

    public synchronized Map<Integer, Reservation> listeReservations() throws RemoteException {
        return new HashMap<Integer, Reservation>(reservations);
    }

}
