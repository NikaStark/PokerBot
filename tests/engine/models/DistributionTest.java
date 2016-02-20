package engine.models;

import engine.models.Enums.Dignities;
import engine.models.Enums.Suits;
import engine.models.exceptions.ReassigningFieldException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class DistributionTest {

    @Test(expected = ReassigningFieldException.class)
    public void testAddPlayersCard() throws Exception {
        final Card card = new Card(Dignities.create(7), Suits.SPADES);
        final Distribution distribution = new Distribution(null);
        distribution.addPlayersCard(card, 0);
        distribution.addPlayersCard(card, 0);
    }

    @Test(expected = ReassigningFieldException.class)
    public void testAddFlopCard() throws Exception {
        final Card card = new Card(Dignities.create(4), Suits.DIAMONDS);
        final Distribution distribution = new Distribution(null);
        distribution.addFlopCard(card, 0);
        distribution.addFlopCard(card, 0);
    }

    @Test(expected = ReassigningFieldException.class)
    public void testSetTurnCardAlreadyAssigned() throws Exception {
        final Card[] inputPlayersCards = new Card[]{new Card(Dignities.create(10), Suits.DIAMONDS),
                new Card(Dignities.create(10), Suits.SPADES)};
        final Distribution distribution = new Distribution(null);
        for (int i = 0; i < inputPlayersCards.length; i++) {
            distribution.addPlayersCard(inputPlayersCards[i], i);
        }
        final Card inputTurnCards = new Card(Dignities.create(7), Suits.CLUBS);
        distribution.setTurnCard(inputTurnCards);
        distribution.setTurnCard(inputTurnCards);
    }

    @Test(expected = ReassigningFieldException.class)
    public void testSetRiverCardAlreadyAssigned() throws Exception {
        final Card[] inputPlayersCards = new Card[]{new Card(Dignities.create(10), Suits.DIAMONDS),
                new Card(Dignities.create(10), Suits.SPADES)};
        final Distribution distribution = new Distribution(null);
        for (int i = 0; i < inputPlayersCards.length; i++) {
            distribution.addPlayersCard(inputPlayersCards[i], i);
        }
        final Card inputRiverCards = new Card(Dignities.create(9), Suits.HERTZ);
        distribution.setRiverCard(inputRiverCards);
        distribution.setRiverCard(inputRiverCards);
    }

    @Test
    public void testRemoveKnownCardsFromDeck() throws Exception {
        final Card[] inputPlayersCards = new Card[]{new Card(Dignities.create(4), Suits.HERTZ),
                new Card(Dignities.create(6), Suits.SPADES)};
        final Card[] inputFlopCards = new Card[]{new Card(Dignities.create(7), Suits.CLUBS),
                new Card(Dignities.create(2), Suits.DIAMONDS), new Card(Dignities.create(9), Suits.HERTZ)};
        final Card inputTurnCard = new Card(Dignities.create(5), Suits.SPADES);
        final ArrayList<Card> expectedAnswer = new ArrayList<>(Arrays.asList(Game.DECK_OF_CARDS));
        expectedAnswer.removeAll(new ArrayList<>(Arrays.asList(inputPlayersCards)));
        expectedAnswer.removeAll(new ArrayList<>(Arrays.asList(inputFlopCards)));
        expectedAnswer.remove(inputTurnCard);
        final Distribution distribution = new Distribution(null);
        for (int i = 0; i < inputPlayersCards.length; i++) {
            distribution.addPlayersCard(inputPlayersCards[i], i);
        }
        for (int i = 0; i < inputFlopCards.length; i++) {
            distribution.addFlopCard(inputFlopCards[i], i);
        }
        distribution.setTurnCard(inputTurnCard);
        final ArrayList<Card> actualAnswer = distribution.getCurrentDeck();
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testRateMyStack() throws Exception {
        final int expectedAnswer = 40000;
        final Distribution distribution = new Distribution(null);
        distribution.getCurrentPossibleSteps().put(Distribution.PossibleSteps.FOLD, new Integer[]{0});
        distribution.getCurrentPossibleSteps().put(Distribution.PossibleSteps.CHECK, new Integer[]{0});
        distribution.getCurrentPossibleSteps().put(Distribution.PossibleSteps.RAISE, new Integer[]{2000, expectedAnswer, 1000, 1});
        distribution.calculateMyStack();
        final int actualAnswer = distribution.getMyStack();
        assertEquals(expectedAnswer, actualAnswer);
    }
}