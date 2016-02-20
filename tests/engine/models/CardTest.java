package engine.models;

import engine.models.Enums.Dignities;
import engine.models.Enums.Suits;
import engine.models.exceptions.IllegalSuitException;
import org.junit.Test;

import static org.junit.Assert.*;

public class CardTest {

    @Test
    public void testCreateInstance() throws Exception {
        final Card card = new Card(Dignities.create(12), Suits.create('C'));
        final Suits expectedAnswer = Suits.CLUBS;

        final Suits actualAnswer = card.getSuit();
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test(expected = IllegalSuitException.class)
    public void testCreateInstanceWrong() throws Exception {
        final Card card = new Card(Dignities.create(12), Suits.create('a'));
    }

    @Test
    public void testEqualsLessThenMaxNumericValueWhenEqual() throws Exception {
        final Card card1 = new Card(Dignities.create(7), Suits.SPADES);
        final Card card2 = new Card(Dignities.create(7), Suits.SPADES);
        final boolean expectedAnswer = true;

        final boolean actualAnswer1 = card1.equals(card2);
        assertEquals(expectedAnswer, actualAnswer1);

        final boolean actualAnswer2 = card2.equals(card1);
        assertEquals(expectedAnswer, actualAnswer2);
    }

    @Test
    public void testEqualsMoreThenMaxNumericValueWhenEqual() throws Exception {
        final Card card1 = new Card(Dignities.KING, Suits.SPADES);
        final Card card2 = new Card(Dignities.KING, Suits.SPADES);
        final boolean expectedAnswer = true;

        final boolean actualAnswer1 = card1.equals(card2);
        assertEquals(expectedAnswer, actualAnswer1);

        final boolean actualAnswer2 = card2.equals(card1);
        assertEquals(expectedAnswer, actualAnswer2);
    }

    @Test
    public void testEqualsLessThenMaxNumericValueWhenNotEqual() throws Exception {
        final Card card1 = new Card(Dignities.create(3), Suits.SPADES);
        final Card card2 = new Card(Dignities.create(6), Suits.SPADES);
        final boolean expectedAnswer = false;

        final boolean actualAnswer1 = card1.equals(card2);
        assertEquals(expectedAnswer, actualAnswer1);

        final boolean actualAnswer2 = card2.equals(card1);
        assertEquals(expectedAnswer, actualAnswer2);
    }

    @Test
    public void testEqualsMoreThenMaxNumericValueWhenNotEqual() throws Exception {
        final Card card1 = new Card(Dignities.KING, Suits.SPADES);
        final Card card2 = new Card(Dignities.KING, Suits.CLUBS);
        final boolean expectedAnswer = false;

        final boolean actualAnswer1 = card1.equals(card2);
        assertEquals(expectedAnswer, actualAnswer1);

        final boolean actualAnswer2 = card2.equals(card1);
        assertEquals(expectedAnswer, actualAnswer2);
    }

    @Test
    public void testToString() throws Exception {
        final Card card = new Card(Dignities.create(11), Suits.DIAMONDS);
        final String expectedAnswer = "J DIAMONDS";

        final String actualAnswer = card.toString();
        assertEquals(expectedAnswer, actualAnswer);
    }

}