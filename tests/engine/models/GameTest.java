package engine.models;

import engine.models.Enums.Dignities;
import engine.models.Enums.Suits;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void testInitializationDeckOfCards() throws Exception {
        final Card[] expectedAnswer = new Card[]{
                new Card(Dignities.create(2), Suits.SPADES), new Card(Dignities.create(2), Suits.HERTZ),
                new Card(Dignities.create(2), Suits.CLUBS), new Card(Dignities.create(2), Suits.DIAMONDS),
                new Card(Dignities.create(3), Suits.SPADES), new Card(Dignities.create(3), Suits.HERTZ),
                new Card(Dignities.create(3), Suits.CLUBS), new Card(Dignities.create(3), Suits.DIAMONDS),
                new Card(Dignities.create(4), Suits.SPADES), new Card(Dignities.create(4), Suits.HERTZ),
                new Card(Dignities.create(4), Suits.CLUBS), new Card(Dignities.create(4), Suits.DIAMONDS),
                new Card(Dignities.create(5), Suits.SPADES), new Card(Dignities.create(5), Suits.HERTZ),
                new Card(Dignities.create(5), Suits.CLUBS), new Card(Dignities.create(5), Suits.DIAMONDS),
                new Card(Dignities.create(6), Suits.SPADES), new Card(Dignities.create(6), Suits.HERTZ),
                new Card(Dignities.create(6), Suits.CLUBS), new Card(Dignities.create(6), Suits.DIAMONDS),
                new Card(Dignities.create(7), Suits.SPADES), new Card(Dignities.create(7), Suits.HERTZ),
                new Card(Dignities.create(7), Suits.CLUBS), new Card(Dignities.create(7), Suits.DIAMONDS),
                new Card(Dignities.create(8), Suits.SPADES), new Card(Dignities.create(8), Suits.HERTZ),
                new Card(Dignities.create(8), Suits.CLUBS), new Card(Dignities.create(8), Suits.DIAMONDS),
                new Card(Dignities.create(9), Suits.SPADES), new Card(Dignities.create(9), Suits.HERTZ),
                new Card(Dignities.create(9), Suits.CLUBS), new Card(Dignities.create(9), Suits.DIAMONDS),
                new Card(Dignities.create(10), Suits.SPADES), new Card(Dignities.create(10), Suits.HERTZ),
                new Card(Dignities.create(10), Suits.CLUBS), new Card(Dignities.create(10), Suits.DIAMONDS),
                new Card(Dignities.create(11), Suits.SPADES), new Card(Dignities.create(11), Suits.HERTZ),
                new Card(Dignities.create(11), Suits.CLUBS), new Card(Dignities.create(11), Suits.DIAMONDS),
                new Card(Dignities.create(12), Suits.SPADES), new Card(Dignities.create(12), Suits.HERTZ),
                new Card(Dignities.create(12), Suits.CLUBS), new Card(Dignities.create(12), Suits.DIAMONDS),
                new Card(Dignities.create(13), Suits.SPADES), new Card(Dignities.create(13), Suits.HERTZ),
                new Card(Dignities.create(13), Suits.CLUBS), new Card(Dignities.create(13), Suits.DIAMONDS),
                new Card(Dignities.create(14), Suits.SPADES), new Card(Dignities.create(14), Suits.HERTZ),
                new Card(Dignities.create(14), Suits.CLUBS), new Card(Dignities.create(14), Suits.DIAMONDS),

        };
        final Card[] actualAnswer = Game.DECK_OF_CARDS;
        assertArrayEquals(expectedAnswer, actualAnswer);
    }

}