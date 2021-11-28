package utils;

import entities.Patient;

import java.util.Comparator;

public class PatientComparatorID implements ComparingStrategy {
    public int compare(Patient p1, Patient p2) {

        int result = p2.getId() - p1.getId();

        if (result > 0) {
            return -1;
        } else if (result == 0) {
            return 0;
        } else {
            return 1;
        }

    }
}
