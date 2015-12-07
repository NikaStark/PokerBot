package engine.models;

import engine.models.Enums.Suits;
import engine.models.exceptions.ReassigningFieldException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class DistributionTest {

    @Test
    public void testGetCardsOfPlayer() throws Exception {
        final Card[] inputCards = new Card[]{new Card(10, Suits.Diamonds), new Card(10, Suits.Spades)};
        final Distribution distribution = new Distribution(inputCards, null);
        final Card[] actualCards = distribution.getCardsOfPlayer();
        assertArrayEquals(inputCards, actualCards);
    }

    @Test
    public void testGetFlopCards() throws Exception {
        final Card[] inputPlayersCards = new Card[]{new Card(10, Suits.Diamonds), new Card(10, Suits.Spades)};
        final Distribution distribution = new Distribution(inputPlayersCards, null);
        final Card[] inputFlopCards = new Card[]{new Card(3, Suits.Diamonds), new Card(4, Suits.Diamonds), new Card(5, Suits.Diamonds)};
        distribution.setFlopCards(inputFlopCards);
        final Card[] actualCards = distribution.getFlopCards();
        assertArrayEquals(inputFlopCards, actualCards);
    }

    @Test
    public void testGetTurnCard() throws Exception {
        final Card[] inputPlayersCards = new Card[]{new Card(10, Suits.Diamonds), new Card(10, Suits.Spades)};
        final Distribution distribution = new Distribution(inputPlayersCards, null);
        final Card inputTurnCards = new Card(7, Suits.Clubs);
        distribution.setTurnCard(inputTurnCards);
        final Card actualCards = distribution.getTurnCard();
        assertEquals(inputTurnCards, actualCards);
    }

    @Test
    public void testGetRiverCard() throws Exception {
        final Card[] inputPlayersCards = new Card[]{new Card(10, Suits.Diamonds), new Card(10, Suits.Spades)};
        final Distribution distribution = new Distribution(inputPlayersCards, null);
        final Card inputRiverCards = new Card(9, Suits.Hertz);
        distribution.setRiverCard(inputRiverCards);
        final Card actualCards = distribution.getRiverCard();
        assertEquals(inputRiverCards, actualCards);
    }

    @Test(expected = ReassigningFieldException.class)
    public void testSetFlopCardsWhenAlreadyAssigned() throws Exception {
        final Card[] inputPlayersCards = new Card[]{new Card(10, Suits.Diamonds), new Card(10, Suits.Spades)};
        final Distribution distribution = new Distribution(inputPlayersCards, null);
        final Card[] inputFlopCards = new Card[]{new Card(3, Suits.Diamonds), new Card(4, Suits.Diamonds), new Card(5, Suits.Diamonds)};
        distribution.setFlopCards(inputFlopCards);
        distribution.setFlopCards(inputFlopCards);
    }

    @Test(expected = ReassigningFieldException.class)
    public void testSetTurnCardAlreadyAssigned() throws Exception {
        final Card[] inputPlayersCards = new Card[]{new Card(10, Suits.Diamonds), new Card(10, Suits.Spades)};
        final Distribution distribution = new Distribution(inputPlayersCards, null);
        final Card inputTurnCards = new Card(7, Suits.Clubs);
        distribution.setTurnCard(inputTurnCards);
        distribution.setTurnCard(inputTurnCards);
    }

    @Test(expected = ReassigningFieldException.class)
    public void testSetRiverCardAlreadyAssigned() throws Exception {
        final Card[] inputPlayersCards = new Card[]{new Card(10, Suits.Diamonds), new Card(10, Suits.Spades)};
        final Distribution distribution = new Distribution(inputPlayersCards, null);
        final Card inputRiverCards = new Card(9, Suits.Hertz);
        distribution.setRiverCard(inputRiverCards);
        distribution.setRiverCard(inputRiverCards);
    }

    @Test
    public void testRemoveKnownCardsFromDeck() throws Exception {
        final Card[] inputPlayersCards = new Card[]{new Card(4, Suits.Hertz), new Card(6, Suits.Spades)};
        final Card[] inputFlopCards = new Card[]{new Card(7, Suits.Clubs), new Card(2, Suits.Diamonds), new Card(9, Suits.Hertz)};
        final Card inputTurnCad = new Card(5, Suits.Spades);
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
    public void testCurrentKnownCards() throws Exception {
        final Card[] inputPlayersCards = new Card[]{new Card(4, Suits.Hertz), new Card(6, Suits.Spades)};
        final Card[] expectedAnswer = new Card[]{new Card(7, Suits.Clubs), new Card(2, Suits.Diamonds), new Card(9, Suits.Hertz)};
        final Distribution distribution = new Distribution(inputPlayersCards, null);
        final Distribution.StreetPoker inputStreetPoker = Distribution.StreetPoker.Flop;
        distribution.setFlopCards(expectedAnswer);
        final Card[] actualAnswer = distribution.currentKnownCards(inputStreetPoker);
        assertArrayEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testAllCurrentKnownCards() throws Exception {
        final Card[] inputPlayersCards = new Card[]{new Card(7, Suits.Clubs), new Card(10, Suits.Diamonds)};
        final Card[] inputFlopCards = new Card[]{new Card(7, Suits.Clubs), new Card(2, Suits.Diamonds), new Card(9, Suits.Hertz)};
        final Card[] expectedAnswer = new Card[]{new Card(7, Suits.Clubs), new Card(10, Suits.Diamonds), new Card(7, Suits.Clubs),
                new Card(2, Suits.Diamonds), new Card(9, Suits.Hertz)};
        final Distribution distribution = new Distribution(inputPlayersCards, null);
        final Distribution.StreetPoker inputStreetPoker = Distribution.StreetPoker.Flop;
        distribution.setFlopCards(inputFlopCards);
        final Card[] actualAnswer = distribution.allCurrentKnownCards(inputStreetPoker);
        assertArrayEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testCounterKnownCards() throws Exception {
        final Card[] inputPlayersCards = new Card[]{new Card(7, Suits.Clubs), new Card(10, Suits.Diamonds)};
        final Distribution.StreetPoker inputStreetPoker = Distribution.StreetPoker.Turn;
        final int expectedAnswer = 6;
        final Distribution distribution = new Distribution(inputPlayersCards, null);
        final int actualAnswer = distribution.counterKnownCards(inputStreetPoker);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testCurrentStreetPoker() throws Exception {
        final Card[] inputPlayersCards = new Card[]{new Card(7, Suits.Clubs), new Card(10, Suits.Diamonds)};
        final Distribution.StreetPoker expectedAnswer = Distribution.StreetPoker.PreFlop;
        final Distribution distribution = new Distribution(inputPlayersCards, null);
        final Distribution.StreetPoker actualAnswer = distribution.currentStreetPoker();
        assertEquals(expectedAnswer, actualAnswer);
    }
}