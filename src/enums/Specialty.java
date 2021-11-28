package enums;

public enum Specialty {
    DIAGNOSTICIAN("diagnostician"),
    CARDIOLOGIST("cardiologist"),
    NEUROLOGIST("neurologist"),
    ANESTHESIOLOGIST("anesthesiologist"),
    ER_doctor("ER doctor"),
    SURGEON("surgeon"),
    RADIOLOGIST("radiologist");

    private String specialty;

    Specialty(String specialty) {
        this.specialty = specialty;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}
