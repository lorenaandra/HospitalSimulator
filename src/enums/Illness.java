package enums;

public enum Illness {
    UNKNOWN("unknown"),
    HEALTHY("currently healthy"),
    ABDOMINAL_PAIN("abdominal pain"),
    ALLERGIC_REACTION("allergic reaction"),
    BROKEN_BONES("broken bones"),
    BURNS("burns"),
    CAR_ACCIDENT("car accident"),
    CUTS("cuts"),
    FOOD_POISONING("food poisoning"),
    HEART_ATTACK("heart attack"),
    HEART_DISEASE("heart disease"),
    FEVER("fever"),
    STROKE("stroke");

    private String disease;

    Illness(String disease) {
        this.disease = disease;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }
}
