package engine.models;

import engine.models.Enums.Suits;
import engine.models.exceptions.ReassigningFieldException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class DistributionTest {

    @Test(expected = ReassigningFieldException.class)
    public void testSetFlopCardsWhenAlreadyAssigned() throws Exception {
        final Card[] inputPlayersCards = new Card[]{new Card(10, Suits.DIAMONDS), new Card(10, Suits.SPADES)};
        final Distribution distribution = new Distribution(inputPlayersCards, null);
        final Card[] inputFlopCards = new Card[]{new Card(3, Suits.DIAMONDS), new Card(4, Suits.DIAMONDS), new Card(5, Suits.DIAMONDS)};
        distribution.setFlopCards(inputFlopCards);
        distribution.setFlopCards(inputFlopCards);
    }

    @Test(expected = ReassigningFieldException.class)
    public void testSetTurnCardAlreadyAssigned() throws Exception {
        final Card[] inputPlayersCards = new Card[]{new Card(10, Suits.DIAMONDS), new Card(10, Suits.SPADES)};
        final Distribution distribution = new Distribution(inputPlayersCards, null);
        final Card inputTurnCards = new Card(7, Suits.CLUBS);
        distribution.setTurnCard(inputTurnCards);
        distribution.setTurnCard(inputTurnCards);
    }

    @Test(expected = ReassigningFieldException.class)
    public void testSetRiverCardAlreadyAssigned() throws Exception {
        final Card[] inputPlayersCards = new Card[]{new Card(10, Suits.DIAMONDS), new Card(10, Suits.SPADES)};
        final Distribution distribution = new Distribution(inputPlayersCards, null);
        final Card inputRiverCards = new Card(9, Suits.HERTZ);
        distribution.setRiverCard(inputRiverCards);
        distribution.setRiverCard(inputRiverCards);
    }

    @Test
    public void testRemoveKnownCardsFromDeck() throws Exception {
        final Card[] inputPlayersCards = new Card[]{new Card(4, Suits.HERTZ), new Card(6, Suits.SPADES)};
        final Card[] inputFlopCards = new Card[]{new Card(7, Suits.CLUBS), new Card(2, Suits.DIAMONDS), new Card(9, Suits.HERTZ)};
        final Card inputTurnCad = new Card(5, Suits.SPADES);
        final ArrayList<Card> expectedAnswer = new ArrayList<>(Arrays.asList(Game.DECK_OF_CARDS));
        expectedAnswer.removeAll(new ArrayList<>(Arrays.asList(inputPlayersCards)));
        expectedAnswer.removeAll(new ArrayList<>(Arrays.asList(inputFlopCards)));
        expectedAnswer.remove(inputTurnCad);
        final Distribution distribution = new Distribution(inputPlayersCards, null);
        distribution.setFlopCards(inputFlopCards);
        distribution.setTurnCard(inputTurnCad);
        final ArrayList<Card> actualAnswer = distribution.getCurrentDeck();
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testAllCurrentKnownCards() throws Exception {
        final Card[] inputPlayersCards = new Card[]{new Card(7, Suits.CLUBS), new Card(10, Suits.DIAMONDS)};
        final Card[] inputFlopCards = new Card[]{new Card(7, Suits.CLUBS), new Card(2, Suits.DIAMONDS), new Card(9, Suits.HERTZ)};
        final Card[] expectedAnswer = new Card[]{new Card(7, Suits.CLUBS), new Card(10, Suits.DIAMONDS), new Card(7, Suits.CLUBS),
                new Card(2, Suits.DIAMONDS), new Card(9, Suits.HERTZ)};
        final Distribution distribution = new Distribution(inputPlayersCards, null);
        final Distribution.StreetPoker inputStreetPoker = Distribution.StreetPoker.FLOP;
        distribution.setFlopCards(inputFlopCards);
        final Card[] actualAnswer = distribution.allCurrentKnownCards(inputStreetPoker);
        assertArrayEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testCounterKnownCards() throws Exception {
        final Card[] inputPlayersCards = new Card[]{new Card(7, Suits.CLUBS), new Card(10, Suits.DIAMONDS)};
        final Distribution.StreetPoker inputStreetPoker = Distribution.StreetPoker.TURN;
        final int expectedAnswer = 6;
        final Distribution distribution = new Distribution(inputPlayersCards, null);
        final int actualAnswer = distribution.counterKnownCards(inputStreetPoker);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testCurrentStreetPoker() throws Exception {
        final Card[] inputPlayersCards = new Card[]{new Card(7, Suits.CLUBS), new Card(10, Suits.DIAMONDS)};
        final Distribution.StreetPoker expectedAnswer = Distribution.StreetPoker.PRE_FLOP;
        final Distribution distribution = new Distribution(inputPlayersCards, null);
        final Distribution.StreetPoker actualAnswer = distribution.currentStreetPoker();
        assertEquals(expectedAnswer, actualAnswer);
    }
}