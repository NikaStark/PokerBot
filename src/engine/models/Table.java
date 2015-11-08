package engine.models;

import engine.controllers.calculator.Calculator;

public class Table {

    private final String hexKey;

    private final int maxTablePlayers;

    private final Player[] players;

    private int dealerPos;

    private Distribution currentDistribution;

    private Calculator calculator;

    public Table(String hexKey, int maxTablePlayers) {
        this.hexKey = hexKey;
        this.maxTablePlayers = maxTablePlayers;
        this.players = new Player[maxTablePlayers];
    }

    public String getHexKey() {
        return hexKey;
    }

    public int getMaxTablePlayers() {
        return maxTablePlayers;
    }

    public int getDealerPos() {
        return dealerPos;
    }

    public Player[] getPlayers() {
        return players;
    }

    public Distribution getCurrentDistribution() {
        return currentDistribution;
    }

    public Calculator getCalculator() {
        return calculator;
    }

    public void setDealerPos(int dealerPos) {
        this.dealerPos = dealerPos;
    }

    public void setCurrentDistribution(Distribution currentDistribution) {
        this.currentDistribution = currentDistribution;
    }

    public void setCalculator(Calculator calculator) {
        this.calculator = calculator;
    }

}
