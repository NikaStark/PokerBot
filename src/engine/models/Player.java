package engine.models;

public class Player {

    private boolean statusActive;

    private boolean mySit;

    public Player() {
        this(false);
    }

    public Player(boolean statusActive) {
        this.statusActive = statusActive;
        this.mySit = false;
    }

    public boolean isStatusActive() {
        return statusActive;
    }

    public boolean isMySit() {
        return mySit;
    }

    public void setStatus(boolean isStatusActive) {
        this.statusActive = isStatusActive;
    }

    public void setMySit(boolean isMySit) {
        this.mySit = isMySit;
    }

}
