package engine.models;

import engine.models.Card;
import engine.models.Enums.HighCard;
import engine.models.Enums.Suits;
import org.junit.Test;

import static org.junit.Assert.*;

public class CardTest {

    @Test
    public void testGetNumericValueLessThenMaxNumericValue() throws Exception {
        final Card card = new Card(9, Suits.Clubs);
        final int expectedAnswer = 9;

        final int actualAnswer = card.getNumericValue();
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testGetNumericValueMoreThenMaxNumericValue() throws Exception {
        final Card card = new Card(HighCard.Ace, Suits.Clubs);
        final int expectedAnswer = 14;

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

    @Test
    public void testEqualsLessThenMaxNumericValueWhenEqual() throws Exception {
        final Card card1 = new Card(7, Suits.Spades);
        final Card card2 = new Card(7, Suits.Spades);
        final boolean expectedAnswer = true;

        final boolean actualAnswer1 = card1.equals(card2);
        assertEquals(expectedAnswer, actualAnswer1);

        final boolean actualAnswer2 = card2.equals(card1);
        assertEquals(expectedAnswer, actualAnswer2);
    }

    @Test
    public void testEqualsMoreThenMaxNumericValueWhenEqual() throws Exception {
        final Card card1 = new Card(HighCard.King, Suits.Spades);
        final Card card2 = new Card(HighCard.King, Suits.Spades);
        final boolean expectedAnswer = true;

        final boolean actualAnswer1 = card1.equals(card2);
        assertEquals(expectedAnswer, actualAnswer1);

        final boolean actualAnswer2 = card2.equals(card1);
        assertEquals(expectedAnswer, actualAnswer2);
    }

    @Test
    public void testEqualsLessThenMaxNumericValueWhenNotEqual() throws Exception {
        final Card card1 = new Card(3, Suits.Spades);
        final Card card2 = new Card(6, Suits.Spades);
        final boolean expectedAnswer = false;

        final boolean actualAnswer1 = card1.equals(card2);
        assertEquals(expectedAnswer, actualAnswer1);

        final boolean actualAnswer2 = card2.equals(card1);
        assertEquals(expectedAnswer, actualAnswer2);
    }

    @Test
    public void testEqualsMoreThenMaxNumericValueWhenNotEqual() throws Exception {
        final Card card1 = new Card(HighCard.King, Suits.Spades);
        final Card card2 = new Card(HighCard.King, Suits.Clubs);
        final boolean expectedAnswer = false;

        final boolean actualAnswer1 = card1.equals(card2);
        assertEquals(expectedAnswer, actualAnswer1);

        final boolean actualAnswer2 = card2.equals(card1);
        assertEquals(expectedAnswer, actualAnswer2);
    }

}