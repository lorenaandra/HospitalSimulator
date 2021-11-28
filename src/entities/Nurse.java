package entities;

import java.util.List;

public class Nurse {
    private int id;
    private boolean isBusy;
    private List<Patient> patients;

    public Nurse(int id, boolean isBusy, List<Patient> patients) {
        this.id = id;
        this.isBusy = isBusy;
        this.patients = patients;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    @Override
    public String toString() {
        return "Nurse{" +
                "id=" + id +
                ", isBusy=" + isBusy +
                 + '\n' + ", patients=" + patients +
                '}' + '\n';
    }
}
