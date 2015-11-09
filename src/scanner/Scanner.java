package scanner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Date;

public class Scanner {

    public boolean scanner(File fileInput) {
        while (true) {
            try (RandomAccessFile randomAccessFile = new RandomAccessFile(fileInput, "r")) {
                if (timeToReading(fileInput)) {
//                    long t1=System.nanoTime();
                    readingLogs(randomAccessFile);
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


    public void readingLogs(RandomAccessFile randomAccessFile) throws IOException {
        final String pathOutput = "C:\\Users\\Alex\\Desktop\\logFile3.3.0";
        ArrayList<String> tempStorage = new ArrayList<>();
        String tempString;
        while (!isStartOfDistribution(tempString = readReverseFile(randomAccessFile))) {
            if (isImportant(tempString)) {
               tempStorage.add(tempString);
            }
        }
        try (PrintStream outputFile = new PrintStream(new FileOutputStream(pathOutput))) {
            for (int i = tempStorage.size() - 1; i >= 0; i++) {
                outputFile.println(tempStorage.get(i));
            }
        } catch (IOException exc) {
            System.out.println("I/O Error: " + exc);
        }
    }

    public boolean isStartOfDistribution(final String tempString) {
       return tempString.charAt(0) != 'G' || tempString.charAt(1) != 'a';
    }

    public boolean isImportant(final String tempString) {
        char firstChar = tempString.charAt(0);
        char secondChar = tempString.charAt(1);
        if (firstChar != '<' || secondChar != '-') {
            if (firstChar != '-' || secondChar != '>') {
                if (firstChar != 'M' || secondChar != 's') {
                    if (firstChar != 'C' || secondChar != 'o') {
                        return true;
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
                if (currentLastLine.charAt(0) == 'T' && currentLastLine.charAt(1) == 'a') {
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
                return null;
            }
            readerPointer--;
            inputFile.seek(readerPointer);
        } while (!eol);
        return line.reverse().toString();
    }

}