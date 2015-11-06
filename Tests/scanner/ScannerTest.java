package scanner;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.RandomAccessFile;

import static org.junit.Assert.*;

public class ScannerTest {

    public static File tempFile = new File("temp.0");

    public static String[] input = new String[] {
            "VC++ __active_heap = 1",
            "HeapQueryInformation=2",
            "GetProcessAffinityMask=0xF",
            "GetProcessAffinityMask=0xF",
            "tryLock supported",
            "PID=6072"
    };

    @BeforeClass
    public static void setUp() throws Exception {
        if (!tempFile.createNewFile()) {
            fail();
        }
        try (PrintStream file = new PrintStream(new FileOutputStream(tempFile))) {
            for (String line : input) {
                file.print(line + "\r\n");
            }
        } catch (IOException exc) {
            System.out.println("I/O Error: " + exc);
        }
    }

    @AfterClass
    public static void tearDown() throws Exception {
        if (!tempFile.delete()) {
            fail();
        }
    }

    @Test
    public void testReadReverseFile() throws Exception {
        Scanner scanner = new Scanner();
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(tempFile, "r")) {
            for (int i = input.length - 1; i >= 0; i--) {
                String line = scanner.readReverseFile(randomAccessFile);
                if (!line.equals(input[i])) {
                    randomAccessFile.close();
                    fail();
                }
            }
        } catch (IOException exc) {
            System.out.println("I/O Error: " + exc);
        }
    }
}