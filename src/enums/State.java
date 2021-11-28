package enums;

public enum State {
    IN_TRIAGE("waiting in triage"),
    HOSPITALIZED("hospitalized"),
    WAITING_FOR_OPERATION("waiting for operation"),
    OPERATED("operated"),
    HOME_TREATMENT("home treatment"),
    CRITIC_CONDITION("in critic condition"),
    DECEASED("deceased");

    private String currentState;

    State(String currentState) {
        this.currentState = currentState;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }
}
