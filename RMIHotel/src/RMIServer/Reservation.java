package RMIServer;

import java.io.Serializable;
import java.time.LocalDate;

public class Reservation implements Serializable {

    private int IDReservation;
    private String nomClient;
    private int numeroChambre;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private double prixTotal;

    public Reservation(int IDReservation, String nomClient, int numeroChambre,
                       LocalDate dateDebut, LocalDate dateFin, double prixTotal) {
        this.IDReservation = IDReservation;
        this.nomClient = nomClient;
        this.numeroChambre = numeroChambre;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prixTotal = prixTotal;
    }

    public int getIDReservation() { return IDReservation; }

    public String getNomClient() { return nomClient; }

    public int getNumeroChambre() { return numeroChambre; }

    public String toString() {
        return "Reservation " + IDReservation + " : " + nomClient + ", chambre " + numeroChambre + ", du " + dateDebut + " au " + dateFin + ", " + prixTotal + " DT";
    }

}
