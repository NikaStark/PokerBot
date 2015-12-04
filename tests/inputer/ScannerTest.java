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
import java.util.List;

import static org.junit.Assert.*;

public class ScannerTest {

    public static File tempFile = new File("temp.0");

    public static String[] input = new String[] {
            "maxTablePlayers = 6",
            "Game #144453195279 0006098E",
            "[2015/11/27 14:54:44]",
            " 'Q' 1000",
            " '_' 0",
            " ' ' 0",
            "9314 'Q' 1000 - 0",
            "  sit0",
            "::: 10h",
            "  nCards=2",
            "::: 7s",
            "  sit1",
            "------ 0006098E",
            "  nCards=2",
            "  sit3",
            "  nCards=2",
            "  sit4",
            "  nCards=2",
            "  sit5",
            "  nCards=2",
            "  dealerPos=0",
            "myCards.changed=1",
            ":::TableViewImpl::updateMyCard() 10h (0) [6098E]",
            ":::TableViewImpl::updateMyCard() 7s (1) [6098E]",
            "  sit0",
            "  nCards=2",
            "  sit1",
            "  nCards=2",
            "  sit3",
            "  nCards=2",
            "  sit4",
            "  nCards=2",
            "  sit5",
            "  nCards=2",
            ":::TableViewImpl::updateMyCard() 10h (0) [6098E]",
            ":::TableViewImpl::updateMyCard() 7s (1) [6098E]",
            "[2015/11/27 14:54:45]",
            "box[ 0 ] 0",
            " act 'c' -1",
            " act 'F' -1",
            "box[ 2 ] 0",
            " act 'c' -1",
            "box[ 3 ] 0",
            " act 'c' -1",
            " act 'C' -1",
            "[2015/11/27 14:54:46]",
            " 'F' 0",
            " 'c' 0",
            " '*' 'E' 2000, 40000, 1000, 100"
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
    public void testScanner() throws Exception {
        final Scanner scanner = new Scanner();
        final String[] expectedAnswer = new String[input.length - 1];
        System.arraycopy(input, 1, expectedAnswer, 0, expectedAnswer.length);
        final List<String> tempList = scanner.scanner(tempFile);
        final String[] actualAnswer = tempList.toArray(new String[tempList.size()]);
        assertArrayEquals(expectedAnswer,actualAnswer);
    }

    @Test
    public void testReadingLogs() throws Exception {
        final Scanner scanner = new Scanner();
        final String[] expectedAnswer = new String[input.length - 1];
        System.arraycopy(input, 1, expectedAnswer, 0, expectedAnswer.length);
        String[] actualAnswer;
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(tempFile, "r")) {
            List<String> tempList = scanner.readingLogs(randomAccessFile);
            actualAnswer = tempList.toArray(new String[tempList.size()]);
        }
        assertArrayEquals(expectedAnswer,actualAnswer);
    }

    @Test
    public void testSearchBigBlind() throws Exception {
        final Scanner scanner = new Scanner();
        final int expectedAnswer = 1000;
        int actualAnswer = -1;
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(tempFile, "r")) {
            actualAnswer = scanner.searchBigBlind(randomAccessFile);
        } catch (IOException exc) {
            System.out.println("I/O Error: " + exc);
        }
        assertEquals(expectedAnswer,actualAnswer);
    }

    @Test
    public void testSearchMaxTablePlayers() throws Exception {
        final Scanner scanner = new Scanner();
        final int expectedAnswer = 6;
        int actualAnswer = -1;
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(tempFile, "r")) {
            actualAnswer = scanner.searchMaxTablePlayers(randomAccessFile);
        } catch (IOException exc) {
            System.out.println("I/O Error: " + exc);
        }
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testIsTableNewWhenTableAbsent() throws Exception {
        final Scanner scanner = new Scanner();
        final String tempString = "Game #142837817463 005D0A28";
        final boolean actualAnswer = scanner.isTableNew(tempString);
        assertTrue(actualAnswer);
    }

    @Test
    public void testIsTableNewWhenTableNew() throws Exception {
        final Scanner scanner = new Scanner();
        final Table table = new Table("321DF898", 10, 6);
        Game.tables.add(table);
        final String tempString = "Game #142837817463 005D0A29";
        final boolean actualAnswer = scanner.isTableNew(tempString);
        assertTrue(actualAnswer);
    }

    @Test
    public void testIsTableNewWhenTableNotNew() throws Exception {
        final Scanner scanner = new Scanner();
        final Table table = new Table("005D0A28", 10,  6);
        Game.tables.add(table);
        final String tempString = "Game #142837817463 005D0A28";
        final boolean actualResult = scanner.isTableNew(tempString);
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