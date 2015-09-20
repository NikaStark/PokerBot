package models;

import models.Enums.Suits;
import org.junit.Test;

import static org.junit.Assert.*;

public class CardTest {

    @Test
    public void testGetNumericValue() throws Exception {
        final Card card = new Card(9, Suits.Clubs);
        final int expectedAnswer = 9;

        final int actualAnswer = card.getNumericValue();
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testGetSuit() throws Exception {
        final Card card = new Card(4, Suits.Spades);
        final Suits expectedAnswer = Suits.Spades;

        final Suits actualAnswer = card.getSuit();
        assertEquals(expectedAnswer, actualAnswer);
    }
}