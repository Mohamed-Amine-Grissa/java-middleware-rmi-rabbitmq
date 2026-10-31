package RMIServer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface PatientInterface extends Remote {

    public String getPatientById(int id) throws RemoteException;

    public List<String> getAllPatients() throws RemoteException;

    public String addPatient(int id, String info) throws RemoteException;

}
