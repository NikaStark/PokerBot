package inputer;


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
            "PID=6072",
            " '*' 'E' 2000, 49000, 1000, 100"
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
        final int expectedAnswer = 6;
        int actualResult = -1;
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(tempFile, "r")) {
            actualResult = scanner.searchMaxTablePlayers(randomAccessFile);
        } catch (IOException exc) {
            System.out.println("I/O Error: " + exc);
        }
        assertEquals(expectedAnswer, actualResult);
    }

    @Test
    public void testIsTableNewWhenTableAbsent() throws Exception {
        final Game game = new Game();
        final Scanner scanner = new Scanner();
        final String tempString = "Game #142837817463 005D0A28";
        final boolean actualAnswer = scanner.isTableNew(tempString, game);
        assertTrue(actualAnswer);
    }

    @Test
    public void testIsTableNewWhenTableNew() throws Exception {
        final Game game = new Game();
        final Scanner scanner = new Scanner();
        final Table table = new Table("321DF898", 6);
        game.addTables(table);
        final String tempString = "Game #142837817463 005D0A28";
        final boolean actualAnswer = scanner.isTableNew(tempString, game);
        assertTrue(actualAnswer);
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
    public void testIsStartOfDistributionWhenStart() throws Exception {
        final Scanner scanner = new Scanner();
        final String inputTempString = "Game #142837817463 005D0A28";
        final boolean actualAnswer = scanner.isStartOfDistribution(inputTempString);
        assertTrue(actualAnswer);
    }

    @Test
    public void testIsStartOfDistributionWhenNotStart() throws Exception {
        final Scanner scanner = new Scanner();
        final String inputTempString = "tryLock supported";
        final boolean actualAnswer = scanner.isStartOfDistribution(inputTempString);
        assertFalse(actualAnswer);
    }

    @Test
    public void testIsImportantWhenTrue() throws Exception {
        final Scanner scanner = new Scanner();
        final String inputTempString = "sit0";
        final boolean actualAnswer = scanner.isImportant(inputTempString);
        assertTrue(actualAnswer);
    }

    @Test
    public void testIsImportantWhenFalse() throws Exception {
        final Scanner scanner = new Scanner();
        final String inputTempString = "Table::AdvActions::config() 0865B768";
        final boolean actualAnswer = scanner.isImportant(inputTempString);
        assertFalse(actualAnswer);
    }

    @Test
     public void testTimeToReadingWhenTrue() throws Exception {
        final Scanner scanner = new Scanner();
        final boolean actualAnswer = scanner.timeToReading(tempFile);
        assertTrue(actualAnswer);
    }

    @Test
    public void testTimeToReadingWhenFalse() throws Exception {
        final Scanner scanner = new Scanner();
        final String newLastLineOfTempFile = " act 'C' 24000";
        try (PrintStream file = new PrintStream(new FileOutputStream(tempFile))) {
            file.print(newLastLineOfTempFile + "\r\n");
        } catch (IOException exc) {
            System.out.println("I/O Error: " + exc);
        }
        final boolean actualAnswer = scanner.timeToReading(tempFile);
        assertFalse(actualAnswer);
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