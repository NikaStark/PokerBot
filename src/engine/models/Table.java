package engine.models;

import com.sun.jna.platform.win32.WinDef;
import engine.controllers.calculator.Calculator;
import engine.models.exceptions.AbsentNotInitializePlayerException;

public class Table {

    private final String hexKey;
    private final int maxTablePlayers;
    private final int smallBlind;
    private final Player[] players;
    private Distribution currentDistribution;
    private final Calculator calculator;
    private final WinDef.HWND hwnd;

    public Table(String hexKey, int maxTablePlayers, int smallBlind, Distribution currentDistribution, WinDef.HWND hwnd) {
        this.hexKey = hexKey;
        this.maxTablePlayers = maxTablePlayers;
        this.smallBlind = smallBlind;
        this.currentDistribution = currentDistribution;
        this.hwnd = hwnd;
        this.players = new Player[maxTablePlayers];
        this.calculator = new Calculator();
    }

    public String getHexKey() {
        return hexKey;
    }

    public int getMaxTablePlayers() {
        return maxTablePlayers;
    }

    public int getSmallBlind() {
        return smallBlind;
    }

    public Player[] getPlayers() {
        return players;
    }

    public int getIndexFirstPlayerNotNull() throws AbsentNotInitializePlayerException {
        for (int i = 0; i < getPlayers().length; i++) {
            if (getPlayers()[i] == null) {
                return i;
            }
        }
        throw new AbsentNotInitializePlayerException();
    }

    public Distribution getCurrentDistribution() {
        return currentDistribution;
    }

    public Calculator getCalculator() {
        return calculator;
    }

    public WinDef.HWND getHwnd() {
        return hwnd;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (this.getClass() == object.getClass()) {
            Table table = (Table) object;
            if (this.hexKey.equals(table.hexKey) &&
                    this.maxTablePlayers == table.maxTablePlayers &&
                    this.smallBlind == table.smallBlind) {
                return true;
            }
        }
        return false;
    }

}
