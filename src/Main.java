import entities.Doctor;
import entities.Nurse;
import entities.Patient;
import enums.Illness;
import enums.Specialty;
import enums.State;
import instance.Simulation;
import utils.PatientComparator;
import utils.PatientComparatorID;

import javax.print.Doc;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class Main {

    private Main() {
    }

    public static void main(String[] args) {

        // create ER simulation (singleton)
        Simulation aux = new Simulation();
        // set nr of rounds
        aux.setSimulationLength(1);
        // set available resources
        aux.setNursesCount(5);
        aux.setDoctorsCount(8);
        aux.setPatientsCount(10);
        // call doctors to work
        List<Doctor> availableDoctors = new ArrayList<>();
        availableDoctors.add(new Doctor(100, Specialty.CARDIOLOGIST, false, null));
        availableDoctors.add(new Doctor(101, Specialty.ER_doctor, false, null));
        availableDoctors.add(new Doctor(102, Specialty.ANESTHESIOLOGIST, false, null));
        availableDoctors.add(new Doctor(103, Specialty.NEUROLOGIST, false, null));
        availableDoctors.add(new Doctor(104, Specialty.SURGEON, false, null));
        availableDoctors.add(new Doctor(105, Specialty.RADIOLOGIST, false, null));
        availableDoctors.add(new Doctor(106, Specialty.DIAGNOSTICIAN, false, null));
        availableDoctors.add(new Doctor(107, Specialty.ER_doctor, false, null));
        aux.setDoctors(availableDoctors);
        // open ER
        List<Patient> sickPeople = new ArrayList<>();
        sickPeople.add(new Patient(200, "Paul", "Jackson", 42, State.IN_TRIAGE, Illness.UNKNOWN, 300));
        sickPeople.add(new Patient(201, "Monet", "Cameron", 23, State.IN_TRIAGE, Illness.UNKNOWN, 400));
        sickPeople.add(new Patient(202, "John", "Wick", 33, State.IN_TRIAGE, Illness.UNKNOWN, 700));
        sickPeople.add(new Patient(203, "Andrew", "Howard", 78, State.IN_TRIAGE, Illness.UNKNOWN, 900));
        sickPeople.add(new Patient(204, "Jessica", "Parker", 63, State.IN_TRIAGE, Illness.UNKNOWN, 1200));
        sickPeople.add(new Patient(205, "Sarah", "Johnson", 7, State.IN_TRIAGE, Illness.UNKNOWN, 500));
        sickPeople.add(new Patient(206, "Mike", "Stewart", 40, State.IN_TRIAGE, Illness.UNKNOWN, 15000));
        sickPeople.add(new Patient(207, "Anthony", "Thomson", 27, State.IN_TRIAGE, Illness.UNKNOWN, 10000));
        sickPeople.add(new Patient(208, "Meghan", "Evans", 31, State.IN_TRIAGE, Illness.UNKNOWN, 3000));
        sickPeople.add(new Patient(209, "Jimmy", "Smith", 17, State.IN_TRIAGE, Illness.UNKNOWN, 500));
        aux.setPatients(sickPeople);
        // call nurses to work
        List<Nurse> nurses = new ArrayList<>();
        nurses.add(new Nurse(300, false, null));
        nurses.add(new Nurse(301, false, null));
        nurses.add(new Nurse(302, false, null));
        nurses.add(new Nurse(303, false, null));
        nurses.add(new Nurse(304, false, null));
        aux.setNurses(nurses);

        Simulation.setInstance(aux);
        Simulation simulation = Simulation.getInstance();

        // sort patients according to age - most critical patients are probably the elderly
        // decreasing by age
        Collections.sort(simulation.getPatients(), new PatientComparator());

        // distribute patients to nurses
        int i = 0;
        List<Patient> currentPatients = new ArrayList<>();
        for (Nurse nurse : simulation.getNurses()) {
            currentPatients.add(simulation.getPatients().get(i));
            i++;
            currentPatients.add(simulation.getPatients().get(i));
            i++;
            nurse.setPatients(currentPatients);
            nurse.setBusy(true);
            currentPatients = new ArrayList<>();
        }

        // update patients status
        for (Nurse nurse : simulation.getNurses()) {
            for (Patient patient : nurse.getPatients()) {
                patient.setState(State.WAITING_FOR_OPERATION);
            }
        }

        // select er doctors
        List<Doctor> ERDoctors = new ArrayList<>();
        for (Doctor doctor : simulation.getDoctors()) {
            if (doctor.getSpecialty().equals(Specialty.ER_doctor)) {
                ERDoctors.add(doctor);
            }
        }

        // make nurses distribute patients to doctors
        int ratio = simulation.getPatientsCount() / ERDoctors.size();
        int count = 0;
        currentPatients = new ArrayList<>();
        List<Patient> currentPatients2 = new ArrayList<>();
        for (Nurse nurse : simulation.getNurses()) {
            if (count < ratio) {
                if (!ERDoctors.get(0).isBusy()) {
                    currentPatients.add(nurse.getPatients().get(0));
                    count++;
                    currentPatients.add(nurse.getPatients().get(1));
                    count++;
                    ERDoctors.get(0).setPatients(currentPatients);
                }
            } else {
                if (!ERDoctors.get(1).isBusy()) {
                    currentPatients2.add(nurse.getPatients().get(0));
                    count++;
                    currentPatients2.add(nurse.getPatients().get(1));
                    count++;
                    ERDoctors.get(1).setPatients(currentPatients2);
                }

            }
        }

        // free nurses
        List<Patient> freePatients = new ArrayList<>();
        for (Nurse nurse : simulation.getNurses()) {
            nurse.setPatients(freePatients);
            nurse.setBusy(false);
        }

        // update er doctors status to busy
        for (Doctor erdoctor : ERDoctors) {
            erdoctor.setBusy(true);
        }

        // consult patients
        for (Doctor erdoctor : ERDoctors) {
            for (Patient patient : erdoctor.getPatients()) {
                erdoctor.consult(patient);
                patient.setBudget(patient.getBudget() - 300);
            }
        }

        // select surgeons
        List<Doctor> surgeons = new ArrayList<>();
        for (Doctor doctor : simulation.getDoctors()) {
            if (doctor.getSpecialty().equals(Specialty.SURGEON)) {
                surgeons.add(doctor);
            }
        }


        List<Patient> surgeryPatients = new ArrayList<>();
        // operate on the most critic patients
        for (Patient patient : simulation.getPatients()) {
            if (patient.getState().equals(State.CRITIC_CONDITION)) {
                surgeryPatients.add(patient);
                for (Doctor surgeon : surgeons) {
                    if (!surgeon.isBusy()) {
                        surgeon.setPatients(surgeryPatients);
                        surgeon.operate(patient);
                        surgeon.setBusy(true);
                    }
                }
            }
        }

        // free surgeon
        for (Doctor surgeon : surgeons) {
            surgeon.setBusy(false);
        }

        // end round
        System.out.println("\nRound over, ER is free! Results: \n");
        for (Patient patient : simulation.getPatients()) {
            if (patient.getState().equals(State.DECEASED)) {
                System.out.println("Patient " + patient.getId() + ", named " + patient.getFirstName()
                        + ' ' + patient.getLastName() + ", aged " + patient.getAge()
                        + " has sadly passed away! \n");
            }
            else if (patient.getState().equals(State.HOME_TREATMENT)) {
                System.out.println("Patient " +  patient.getId() + ", named " + patient.getFirstName()
                        + ' ' + patient.getLastName() + ", aged " + patient.getAge()
                + " has been sent home for treatment.\n");
            }
            else if (patient.getState().equals(State.OPERATED)) {
                System.out.println("Patient " +  patient.getId() + ", named " + patient.getFirstName()
                        + ' ' + patient.getLastName() + ", aged " + patient.getAge()
                        + " has been operated and is recovering.\n");
            } else if (patient.getState().equals(State.CRITIC_CONDITION)) {
                System.out.println("Patient " +  patient.getId() + ", named " + patient.getFirstName()
                        + ' ' + patient.getLastName() + ", aged " + patient.getAge()
                        + " is in critic condition and is fighting for his life.\n");
            } else if (patient.getState().equals(State.HOSPITALIZED)) {
                System.out.println("Patient " +  patient.getId() + ", named " + patient.getFirstName()
                        + ' ' + patient.getLastName() + ", aged " + patient.getAge()
                        + " is currently in the hospital under supervised care.\n");
            }
        }

        System.out.println();
        System.out.println(simulation);

        System.out.println();
        System.out.println();
        Collections.sort(simulation.getPatients(), new PatientComparatorID());
        System.out.println("Sorting patients by ID:\n");
        System.out.println(simulation.getPatients());



    }




}