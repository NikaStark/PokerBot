package inputer;

import engine.models.Distribution;
import engine.models.Game;
import engine.models.Table;
import outer.Outer;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Scanner {

    private static final String[] filters = new String[] {
            "<- ",
            "-> ",
            "Msg",
            "Com",
            "[20",
            "Tab",
            "OnT",
            "  n",
            "myC"
    };

    public List<String> scanner(File fileInput) {
        while (true) {
            try (RandomAccessFile randomAccessFile = new RandomAccessFile(fileInput, "r")) {
                if (isTimeToReading(fileInput)) {
                    return readingLogs(randomAccessFile);
                }
            } catch (IOException exc) {
                System.out.println("I/O Error: " + exc);
            }
        }
    }

    protected List<String> readingLogs(RandomAccessFile randomAccessFile) throws IOException {
        List<String> tempStorage = new ArrayList<>();
        String tempString;
        while (!isStartOfDistribution(tempString = readReverseFile(randomAccessFile))) {
            if (isImportant(tempString)) {
               tempStorage.add(tempString);
            }
        }
        tempStorage.add(tempString);
        if (isTableNew(tempString)) {
            Game.TABLES.add(new Table(tempString.substring(19), searchMaxTablePlayers(randomAccessFile),
                    searchSmallBlind(randomAccessFile), new Distribution(tempString.substring(6, 18)),
                    Outer.getForegroundWindow()));
        }
        Collections.reverse(tempStorage);
        return tempStorage;
    }

    protected int searchMaxTablePlayers(RandomAccessFile randomAccessFile) throws IOException {
        String tempString;
        int count;
        while ((tempString = readReverseFile(randomAccessFile)) != null) {
            if (tempString.length() >= 3 && tempString.substring(0, 3).equals("max")) {
                count = Integer.parseInt(tempString.substring(18, 19));
                if (count == 2 || count == 6 || count == 9) {
                    return count;
                }
            }
        }
        throw new RuntimeException();
    }

    protected int searchSmallBlind(RandomAccessFile randomAccessFile) throws IOException {
        String tempString;
        int count = -1;
        while ((tempString = randomAccessFile.readLine()) != null) {
            String subString = tempString.length() >= 5 ? tempString.substring(1, 4) : "";
            if (subString.equals("'Q'") || subString.equals("'P'")) {
                count = Integer.parseInt(tempString.substring(5, 9));
                break;
            }
        }
        return count;
    }

    protected boolean isTableNew(final String tempString) {
        if (Game.TABLES.size() != 0) {
            String tempHexKey = tempString.substring(19);
            for (Table table : Game.TABLES) {
                if (tempHexKey.equals(table.getHexKey())) {
                    return false;
                }
            }
        }
        return true;
    }

    protected boolean isStartOfDistribution(final String tempString) {
        return tempString.length() >= 4 && tempString.substring(0, 4).equals("Game");
    }

    protected boolean isImportant(final String tempString) {
        if (tempString.length() >= 2) {
            String subString = tempString.substring(0,3);
            for (String notImportant : Scanner.filters) {
                if (subString.equals(notImportant)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    protected boolean isTimeToReading(final File fileInput) throws IOException {
        Date currentDate = new Date();
        Date fileDate = new Date(fileInput.lastModified());
        if (currentDate.after(fileDate)) {
            try (RandomAccessFile randomAccessFile = new RandomAccessFile(fileInput, "r")) {
                String currentLastLine;
                do {
                    currentLastLine = readReverseFile(randomAccessFile);
                } while (!isImportant(currentLastLine));
                String subString = currentLastLine.length() >= 3 ? currentLastLine.substring(1, 4) : "";
                if (subString.equals("'*'") || subString.equals("'C'") || subString.equals("'E'")) {
                    return true;
                }
            }
        }
        return false;
    }

    protected String readReverseFile(RandomAccessFile inputFile) throws IOException {
        StringBuilder line = new StringBuilder();
        boolean eol = false;
        long readerPointer;
        int symbol;
        if ((readerPointer = inputFile.getFilePointer()) == 0) {
            inputFile.seek(readerPointer = inputFile.length() - 1);
        }
        do {
            switch (symbol = inputFile.read()) {
                case '\r':
                case '\n':
                    if (!line.toString().isEmpty()) {
                        eol = true;
                    }
                    break;
                default:
                    line.append((char)symbol);
                    break;
            }
            if (readerPointer == 0) {
                break;
            }
            readerPointer--;
            inputFile.seek(readerPointer);
        } while (!eol);
        return line.reverse().toString();
    }

}