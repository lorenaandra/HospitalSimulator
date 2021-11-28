package entities;

import enums.Illness;
import enums.Specialty;
import enums.State;

import java.util.ArrayList;
import java.util.List;

public class Doctor {
    private int id;
    private Specialty specialty;
    private boolean isBusy;
    private List<Patient> patients;

    public Doctor(int id, Specialty specialty, boolean isBusy, List<Patient> patients) {
        this.id = id;
        this.specialty = specialty;
        this.isBusy = isBusy;
        this.patients = patients;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
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

    public void operate(Patient patient) {
        patient.setState(State.OPERATED);
        patient.setBudget(patient.getBudget() - 1000);
    }

    public void consult(Patient patient) {
        if (patient.getAge() > 5 && patient.getAge() < 10) {
            patient.setIllness(Illness.FOOD_POISONING);
            patient.setState(State.HOME_TREATMENT);
        } else if (patient.getAge() > 10 && patient.getAge() < 20) {
            patient.setIllness(Illness.CUTS);
            patient.setState(State.HOME_TREATMENT);
        } else if (patient.getAge() > 20 && patient.getAge() < 30) {
            patient.setIllness(Illness.BURNS);
            patient.setState(State.HOME_TREATMENT);
        } else if (patient.getAge() > 30 && patient.getAge() < 40) {
            patient.setIllness(Illness.ALLERGIC_REACTION);
            patient.setState(State.HOSPITALIZED);
        } else if (patient.getAge() > 40 && patient.getAge() < 50) {
            patient.setIllness(Illness.CAR_ACCIDENT);
            patient.setState(State.CRITIC_CONDITION);
        } else if (patient.getAge() > 50 && patient.getAge() < 60) {
            patient.setIllness(Illness.FEVER);
            patient.setState(State.HOSPITALIZED);
        } else if (patient.getAge() > 60 && patient.getAge() < 70) {
            patient.setIllness(Illness.STROKE);
            patient.setState(State.CRITIC_CONDITION);
        } else if (patient.getAge() > 70) {
            patient.setIllness(Illness.HEART_ATTACK);
            patient.setState(State.DECEASED);
        }
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", specialty=" + specialty +
                ", isBusy=" + isBusy +
                ", patients=" + patients +
                '}' + '\n';
    }
}
