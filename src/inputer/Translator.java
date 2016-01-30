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
//        if (!isDistributionNew(tempStorage.get(1))) {
//            tempStorage = whatStreetNow(tempStorage) == Distribution.StreetPoker.PRE_FLOP ?
//                    destroyReiterateInfo(tempStorage) : destroyUnnecessaryInfo(tempStorage);
//        } else {
//            tempStorage = destroyReiterateInfo(tempStorage);
//        }
        try {
            reader(tempStorage);
        } catch (AbstractPokerBotException exc) {

        }
    }

    public Distribution.StreetPoker whatStreetNow(List<String> tempStorage) {
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
        return null; //TODO Bad practice, maybe do a throw exception
    }

    public List<String> destroyReiterateInfo(List<String> tempStorage) {
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

    public List<String> destroyUnnecessaryInfo(List<String> tempStorage) {
        tempStorage.removeAll(tempStorage.subList(0, searchPointer(tempStorage) + 1));
        return tempStorage;
    }

    public int searchPointer(List<String> tempStorage) {
        int temp = -1;
        for (int i = tempStorage.size() - 1; i >= 0; i--) {
            String subString = tempStorage.get(i).substring(1, 4);
            if (subString.equals("'*'") || subString.equals("'C'") || subString.equals("'E'")) {
                if (tempStorage.size() - i > 2) {
                    return i;
                }
            }
        }
        return temp; //TODO Bad practice, maybe do a throw exception
    }

    public boolean isDistributionNew(final String line) {
        String numberOfDistribution = line.substring(6, 18);
        String hexKey = line.substring(19);
        try {
            return !searchTable(hexKey).getCurrentDistribution().getNumberOfDistribution().equals(numberOfDistribution);
        } catch (AbstractPokerBotException exc) {
            return false;
        }
    }

    public Table searchTable(final String hexKey) throws NotFoundTableException {
        for (Table table : Game.tables) {
            if (table.getHexKey().equals(hexKey)) {
                return table;
            }
        }
        throw new NotFoundTableException();
    }

    public void reader(List<String> tempStorage) throws
            NotFoundTableException, IllegalSuitException, NotFoundDistributionException, ReassigningFieldException {
        Card[] playerCards = new Card[Game.AMOUNT_CARDS_AT_PLAYER];
        Card[] flopCards = new Card[Game.AMOUNT_CARDS_AT_FLOP];
        Table currentTable = searchTable(tempStorage.get(0).substring(19));
        for (int i = 0; i < tempStorage.size(); i++) {
            switch (tempStorage.get(i).substring(0, 5)) {
                case "  sit":
                    initializePlayer(tempStorage, currentTable, i);
                    break;
                case "  dea":
                    currentTable.getCurrentDistribution().setDealerPos(Integer.parseInt(tempStorage.get(i).substring(12, 13)));
                    break;
                case ":::Ta":   // TODO Maybe do creating player and flop cards in a steps(First card - creating array, next - get creating array at last step and put new card...)
                    String line = tempStorage.get(i);
                    int numberOfCard = Integer.parseInt(line.substring(
                            line.lastIndexOf('(') + 1, line.lastIndexOf('(') + 2));
                    if (line.substring(24, 26).equals("My")) {
                        playerCards[numberOfCard] = searchCard(line);
                        if (numberOfCard == Game.AMOUNT_CARDS_AT_PLAYER - 1) {
                            currentTable.setCurrentDistribution(
                                    new Distribution(playerCards, tempStorage.get(0).substring(6, 18)));
                        }
                    } else {
                        if (numberOfCard <= 2) {
                            playerCards[numberOfCard] = searchCard(line);
                            if (numberOfCard == Game.AMOUNT_CARDS_AT_FLOP - 1) {
                                currentTable.getCurrentDistribution().setFlopCards(flopCards);
                            }
                        } else if (numberOfCard == 3) {
                            currentTable.getCurrentDistribution().setTurnCard(searchCard(line));
                        } else if (numberOfCard == 4) {
                            currentTable.getCurrentDistribution().setRiverCard(searchCard(line));
                        }
                    }
                    break;
                case " 'F' ":
                    currentTable.getCurrentDistribution().getCurrentPossibleSteps().
                            put(Distribution.PossibleSteps.FOLD, new Integer[]{0});
                    break;
                case " 'c' ":
                    currentTable.getCurrentDistribution().getCurrentPossibleSteps().
                            put(Distribution.PossibleSteps.CHECK, new Integer[]{0});
                    break;
                case " 'C' ":
                    currentTable.getCurrentDistribution().getCurrentPossibleSteps().
                            put(Distribution.PossibleSteps.CALL,
                                    new Integer[]{Integer.parseInt(tempStorage.get(i).substring(5).trim())});
                    break;
                case " '*' ":
                    String[] valuesOfCurrentPossibleSteps = tempStorage.get(i).substring(9).trim().split(", ");
                    currentTable.getCurrentDistribution().getCurrentPossibleSteps().
                            put(Distribution.PossibleSteps.RAISE, new Integer[]{
                                            Integer.parseInt(valuesOfCurrentPossibleSteps[0]),
                                            Integer.parseInt(valuesOfCurrentPossibleSteps[1]),
                                            Integer.parseInt(valuesOfCurrentPossibleSteps[2]),
                                            Integer.parseInt(valuesOfCurrentPossibleSteps[3])});
                    break;
            }
        }
        currentTable.getCurrentDistribution().rateMyStack();
    }

    public boolean initializePlayer(List<String> tempStorage, Table currentTable, int i) {
        try {
            String line = tempStorage.get(i);
            int numberPlayer = Integer.parseInt(line.substring(5, 6));
            if (currentTable.getIndexFirstPlayerNotNull() != numberPlayer) {
                do {
                    currentTable.getPlayers()[currentTable.getIndexFirstPlayerNotNull()] = new Player();
                } while (currentTable.getIndexFirstPlayerNotNull() == numberPlayer);
            }
            currentTable.getPlayers()[currentTable.getIndexFirstPlayerNotNull()] =
                    tempStorage.get(i + 1).substring(0, 3).equals(":::") ?
                            new Player(true) :
                            new Player(false, true);
            return true;
        } catch (AbsentInfinitePlayerException exc) {
            System.out.println("Error: " + exc);
            return false;
        }
    }

    public Card searchCard(String line) throws IllegalSuitException {
        int numericValue = Integer.parseInt(line.substring(31, 32));
        char suit;
        if (numericValue == 1) {
            numericValue = Integer.parseInt(line.substring(32, 33)) + 10;
            suit = line.charAt(33);
        } else {
            suit = line.charAt(32);
        }
        return new Card(numericValue, suit);
    }


}
