package inputer;

import engine.models.Distribution;
import engine.models.Game;
import engine.models.Table;
import engine.models.exceptions.AbstractPokerBotException;
import engine.models.exceptions.NotFoundTableException;

import java.util.List;

public class Translator {

    public void translator(List<String> tempStorage) {
        tempStorage = whatStreetNow(tempStorage) == Distribution.StreetPoker.PreFlop ?
        destroyReiterateInfo(tempStorage) : destroyUnnecessaryInfo(tempStorage);

    }

    public Distribution.StreetPoker whatStreetNow(List<String> tempStorage) {
        for (int i = tempStorage.size() - 1; i >= 0; i--) {
            String currentLine = tempStorage.get(i);
            if (currentLine.substring(18, currentLine.indexOf(' ')).equals("updateMyCard()")) {
                return Distribution.StreetPoker.PreFlop;
            } else {
                int position = currentLine.lastIndexOf('(');
                switch (currentLine.substring(position, position + 3)) {
                    case "(2)": return Distribution.StreetPoker.Flop;
                    case "(3)": return Distribution.StreetPoker.Turn;
                    case "(4)": return Distribution.StreetPoker.River;
                }
            }
        }
        return null; //TODO Bad practice, maybe do a throw exception
    }

    public List<String> destroyReiterateInfo(List<String> tempStorage) {
        for (int i = 0; i < tempStorage.size(); i++) {
            if (tempStorage.get(i).substring(0, 3).equals(":::")) {
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

}
