package engine.models;

import engine.models.Enums.Suits;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void testInitializationDeckOfCards() throws Exception {
        final Card[] expectedAnswer = new Card[]{
                new Card(2, Suits.SPADES), new Card(2, Suits.HERTZ), new Card(2, Suits.CLUBS), new Card(2, Suits.DIAMONDS),
                new Card(3, Suits.SPADES), new Card(3, Suits.HERTZ), new Card(3, Suits.CLUBS), new Card(3, Suits.DIAMONDS),
                new Card(4, Suits.SPADES), new Card(4, Suits.HERTZ), new Card(4, Suits.CLUBS), new Card(4, Suits.DIAMONDS),
                new Card(5, Suits.SPADES), new Card(5, Suits.HERTZ), new Card(5, Suits.CLUBS), new Card(5, Suits.DIAMONDS),
                new Card(6, Suits.SPADES), new Card(6, Suits.HERTZ), new Card(6, Suits.CLUBS), new Card(6, Suits.DIAMONDS),
                new Card(7, Suits.SPADES), new Card(7, Suits.HERTZ), new Card(7, Suits.CLUBS), new Card(7, Suits.DIAMONDS),
                new Card(8, Suits.SPADES), new Card(8, Suits.HERTZ), new Card(8, Suits.CLUBS), new Card(8, Suits.DIAMONDS),
                new Card(9, Suits.SPADES), new Card(9, Suits.HERTZ), new Card(9, Suits.CLUBS), new Card(9, Suits.DIAMONDS),
                new Card(10, Suits.SPADES), new Card(10, Suits.HERTZ), new Card(10, Suits.CLUBS), new Card(10, Suits.DIAMONDS),
                new Card(11, Suits.SPADES), new Card(11, Suits.HERTZ), new Card(11, Suits.CLUBS), new Card(11, Suits.DIAMONDS),
                new Card(12, Suits.SPADES), new Card(12, Suits.HERTZ), new Card(12, Suits.CLUBS), new Card(12, Suits.DIAMONDS),
                new Card(13, Suits.SPADES), new Card(13, Suits.HERTZ), new Card(13, Suits.CLUBS), new Card(13, Suits.DIAMONDS),
                new Card(14, Suits.SPADES), new Card(14, Suits.HERTZ), new Card(14, Suits.CLUBS), new Card(14, Suits.DIAMONDS),
        };
        final Card[] actualAnswer = Game.DECK_OF_CARDS;
        assertArrayEquals(expectedAnswer, actualAnswer);
    }

}