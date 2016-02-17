package inputer;

import engine.models.*;
import engine.models.exceptions.*;

import java.util.List;

public class Translator {

    public void translator(List<String> tempStorage) {
        tempStorage = !isDistributionNew(tempStorage.get(1)) ?
                whatStreetNow(tempStorage) == Distribution.StreetPoker.PRE_FLOP ?
                        destroyReiterateInfo(tempStorage)
                        : destroyUnnecessaryInfo(tempStorage)
                : destroyReiterateInfo(tempStorage);
         reader(tempStorage);
    }

    protected Distribution.StreetPoker whatStreetNow(List<String> tempStorage) {
        for (int i = tempStorage.size() - 1; i >= 0; i--) {
            String currentLine = tempStorage.get(i);
            if (currentLine.substring(18, currentLine.indexOf(' ')).equals("updateMyCard()")) {
                return Distribution.StreetPoker.PRE_FLOP;
            } else {
                int position = currentLine.lastIndexOf('(');
                switch (currentLine.substring(position, position + 3)) {
                    case "(2)": return Distribution.StreetPoker.FLOP;
                    case "(3)": return Distribution.StreetPoker.TURN;
                    case "(4)": return Distribution.StreetPoker.RIVER;
                }
            }
        }
        throw new RuntimeException();
    }

    protected List<String> destroyReiterateInfo(List<String> tempStorage) {
        for (int i = 0; i < tempStorage.size(); i++) {
            String subString = tempStorage.get(i).substring(0, 3);
            if (subString.equals(":::")) {
                if (tempStorage.get(i).length() <= 7) {
                    tempStorage.remove(i--);
                } else {
                    int position = tempStorage.get(i).lastIndexOf('(');
                    if (tempStorage.get(i).substring(position, position + 3).equals("(1)")) {
                        tempStorage.remove(i);
                        break;
                    }
                    tempStorage.remove(i--);
                }
            } else if (subString.equals("  s")) {
                tempStorage.remove(i--);
            }
        }
        return tempStorage;
    }

    protected List<String> destroyUnnecessaryInfo(List<String> tempStorage) {
        tempStorage.removeAll(tempStorage.subList(0, searchPointer(tempStorage) + 1));
        return tempStorage;
    }

    protected int searchPointer(List<String> tempStorage) {
        for (int i = tempStorage.size() - 1; i >= 0; i--) {
            String subString = tempStorage.get(i).substring(1, 4);
            if (subString.equals("'*'") || subString.equals("'C'") || subString.equals("'E'")) {
                if (tempStorage.size() - i > 2) {
                    return i;
                }
            }
        }
        throw new RuntimeException();
    }

    protected boolean isDistributionNew(final String line) {
        String numberOfDistribution = line.substring(6, 18);
        String hexKey = line.substring(19);
        try {
            return !searchTable(hexKey).getCurrentDistribution().getNumberOfDistribution().equals(numberOfDistribution);
        } catch (NotFoundTableException exc) {
            return false;
        }
    }

    protected Table searchTable(final String hexKey) throws NotFoundTableException {
        for (Table table : Game.TABLES) {
            if (table.getHexKey().equals(hexKey)) {
                return table;
            }
        }
        throw new NotFoundTableException();
    }

    protected void reader(List<String> tempStorage)  {
        try {
            Table currentTable = searchTable(tempStorage.get(0).substring(19));
            for (String currentLine : tempStorage) {
                switch (currentLine.substring(0, 5)) {
                    case "  sit":
                        initializePlayer(currentLine, currentTable);
                        break;
                    case "  dea":
                        initializeDealerPos(currentLine, currentTable);
                        break;
                    case ":::Ta":
                        initializeCard(currentLine, currentTable);
                        break;
                    case " 'F' ":
                        initializePossibleSteps(currentLine, currentTable, currentLine.charAt(2));
                        break;
                    case " 'c' ":
                        initializePossibleSteps(currentLine, currentTable, currentLine.charAt(2));
                        break;
                    case " 'C' ":
                        initializePossibleSteps(currentLine, currentTable, currentLine.charAt(2));
                        break;
                    case " 'E' ":
                        initializePossibleSteps(currentLine, currentTable, currentLine.charAt(2));
                        break;
                    case " '*' ":
                        initializePossibleSteps(currentLine, currentTable, currentLine.charAt(2));
                        break;
                }
            }
            currentTable.getCurrentDistribution().calculateMyStack();
            currentTable.getCurrentDistribution().calculateBank(currentTable.getHwnd()); // TODO You must do this only at first street each distribution
        } catch (NotFoundTableException exc) {
            throw new RuntimeException("Exception - " + exc);
        }
    }

    protected Card searchCard(String line) throws IllegalSuitException {
        int numericValue = Integer.parseInt(line.substring(33, 34));
        char suit;
        if (numericValue == 1) {
            numericValue = Integer.parseInt(line.substring(34, 35)) + 10;
            suit = line.charAt(35);
        } else {
            suit = line.charAt(34);
        }
        return new Card(numericValue, suit);
    }

    protected void initializePlayer(String line, Table currentTable) {
        try {
            int numberPlayer = Integer.parseInt(line.substring(5, 6));
            if (currentTable.getIndexFirstPlayerNotNull() != numberPlayer) {
                do {
                    currentTable.getPlayers()[currentTable.getIndexFirstPlayerNotNull()] = new Player();
                } while (currentTable.getIndexFirstPlayerNotNull() != numberPlayer);
            }
            currentTable.getPlayers()[currentTable.getIndexFirstPlayerNotNull()] = new Player(true);
        } catch (AbsentNotInitializePlayerException exc) {
           throw new RuntimeException("Exception - " + exc);
        }
    }

    protected void defineMySit(Table currentTable) {
        try {
            currentTable.getPlayers()[currentTable.getIndexFirstPlayerNotNull() - 1].setMySit(true);
        } catch (AbsentNotInitializePlayerException exc) {
            throw new RuntimeException("Exception - " + exc);
        }
    }

    protected void initializeCard(String line, Table currentTable) {
        int numberOfCard = Integer.parseInt(line.substring(line.lastIndexOf('(') + 1, line.lastIndexOf('(') + 2));
        try {
            if (line.substring(24, 26).equals("My")) {
                currentTable.getCurrentDistribution().addPlayersCard(searchCard(line), numberOfCard);
                defineMySit(currentTable);
            } else {
                if (numberOfCard <= 2) {
                    currentTable.getCurrentDistribution().addFlopCard(searchCard(line), numberOfCard);
                } else if (numberOfCard == 3) {
                    currentTable.getCurrentDistribution().setTurnCard(searchCard(line));
                } else if (numberOfCard == 4) {
                    currentTable.getCurrentDistribution().setRiverCard(searchCard(line));
                }
            }
        }catch (IllegalSuitException | ReassigningFieldException exc) {
            throw new RuntimeException("Exception - " + exc);
        }
    }

    protected void initializeDealerPos(String line, Table currentTable) {
        currentTable.getCurrentDistribution().setDealerPos(Integer.parseInt(line.substring(12, 13)));
    }

    protected void initializePossibleSteps(String line, Table currentTable, char symbol) {
        if (symbol == 'F') {
            currentTable.getCurrentDistribution().getCurrentPossibleSteps().
                    put(Distribution.PossibleSteps.FOLD, new Integer[]{0});
        } else if (symbol == 'c') {
            currentTable.getCurrentDistribution().getCurrentPossibleSteps().
                    put(Distribution.PossibleSteps.CHECK, new Integer[]{0});
        } else if (symbol == 'C') {
            currentTable.getCurrentDistribution().getCurrentPossibleSteps().
                    put(Distribution.PossibleSteps.CALL, new Integer[]{Integer.parseInt(line.substring(5).trim())});
        } else if (symbol == 'E') {
            currentTable.getCurrentDistribution().getCurrentPossibleSteps().
                    put(Distribution.PossibleSteps.RAISE, new Integer[]{Integer.parseInt(line.substring(5).trim())});
        } else if (symbol == '*') {
            String[] valuesOfCurrentPossibleSteps = line.substring(9).trim().split(", ");
            currentTable.getCurrentDistribution().getCurrentPossibleSteps().
                    put(Distribution.PossibleSteps.RAISE, new Integer[]{
                            Integer.parseInt(valuesOfCurrentPossibleSteps[0]),
                            Integer.parseInt(valuesOfCurrentPossibleSteps[1]),
                            Integer.parseInt(valuesOfCurrentPossibleSteps[2]),
                            Integer.parseInt(valuesOfCurrentPossibleSteps[3])});
        }
    }

}
