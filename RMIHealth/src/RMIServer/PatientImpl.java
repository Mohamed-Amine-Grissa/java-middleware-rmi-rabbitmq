package RMIServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PatientImpl extends UnicastRemoteObject implements PatientInterface {

    private final HashMap<Integer, String> patients = new HashMap<Integer, String>();

    public PatientImpl() throws RemoteException {
        patients.put(1, "Ben Salah AAAAA: 45 , Historique Medical: Rien");
        patients.put(2, "TrS SSSS: 32 , Historique Medical: Asthme");
        patients.put(3, "GhB KKKKK: 60 , Historique Medical: Diabete type 2");
    }

    public synchronized String getPatientById(int id) throws RemoteException {
        String info = patients.get(id);
        return info == null ? "Error: no patient with id " + id + "." : id + " - " + info;
    }

    public synchronized List<String> getAllPatients() throws RemoteException {
        List<String> result = new ArrayList<String>();
        for (int id : patients.keySet()) {
            result.add(id + " - " + patients.get(id));
        }
        return result;
    }

    public synchronized String addPatient(int id, String info) throws RemoteException {
        if (patients.containsKey(id)) {
            return "Error: a patient with id " + id + " already exists.";
        }
        patients.put(id, info);
        System.out.println("Patient " + id + " added");
        return "Patient " + id + " added successfully.";
    }

}
