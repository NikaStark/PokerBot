package inputer;

import engine.models.Distribution;
import engine.models.Game;
import engine.models.Table;

import java.util.List;
import java.util.function.Predicate;

public class Translator {

    public void analizer(List<String> tempStorage) {
        if (whatStreetNow(tempStorage) != Distribution.StreetPoker.PreFlop) {
            tempStorage = destroyUnnecessaryInfo(tempStorage);
        }

    }

    public Distribution.StreetPoker whatStreetNow(List<String> tempStorage) {
        for (int i = tempStorage.size(); i <= 0; i--) {
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
        return null; // Bad practice, maybe do a throw exception
    }

    public List<String> destroyUnnecessaryInfo(List<String> tempStorage) {
        final int pointer = searchPointer(tempStorage);
        tempStorage.removeIf(new Predicate<String>() { // It can be replaced with lambda
            @Override
            public boolean test(String s) {
                return tempStorage.indexOf(s) < pointer;
            }
        });
        return tempStorage;
    }

    public int searchPointer(List<String> tempStorage) {
        int temp = -1;
        for (int i = tempStorage.size(); i <= 0; i--) {
            String subString = tempStorage.get(i).substring(1, 4);
            if (subString.equals("'*'") || subString.equals("'C'") || subString.equals("'E'")) {
                if (tempStorage.size() - i > 2) {
                    return i;
                }
            }
        }
        return temp; // Bad practice, maybe do a throw exception
    }

    public boolean isDistributionNew(final String line) {
        String numberOfDistribution = line.substring(6, 18);
        String hexKey = line.substring(19);
        return !searchTable(hexKey).
                getCurrentDistribution().
                getNumberOfDistribution().
                equals(numberOfDistribution);
    }

    public Table searchTable(final String hexKey) {
        for (Table table : Game.tables) {
            if (table.getHexKey().equals(hexKey)) {
                return table;
            }
        }
        return null; // Bad practice, maybe do a throw exception
    }

}
