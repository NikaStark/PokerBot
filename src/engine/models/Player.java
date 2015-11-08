package engine.models;

public class Player {

    private boolean statusIsActive;

    private boolean isItMySit;

    public Player() {
        this(false, false);
    }

    public Player(boolean statusIsActive, boolean isItMySit) {
        this.statusIsActive = statusIsActive;
        this.isItMySit = isItMySit;
    }

    public boolean isStatusIsActive() {
        return statusIsActive;
    }

    public void setStatusIsActive(boolean statusIsActive) {
        this.statusIsActive = statusIsActive;
    }

    public boolean isItMySit() {
        return isItMySit;
    }

}
