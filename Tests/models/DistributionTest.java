package models;

import models.Enums.StreetPoker;
import models.Enums.Suits;
import models.exceptions.ReassigningFieldException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class DistributionTest {

    @Test
    public void testGetCardsOfPlayer() throws Exception {
        final Card[] inputCards = new Card[]{new Card(10, Suits.Diamonds), new Card(10, Suits.Spades)};
        final Distribution distribution = new Distribution(inputCards);
        final Card[] actualCards = distribution.getCardsOfPlayer();
        assertArrayEquals(inputCards, actualCards);
    }

    @Test
    public void testGetFlopCards() throws Exception {
        final Card[] inputPlayersCards = new Card[]{new Card(10, Suits.Diamonds), new Card(10, Suits.Spades)};
        final Distribution distribution = new Distribution(inputPlayersCards);
        final Card[] inputFlopCards = new Card[]{new Card(3, Suits.Diamonds), new Card(4, Suits.Diamonds), new Card(5, Suits.Diamonds)};
        distribution.setFlopCards(inputFlopCards);
        final Card[] actualCards = distribution.getFlopCards();
        assertArrayEquals(inputFlopCards, actualCards);
    }

    @Test
    public void testGetTurnCard() throws Exception {
        final Card[] inputPlayersCards = new Card[]{new Card(10, Suits.Diamonds), new Card(10, Suits.Spades)};
        final Distribution distribution = new Distribution(inputPlayersCards);
        final Card inputTurnCards = new Card(7, Suits.Clubs);
        distribution.setTurnCard(inputTurnCards);
        final Card actualCards = distribution.getTurnCard();
        assertEquals(inputTurnCards, actualCards);
    }

    @Test
    public void testGetRiverCard() throws Exception {
        final Card[] inputPlayersCards = new Card[]{new Card(10, Suits.Diamonds), new Card(10, Suits.Spades)};
        final Distribution distribution = new Distribution(inputPlayersCards);
        final Card inputRiverCards = new Card(9, Suits.Hertz);
        distribution.setRiverCard(inputRiverCards);
        final Card actualCards = distribution.getRiverCard();
        assertEquals(inputRiverCards, actualCards);
    }

    @Test
    public void testSetFlopCardsWhenAlreadyAssigned() throws Exception {
        final Card[] inputPlayersCards = new Card[]{new Card(10, Suits.Diamonds), new Card(10, Suits.Spades)};
        final Distribution distribution = new Distribution(inputPlayersCards);
        final Card[] inputFlopCards = new Card[]{new Card(3, Suits.Diamonds), new Card(4, Suits.Diamonds), new Card(5, Suits.Diamonds)};
        distribution.setFlopCards(inputFlopCards);
        try {
            distribution.setFlopCards(inputFlopCards);
            fail();
        } catch (final ReassigningFieldException e) {}
    }

    @Test
    public void testSetTurnCardAlreadyAssigned() throws Exception {
        final Card[] inputPlayersCards = new Card[]{new Card(10, Suits.Diamonds), new Card(10, Suits.Spades)};
        final Distribution distribution = new Distribution(inputPlayersCards);
        final Card inputTurnCards = new Card(7, Suits.Clubs);
        distribution.setTurnCard(inputTurnCards);
        try {
            distribution.setTurnCard(inputTurnCards);
            fail();
        } catch (final ReassigningFieldException e) {}
    }

    @Test
    public void testSetRiverCardAlreadyAssigned() throws Exception {
        final Card[] inputPlayersCards = new Card[]{new Card(10, Suits.Diamonds), new Card(10, Suits.Spades)};
        final Distribution distribution = new Distribution(inputPlayersCards);
        final Card inputRiverCards = new Card(9, Suits.Hertz);
        distribution.setRiverCard(inputRiverCards);
        try {
            distribution.setRiverCard(inputRiverCards);
            fail();
        } catch (final ReassigningFieldException e) {}
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
        final Distribution distribution = new Distribution(inputPlayersCards);
        distribution.setFlopCards(inputFlopCards);
        distribution.setTurnCard(inputTurnCad);
        final ArrayList<Card> actualAnswer = distribution.getCurrentDeck();
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testCurrentKnownCards() throws Exception {
        final Card[] inputPlayersCards = new Card[]{new Card(4, Suits.Hertz), new Card(6, Suits.Spades)};
        final Card[] inputFlopCards = new Card[]{new Card(7, Suits.Clubs), new Card(2, Suits.Diamonds), new Card(9, Suits.Hertz)};
        final Card[] expectedAnswer = inputFlopCards;
        final Distribution distribution = new Distribution(inputPlayersCards);
        final StreetPoker inputStreetPoker = StreetPoker.Flop;
        distribution.setFlopCards(inputFlopCards);
        final Card[] actualAnswer = distribution.currentKnownCards(inputStreetPoker);
        assertArrayEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testAllCurrentKnownCards() throws Exception {
        final Card[] inputPlayersCards = new Card[]{new Card(7, Suits.Clubs), new Card(10, Suits.Diamonds)};
        final Card[] inputFlopCards = new Card[]{new Card(7, Suits.Clubs), new Card(2, Suits.Diamonds), new Card(9, Suits.Hertz)};
        final Card[] expectedAnswer = new Card[]{new Card(7, Suits.Clubs), new Card(10, Suits.Diamonds), new Card(7, Suits.Clubs),
                new Card(2, Suits.Diamonds), new Card(9, Suits.Hertz)};
        final Distribution distribution = new Distribution(inputPlayersCards);
        final StreetPoker inputStreetPoker = StreetPoker.Flop;
        distribution.setFlopCards(inputFlopCards);
        final Card[] actualAnswer = distribution.allCurrentKnownCards(inputStreetPoker);
        assertArrayEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testCounterKnownCards() throws Exception {
        final Card[] inputPlayersCards = new Card[]{new Card(7, Suits.Clubs), new Card(10, Suits.Diamonds)};
        final StreetPoker inputStreetPoker = StreetPoker.Turn;
        final int expectedAnswer = 6;
        final Distribution distribution = new Distribution(inputPlayersCards);
        final int actualAnswer = distribution.counterKnownCards(inputStreetPoker);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testCurrentStreetPoker() throws Exception {
        final Card[] inputPlayersCards = new Card[]{new Card(7, Suits.Clubs), new Card(10, Suits.Diamonds)};
        final StreetPoker expectedAnswer = StreetPoker.PreFlop;
        final Distribution distribution = new Distribution(inputPlayersCards);
        final StreetPoker actualAnswer = distribution.currentStreetPoker();
        assertEquals(expectedAnswer, actualAnswer);
    }
}