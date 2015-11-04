package scanner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.util.Date;

public class Scanner {


    public boolean activeWindow(final File fileInput) throws IOException {
        Date currentDate = new Date();
        Date fileDate = new Date(fileInput.lastModified());
        if (currentDate.after(fileDate)) {
            try (RandomAccessFile randomAccessFile = new RandomAccessFile(fileInput, "r")) {
                String currentLastLine = readReverseFile(randomAccessFile);
                if (currentLastLine.charAt(0) == ' ' && currentLastLine.charAt(1) == '\'' &&
                        currentLastLine.charAt(2) == '*') {
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
            readerPointer = inputFile.length() - 1;
        }
        while (!eol) {
            inputFile.seek(readerPointer);
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
        }
        inputFile.seek(readerPointer);
        return line.reverse().toString();
    }

}
