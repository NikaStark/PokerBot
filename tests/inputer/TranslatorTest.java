package inputer;

import engine.models.*;
import engine.models.Enums.Suits;
import engine.models.exceptions.IllegalSuitException;
import engine.models.exceptions.NotFoundTableException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


public class TranslatorTest {

    @Before
    public void setUp() throws Exception {
        final String numberDistribution = "144453195279";
        final Card[] playersCards = new Card[]{new Card(10, Suits.HERTZ), new Card(7, Suits.SPADES)};
        final Distribution distribution = new Distribution(numberDistribution);
        for (int i = 0; i < playersCards.length; i++) {
            distribution.addPlayersCard(playersCards[i], i);
        }
        final String hexKey = "0006098E";
        final Table table = new Table(hexKey, 6, 1000, distribution, null);
        Game.TABLES.add(table);
    }

    @After
    public void tearDown() throws Exception {
        Game.TABLES.clear();
    }

    @Test
    public void testWhatStreetNow() throws Exception {
        final Translator translator = new Translator();
        final List<String> tempStorage = new ArrayList<>(Arrays.asList(new String[] {
                "Game #144453195279 0006098E",
                "  sit0",
                "::: 10h",
                "::: 7s",
                "  sit1",
                "  sit3",
                "  sit4",
                "  sit5",
                "  dealerPos=0",
                ":::TableViewImpl::updateMyCard() 10h (0) [6098E]",
                ":::TableViewImpl::updateMyCard() 7s (1) [6098E]",
                ":::TableViewImpl::updateMyCard() 10h (0) [6098E]",
                ":::TableViewImpl::updateMyCard() 7s (1) [6098E]"
        }));
        final Distribution.StreetPoker expectedAnswer = Distribution.StreetPoker.PRE_FLOP;
        final Distribution.StreetPoker actualAnswer = translator.whatStreetNow(tempStorage);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testDestroyReiterateInfo() throws Exception {
        final Translator translator = new Translator();
        final List<String> tempStorage = new ArrayList<>(Arrays.asList(new String[] {
                "Game #144453195279 0006098E",
                "  sit0",
                "::: 10h",
                "::: 7s",
                "  sit1",
                "  sit3",
                "  sit4",
                "  sit5",
                "  dealerPos=0",
                ":::TableViewImpl::updateMyCard() 10h (0) [6098E]",
                ":::TableViewImpl::updateMyCard() 7s (1) [6098E]",
                "  sit0",
                "  sit1",
                "  sit3",
                "  sit4",
                "  sit5",
                ":::TableViewImpl::updateMyCard() 10h (0) [6098E]",
                ":::TableViewImpl::updateMyCard() 7s (1) [6098E]"
        }));
        final String[] expectedAnswer = new String[] {
                "Game #144453195279 0006098E",
                "  dealerPos=0",
                "  sit0",
                "  sit1",
                "  sit3",
                "  sit4",
                "  sit5",
                ":::TableViewImpl::updateMyCard() 10h (0) [6098E]",
                ":::TableViewImpl::updateMyCard() 7s (1) [6098E]"
        };
        final List<String> actualAnswer = translator.destroyReiterateInfo(tempStorage);
        assertArrayEquals(expectedAnswer, actualAnswer.toArray(new String[actualAnswer.size()]));
    }

    @Test
    public void testDestroyUnnecessaryInfo() throws Exception {
        final Translator translator = new Translator();
        final List<String> tempStorage = new ArrayList<>(Arrays.asList(new String[] {
                "[2015/11/27 14:54:46]",
                " 'F' 0",
                " 'c' 0",
                " '*' 'E' 2000, 40000, 1000, 100",
                "[2015/10/26 20:56:41]",
                "box[ 0 ] 0",
                " act 'c' -1",
                " act 'F' -1",
                "[2015/10/26 20:56:43]",
                " 'F' 0",
                " 'c' 0",
                " '*' 'B' 1000, 62000, 1000, 100"
        }));
        final String[] expectedAnswer = new String[] {
                "[2015/10/26 20:56:41]",
                "box[ 0 ] 0",
                " act 'c' -1",
                " act 'F' -1",
                "[2015/10/26 20:56:43]",
                " 'F' 0",
                " 'c' 0",
                " '*' 'B' 1000, 62000, 1000, 100"
        };
        final List<String> actualAnswer = translator.destroyUnnecessaryInfo(tempStorage);
        assertArrayEquals(expectedAnswer, actualAnswer.toArray(new String[actualAnswer.size()]));
    }

    @Test
    public void testSearchPointer() throws Exception {
        final Translator translator = new Translator();
        final String[] tempStorage = new String[]{
                "[2015/11/27 14:54:46]",
                " 'F' 0",
                " 'c' 0",
                " '*' 'E' 2000, 40000, 1000, 100",
                "[2015/10/26 20:56:41]",
                "box[ 0 ] 0",
                " act 'c' -1",
                " act 'F' -1",
                "[2015/10/26 20:56:43]",
                " 'F' 0",
                " 'c' 0",
                " '*' 'B' 1000, 62000, 1000, 100"
        };
        final int expectedAnswer = 3;
        final int actualResult = translator.searchPointer(Arrays.asList(tempStorage));
        assertEquals(expectedAnswer, actualResult);
    }

    @Test
    public void testIsDistributionNewWhenTrue() throws Exception {
        final Translator translator = new Translator();
        final String number = "Game #143284520901 00400A2A";
        final Card[] inputPlayersCards = new Card[]{new Card(7, Suits.CLUBS), new Card(10, Suits.DIAMONDS)};
        final Distribution distribution = new Distribution(number);
        for (int i = 0; i < inputPlayersCards.length; i++) {
            distribution.addPlayersCard(inputPlayersCards[i], i);
        }
        final Table table = new Table("00400A2A", 1, 1, distribution, null);
        Game.TABLES.add(table);
        final boolean actualAnswer = translator.isDistributionNew(number);
        assertTrue(actualAnswer);
    }

    @Test
    public void testIsDistributionNewWhenFalse() throws Exception {
        final Translator translator = new Translator();
        final String number = "Game #143284520901 00400A2A";
        final Distribution distribution = new Distribution("143284520901");
        final Table table = new Table("00400A2A", 1, 1, distribution, null);
        Game.TABLES.add(table);
        final boolean actualAnswer = translator.isDistributionNew(number);
        assertFalse(actualAnswer);
    }

    @Test
     public void testSearchTableExisting() throws Exception {
        final Translator translator = new Translator();
        final String hexKey = "00400A2A";
        final Table expectedAnswer = new Table(hexKey, 1, 1, null, null);
        Game.TABLES.add(expectedAnswer);
        final Table actualAnswer = translator.searchTable(hexKey);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test(expected = NotFoundTableException.class)
    public void testSearchTableNotExisting() throws Exception {
        final Translator translator = new Translator();
        final String hexKey = "00400A2A";
        translator.searchTable(hexKey);
    }

    @Test
    public void testSearchCard() throws Exception {
        final Translator translator = new Translator();
        final Card expectedAnswer = new Card(4, Suits.SPADES);
        final String inputString = ":::TableViewImpl::updateMyCard() 4s (0) [6098E]";
        final Card actualAnswer = translator.searchCard(inputString);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test(expected = IllegalSuitException.class)
    public void testSearchWrongCard() throws Exception {
        final Translator translator = new Translator();
        final String inputString = ":::TableViewImpl::updateMyCard() 4a (0) [6098E]";
        translator.searchCard(inputString);
    }

    @Test
    public void testInitializePlayer() throws Exception {
        final Translator translator = new Translator();
        final List<String> inputInfo = new ArrayList<>(Arrays.asList(new String[] {
                "  sit0",
                "  sit1",
                "  sit3",
                "  sit4",
                "  sit5",
        }));
        final Distribution distribution = new Distribution(null);
        final Table table = new Table("00120908", 6, 1000, distribution, null);
        for (String currentLine : inputInfo) {
            translator.initializePlayer(currentLine, table);
        }
        for (int i = 0; i < table.getPlayers().length; i++) {
            assertNotNull(table.getPlayers()[i]);
        }
    }

    @Test
    public void testDefineMySit() throws Exception {
        final Translator translator = new Translator();
        final Table table = new Table(null, 6, 1000, null, null);
        final int numberOfMySit = 3;
        for (int i = 0; i <= numberOfMySit; i++) {
            table.getPlayers()[i] = new Player(true);
        }
        translator.defineMySit(table);
        assertTrue(table.getPlayers()[numberOfMySit].isMySit());
    }

    @Test
    public void testInitializeCard() throws Exception {
        final Translator translator = new Translator();
        final Distribution distribution = new Distribution(null);
        final Table table = new Table(null, 6, 1000, distribution, null);
        table.getPlayers()[0] = new Player(true);
        final String inputLine1 = ":::TableViewImpl::updateMyCard() 10h (0) [120908]";
        translator.initializeCard(inputLine1, table);
        assertTrue(distribution.getCardsOfPlayer()[0].equals(new Card(10, Suits.HERTZ)));
        final String inputLine2 = ":::TableViewImpl::updateMyCard() 7s (1) [120908]";
        translator.initializeCard(inputLine2, table);
        assertTrue(distribution.getCardsOfPlayer()[1].equals(new Card(7, Suits.SPADES)));
    }

    @Test
    public void testInitializeDealerPos() throws Exception {
        final Translator translator = new Translator();
        final Distribution distribution = new Distribution(null);
        final Table table = new Table(null, 6, 1000, distribution, null);
        final String inputLine = "  dealerPos=2";
        int expectedAnswer = Integer.parseInt(inputLine.substring(12));
        translator.initializeDealerPos(inputLine, table);
        int actualAnswer = distribution.getDealerPos();
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testInitializePossibleSteps() throws Exception {
        final Translator translator = new Translator();
        final Distribution distribution = new Distribution(null);
        final Table table = new Table(null, 6, 1000, distribution, null);
        final String inputLine1 = " 'F' 0";
        translator.initializePossibleSteps(inputLine1, table, inputLine1.charAt(2));
        assertTrue(distribution.getCurrentPossibleSteps().containsKey(Distribution.PossibleSteps.FOLD));
        assertTrue(Arrays.equals(distribution.getCurrentPossibleSteps().
                get(Distribution.PossibleSteps.FOLD), new Integer[]{0}));
        final String inputLine2 = " 'c' 0";
        translator.initializePossibleSteps(inputLine2, table, inputLine2.charAt(2));
        assertTrue(distribution.getCurrentPossibleSteps().containsKey(Distribution.PossibleSteps.CHECK));
        assertTrue(Arrays.equals(distribution.getCurrentPossibleSteps().
                get(Distribution.PossibleSteps.CHECK), new Integer[]{0}));
        final String inputLine3 = " '*' 'B' 1000, 62000, 1000, 100";
        translator.initializePossibleSteps(inputLine3, table, inputLine3.charAt(2));
        assertTrue(distribution.getCurrentPossibleSteps().containsKey(Distribution.PossibleSteps.RAISE));
        assertTrue(Arrays.equals(distribution.getCurrentPossibleSteps().
                get(Distribution.PossibleSteps.RAISE), new Integer[]{1000, 62000, 1000, 100}));
    }

}