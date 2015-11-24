package scanner;


import engine.models.Game;
import engine.models.Table;
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
            "maxTablePlayers = 6",
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
    public void testSearchMaxTablePlayers() throws Exception {
        final Scanner scanner = new Scanner();
        final int expectedResult = 6;
        int actualResult = -1;
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(tempFile, "r")) {
            actualResult = scanner.searchMaxTablePlayers(randomAccessFile);
        } catch (IOException exc) {
            System.out.println("I/O Error: " + exc);
        }
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testIsTableNewWhenTableAbsent() throws Exception {
        final Game game = new Game();
        final Scanner scanner = new Scanner();
        final String tempString = "Game #142837817463 005D0A28";
        final boolean actualResult = scanner.isTableNew(tempString, game);
        assertTrue(actualResult);
    }

    @Test
    public void testIsTableNewWhenTableNew() throws Exception {
        final Game game = new Game();
        final Scanner scanner = new Scanner();
        final Table table = new Table("321DF898", 6);
        game.addTables(table);
        final String tempString = "Game #142837817463 005D0A28";
        final boolean actualResult = scanner.isTableNew(tempString, game);
        assertTrue(actualResult);
    }

    @Test
    public void testIsTableNewWhenTableNotNew() throws Exception {
        final Game game = new Game();
        final Scanner scanner = new Scanner();
        final Table table = new Table("005D0A28", 6);
        game.addTables(table);
        final String tempString = "Game #142837817463 005D0A28";
        final boolean actualResult = scanner.isTableNew(tempString, game);
        assertFalse(actualResult);
    }

    @Test
    public void testReadReverseFile() throws Exception {
        final Scanner scanner = new Scanner();
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