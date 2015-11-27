package inputer;

import engine.models.Game;
import engine.models.Table;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Date;

public class Scanner {

    public boolean scanner(File fileInput, Game game) {
        while (true) {
            try (RandomAccessFile randomAccessFile = new RandomAccessFile(fileInput, "r")) {
                if (timeToReading(fileInput)) {
//                    long t1=System.nanoTime();
                    readingLogs(randomAccessFile, game);
//                    long t2 = System.nanoTime();
//                    long elapsedTime = t2-t1;
//                    System.out.println("Elapsed time was "+elapsedTime+" ns");
                    break;
//                } else {
//                    wait(1000);
                }
            } catch (IOException exc) {
                System.out.println("I/O Error: " + exc);
//            } catch (InterruptedException exc) {
//                System.out.println("Thread error: " + exc);
//            } catch (IllegalMonitorStateException exc) {
//                System.out.println("Thread error: " + exc);
            }
        }
        return true;
    }


    public void readingLogs(RandomAccessFile randomAccessFile, Game game) throws IOException {
        final String pathOutput = "C:\\Users\\Alex\\Desktop\\logFile3.3.0";
        ArrayList<String> tempStorage = new ArrayList<>();
        String tempString;
        while (!isStartOfDistribution(tempString = readReverseFile(randomAccessFile))) {
            if (isImportant(tempString)) {
               tempStorage.add(tempString);
            }
        }
        tempStorage.add(tempString);
        if (isTableNew(tempString, game)) {
            String hexKeyOfNewTable = tempString.substring(19);
            int maxTablePlayersOfNewTable = searchMaxTablePlayers(randomAccessFile);
            game.addTables(new Table(hexKeyOfNewTable, maxTablePlayersOfNewTable));
        }
        try (PrintStream outputFile = new PrintStream(new FileOutputStream(pathOutput))) {
            for (int i = tempStorage.size() - 1; i >= 0; i--) {
                outputFile.println(tempStorage.get(i));
            }
        } catch (IOException exc) {
            System.out.println("I/O Error: " + exc);
        }
    }


    public int searchMaxTablePlayers(RandomAccessFile randomAccessFile) throws IOException {
        String tempString;
        char count = 0;
        while ((tempString = readReverseFile(randomAccessFile)) != null) {
            if (tempString.length() >= 3 && tempString.substring(0, 3).equals("max")) {
                count = tempString.charAt(18);
                switch(count) {
                    case '2': return 2;
                    case '6': return 6;
                    case '9': return 9;
                }
            }
        }
        return count;
    }

    public boolean isTableNew(final String tempString, Game game) {
        if (game.getTables().size() != 0) {
            String tempHexKey = tempString.substring(19);
            for (Table table : game.getTables()) {
                if (tempHexKey.equals(table.getHexKey())) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isStartOfDistribution(final String tempString) {
        return tempString.length() >= 4 && tempString.substring(0, 4).equals("Game");
    }

    public boolean isImportant(final String tempString) {
        if (tempString.length() >= 2) {
            String subString = tempString.substring(0,2);
            if (!subString.equals("<-")) {
                if (!subString.equals("->")) {
                    if (!subString.equals("Ms")) {
                        if (!subString.equals("Co")) {
                            if (!subString.equals("Ta")) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean timeToReading(final File fileInput) throws IOException {
        Date currentDate = new Date();
        Date fileDate = new Date(fileInput.lastModified());
        if (currentDate.after(fileDate)) {
            try (RandomAccessFile randomAccessFile = new RandomAccessFile(fileInput, "r")) {
                String currentLastLine;
                do {
                    currentLastLine = readReverseFile(randomAccessFile);
                } while (!isImportant(currentLastLine));
                String subString = currentLastLine.length() >= 3 ? currentLastLine.substring(1, 4) : "";
                if (subString.equals("'*'") || subString.equals("'C'")) {
                    return true;
                }
            }
        }
        return false;
    }

    public String readReverseFile(RandomAccessFile inputFile) throws IOException {
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