package engine.models;

import engine.models.Enums.HighCard;
import engine.models.Enums.Suits;
import engine.models.exceptions.IllegalSuitException;
import org.junit.Test;

import static org.junit.Assert.*;

public class CardTest {

    @Test
    public void testCreateInstance() throws Exception {
        final Card card = new Card(12, 'C');
        final Suits expectedAnswer = Suits.CLUBS;

        final Suits actualAnswer = card.getSuit();
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test(expected = IllegalSuitException.class)
    public void testCreateInstanceWrong() throws Exception {
        final Card card = new Card(12, 'a');
    }

    @Test
    public void testEqualsLessThenMaxNumericValueWhenEqual() throws Exception {
        final Card card1 = new Card(7, Suits.SPADES);
        final Card card2 = new Card(7, Suits.SPADES);
        final boolean expectedAnswer = true;

        final boolean actualAnswer1 = card1.equals(card2);
        assertEquals(expectedAnswer, actualAnswer1);

        final boolean actualAnswer2 = card2.equals(card1);
        assertEquals(expectedAnswer, actualAnswer2);
    }

    @Test
    public void testEqualsMoreThenMaxNumericValueWhenEqual() throws Exception {
        final Card card1 = new Card(HighCard.King, Suits.SPADES);
        final Card card2 = new Card(HighCard.King, Suits.SPADES);
        final boolean expectedAnswer = true;

        final boolean actualAnswer1 = card1.equals(card2);
        assertEquals(expectedAnswer, actualAnswer1);

        final boolean actualAnswer2 = card2.equals(card1);
        assertEquals(expectedAnswer, actualAnswer2);
    }

    @Test
    public void testEqualsLessThenMaxNumericValueWhenNotEqual() throws Exception {
        final Card card1 = new Card(3, Suits.SPADES);
        final Card card2 = new Card(6, Suits.SPADES);
        final boolean expectedAnswer = false;

        final boolean actualAnswer1 = card1.equals(card2);
        assertEquals(expectedAnswer, actualAnswer1);

        final boolean actualAnswer2 = card2.equals(card1);
        assertEquals(expectedAnswer, actualAnswer2);
    }

    @Test
    public void testEqualsMoreThenMaxNumericValueWhenNotEqual() throws Exception {
        final Card card1 = new Card(HighCard.King, Suits.SPADES);
        final Card card2 = new Card(HighCard.King, Suits.CLUBS);
        final boolean expectedAnswer = false;

        final boolean actualAnswer1 = card1.equals(card2);
        assertEquals(expectedAnswer, actualAnswer1);

        final boolean actualAnswer2 = card2.equals(card1);
        assertEquals(expectedAnswer, actualAnswer2);
    }

    @Test
    public void testToString() throws Exception {
        final Card card = new Card(11, Suits.DIAMONDS);
        final String expectedAnswer = "11DIAMONDS";

        final String actualAnswer = card.toString();
        assertEquals(expectedAnswer, actualAnswer);
    }

}