package inputer;

import engine.models.Card;
import engine.models.Distribution;
import engine.models.Enums.Suits;
import engine.models.Game;
import engine.models.Table;
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
        final Card[] playersCards = new Card[]{new Card(10, Suits.Hertz), new Card(7, Suits.Spades)};
        final Distribution distribution = new Distribution(playersCards, numberDistribution);
        final String hexKey = "0006098E";
        final Table table = new Table(hexKey, 6, 1000);
        table.setCurrentDistribution(distribution);
        Game.tables.add(table);
    }

    @After
    public void tearDown() throws Exception {
        Game.tables.clear();
    }

    @Test
    public void testWhatStreetNow() throws Exception {
        final Translator translator = new Translator();
        final List<String> tempStorage = new ArrayList<>(Arrays.asList(new String[] {
                "Game #144453195279 0006098E",
                "  sit0",
                "::: 10h",
                "  nCards=2",
                "::: 7s",
                "  sit1",
                "  nCards=2",
                "  sit3",
                "  nCards=2",
                "  sit4",
                "  nCards=2",
                "  sit5",
                "  nCards=2",
                "  dealerPos=0",
                ":::TableViewImpl::updateMyCard() 10h (0) [6098E]",
                ":::TableViewImpl::updateMyCard() 7s (1) [6098E]",
                ":::TableViewImpl::updateMyCard() 10h (0) [6098E]",
                ":::TableViewImpl::updateMyCard() 7s (1) [6098E]"
        }));
        final Distribution.StreetPoker expectedAnswer = Distribution.StreetPoker.PreFlop;
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
                "  nCards=2",
                "::: 7s",
                "  sit1",
                "  nCards=2",
                "  sit3",
                "  nCards=2",
                "  sit4",
                "  nCards=2",
                "  sit5",
                "  nCards=2",
                "  dealerPos=0",
                ":::TableViewImpl::updateMyCard() 10h (0) [6098E]",
                ":::TableViewImpl::updateMyCard() 7s (1) [6098E]",
                ":::TableViewImpl::updateMyCard() 10h (0) [6098E]",
                ":::TableViewImpl::updateMyCard() 7s (1) [6098E]"
        }));
        final String[] expectedAnswer = new String[] {
                "Game #144453195279 0006098E",
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
                "  dealerPos=0",
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
        final Card[] inputPlayersCards = new Card[]{new Card(7, Suits.Clubs), new Card(10, Suits.Diamonds)};
        final Distribution distribution = new Distribution(inputPlayersCards, number);
        final Table table = new Table("00400A2A", 1, 1);
        table.setCurrentDistribution(distribution);
        Game.tables.add(table);
        final boolean actualAnswer = translator.isDistributionNew(number);
        assertTrue(actualAnswer);
    }

    @Test
    public void testIsDistributionNewWhenFalse() throws Exception {
        final Translator translator = new Translator();
        final String number = "Game #143284520901 00400A2A";
        final Table table = new Table("00400A2A", 1, 1);
        Game.tables.add(table);
        final boolean actualAnswer = translator.isDistributionNew(number);
        assertFalse(actualAnswer);
    }

    @Test
     public void testSearchTableExisting() throws Exception {
        final Translator translator = new Translator();
        final String hexKey = "00400A2A";
        final Table expectedAnswer = new Table(hexKey, 1, 1);
        Game.tables.add(expectedAnswer);
        final Table actualAnswer = translator.searchTable(hexKey);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test(expected = NotFoundTableException.class)
    public void testSearchTableNotExisting() throws Exception {
        final Translator translator = new Translator();
        final String hexKey = "00400A2A";
        translator.searchTable(hexKey);
    }

}