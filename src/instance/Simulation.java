package instance;

import entities.Doctor;
import entities.Nurse;
import entities.Patient;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private static Simulation instance = null;
    private int simulationLength;
    private int nursesCount;
    private int doctorsCount;
    private int patientsCount;
    private List<Doctor> doctors = new ArrayList<>();
    private List<Nurse> nurses = new ArrayList<>();
    private List<Patient> patients = new ArrayList<>();



    public Simulation() {
    }

    public static Simulation getInstance() {
        if (instance == null) {
            instance = new Simulation();
        }
        return instance;
    }

    public static void setInstance(Simulation instance) {
        Simulation.instance = instance;
    }

    public int getSimulationLength() {
        return simulationLength;
    }

    public void setSimulationLength(int simulationLength) {
        this.simulationLength = simulationLength;
    }

    public int getNursesCount() {
        return nursesCount;
    }

    public void setNursesCount(int nursesCount) {
        this.nursesCount = nursesCount;
    }

    public int getDoctorsCount() {
        return doctorsCount;
    }

    public void setDoctorsCount(int doctorsCount) {
        this.doctorsCount = doctorsCount;
    }

    public int getPatientsCount() {
        return patientsCount;
    }

    public void setPatientsCount(int patientsCount) {
        this.patientsCount = patientsCount;
    }

    public List<Nurse> getNurses() {
        return nurses;
    }

    public void setNurses(List<Nurse> nurses) {
        this.nurses = nurses;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    @Override
    public String toString() {
        return "Simulation{" +
                "simulationLength=" + simulationLength +
                ", nursesCount=" + nursesCount +
                ", doctorsCount=" + doctorsCount +
                ", patientsCount=" + patientsCount + '\n' +
                ", doctors=" + doctors + '\n' +
                ", nurses=" + nurses + '\n' +
                ", patients=" + patients +
                '}' + '\n';
    }
}
