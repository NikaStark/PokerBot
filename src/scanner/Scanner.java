package scanner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.util.Date;

public class Scanner {

    public void scanner(File fileInput) {
//        int i = 0;
        while (true) {
            try (RandomAccessFile randomAccessFile = new RandomAccessFile(fileInput, "r")) {
                if (timeToReading(fileInput)) {
                    long t1=System.nanoTime();
                    cleaningLogs(randomAccessFile);
                    long t2 = System.nanoTime();
                    long elapsedTime = t2-t1;
                    System.out.println("Elapsed time was "+elapsedTime+" ns");
                    break;
//                } else {
//                    wait(1000);
//                    System.out.println(i++);
                }
            } catch (IOException exc) {
                System.out.println("I/O Error: " + exc);
//            } catch (InterruptedException exc) {
//                System.out.println("Thread error: " + exc);
//            } catch (IllegalMonitorStateException exc) {
//                System.out.println("Thread error: " + exc);
            }
        }
    }

    public void cleaningLogs(RandomAccessFile randomAccessFile) {
        final String pathOutput = "C:\\Users\\Alex\\Desktop\\logFile3.3.0";
        try (PrintStream outputFile = new PrintStream(new FileOutputStream(pathOutput))) {
            String tempString;
            while ((tempString = readReverseFile(randomAccessFile)) != null) {
                if (filtering(tempString)) {
                    outputFile.println(tempString);
                }
            }
        } catch (IOException exc) {
            System.out.println("I/O Error: " + exc);
        }
    }

    public boolean filtering(final String tempString) {
        char firstChar = tempString.charAt(0);
        char secondChar = tempString.charAt(1);
        if (firstChar != '<' || secondChar != '-') {
            if (firstChar != '-' || secondChar != '>') {
                if (firstChar != 'M' || (secondChar != 's')) {
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
                } while (!filtering(currentLastLine));
                if (currentLastLine.charAt(0) == 'T' && currentLastLine.charAt(1) == 'a') {
                    return true;
                }
            }
        }
        return false;
    }

    public String readReverseFile(RandomAccessFile inputFile) throws IOException {
        System.out.println(inputFile.getFilePointer());
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
            readerPointer--;
            inputFile.seek(readerPointer);
        } while (!eol);
        System.out.println(inputFile.getFilePointer());
        return line.reverse().toString();
    }

}
