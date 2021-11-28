package utils;

import entities.Patient;

import java.util.Comparator;

public interface ComparingStrategy extends Comparator<Patient> {
    @Override
    int compare(Patient o1, Patient o2);
}
