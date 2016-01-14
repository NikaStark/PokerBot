package engine.models;

import com.sun.jna.platform.win32.WinDef;
import engine.controllers.calculator.Calculator;
import engine.models.exceptions.AbsentInfinitePlayerException;
import engine.models.exceptions.NotFoundDistributionException;

public class Table {

    private final String hexKey;

    private final int maxTablePlayers;

    private final int bigBlind;

    private final Player[] players;

    private int dealerPos;

    private Distribution currentDistribution;

    private Calculator calculator;

    private WinDef.HWND hwnd;

    public Table(String hexKey, int maxTablePlayers, int bigBlind, WinDef.HWND hwnd) {
        this.hexKey = hexKey;
        this.maxTablePlayers = maxTablePlayers;
        this.bigBlind = bigBlind;
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

    public int getBigBlind() {
        return bigBlind;
    }

    public Player[] getPlayers() {
        return players;
    }

    public int getIndexFirstPlayerNotNull() throws AbsentInfinitePlayerException {
        for (int i = 0; i < getPlayers().length; i++) {
            if (getPlayers()[i] == null) {
                return i;
            }
        }
        throw new AbsentInfinitePlayerException();
    }

    public int getDealerPos() {
        return dealerPos;
    }

    public Distribution getCurrentDistribution() throws NotFoundDistributionException {
        if (this.currentDistribution == null) {
            throw new NotFoundDistributionException();
        }
        return currentDistribution;
    }

    public Calculator getCalculator() {
        return calculator;
    }

    public WinDef.HWND getHwnd() {
        return hwnd;
    }

    public void setDealerPos(int dealerPos) {
        this.dealerPos = dealerPos;
    }

    public void setCurrentDistribution(Distribution currentDistribution) {
        this.currentDistribution = currentDistribution;
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
                    this.bigBlind == table.bigBlind) {
                return true;
            }
        }
        return false;
    }

}
