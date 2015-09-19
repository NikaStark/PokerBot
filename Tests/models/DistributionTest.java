package models;

import models.Enums.Suits;
import models.exceptions.ReassigningFieldException;
import org.junit.Test;

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
}