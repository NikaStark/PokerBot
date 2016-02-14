package engine.controllers.calculator;

import engine.models.Card;
import engine.models.Distribution;
import engine.models.Enums.HighCard;
import engine.models.Enums.Suits;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;


public class CalculatorTest {

    @Test
    public void testCreatorPossibleCombinations() throws Exception {
        final Calculator calculator = new Calculator();
        final ArrayList<Card[]> inputPossibleCards = new ArrayList<>(Arrays.asList(new Card[][]{
                new Card[] {
                        new Card(HighCard.Ace, Suits.CLUBS),
                        new Card(2, Suits.CLUBS),
                        new Card(HighCard.Jack, Suits.CLUBS),
                        new Card(9, Suits.DIAMONDS),
                        new Card(10, Suits.HERTZ)
                },
                new Card[] {
                        new Card(HighCard.Jack, Suits.DIAMONDS),
                        new Card(5, Suits.DIAMONDS),
                        new Card(HighCard.Ace, Suits.HERTZ),
                        new Card(HighCard.King, Suits.HERTZ),
                        new Card(HighCard.Queen, Suits.HERTZ)
                },
                new Card[] {
                        new Card(9, Suits.CLUBS),
                        new Card(HighCard.King, Suits.SPADES),
                        new Card(HighCard.Queen, Suits.SPADES),
                        new Card(HighCard.Jack, Suits.SPADES),
                        new Card(10, Suits.SPADES)
                }

        }));
        final Card[] inputKnownCard = new Card[] {
                new Card(7, Suits.CLUBS),
                new Card(6, Suits.HERTZ)};
        final ArrayList<Card[]>  expectedAnswer = new ArrayList<>(Arrays.asList(new Card[][]{
                new Card[] {
                        new Card(HighCard.Ace, Suits.CLUBS),
                        new Card(2, Suits.CLUBS),
                        new Card(HighCard.Jack, Suits.CLUBS),
                        new Card(9, Suits.DIAMONDS),
                        new Card(10, Suits.HERTZ),
                        new Card(7, Suits.CLUBS),
                        new Card(6, Suits.HERTZ)
                },
                new Card[] {
                        new Card(HighCard.Jack, Suits.DIAMONDS),
                        new Card(5, Suits.DIAMONDS),
                        new Card(HighCard.Ace, Suits.HERTZ),
                        new Card(HighCard.King, Suits.HERTZ),
                        new Card(HighCard.Queen, Suits.HERTZ),
                        new Card(7, Suits.CLUBS),
                        new Card(6, Suits.HERTZ)
                },
                new Card[] {
                        new Card(9, Suits.CLUBS),
                        new Card(HighCard.King, Suits.SPADES),
                        new Card(HighCard.Queen, Suits.SPADES),
                        new Card(HighCard.Jack, Suits.SPADES),
                        new Card(10, Suits.SPADES),
                        new Card(7, Suits.CLUBS),
                        new Card(6, Suits.HERTZ)
                }
        }));
        final ArrayList<Card[]> actualAnswer = calculator.createPossibleCombinations(inputPossibleCards, inputKnownCard);
        assertArrayEquals(expectedAnswer.toArray(new Card[expectedAnswer.size()][]),
                actualAnswer.toArray(new Card[actualAnswer.size()][]));
    }

    @Test
    public void testSearchMaxCombination() throws Exception {
        final Calculator calculator = new Calculator();
        final ArrayList<Card> inputCards = new ArrayList<>(Arrays.asList(new Card[]{
                new Card(HighCard.Ace, Suits.CLUBS),
                new Card(2, Suits.CLUBS),
                new Card(HighCard.Jack, Suits.CLUBS),
                new Card(9, Suits.DIAMONDS),
                new Card(10, Suits.HERTZ),
                new Card(HighCard.Jack, Suits.DIAMONDS),
                new Card(5, Suits.DIAMONDS)
        }));
        final int expectedAnswer = 1;
        final int actualAnswer = calculator.searchMaxCombination(inputCards);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testSmartSample() throws Exception {
        final Calculator calculator = new Calculator();
        final ArrayList<Card> inputCards = new ArrayList<>(Arrays.asList(new Card[]{
                new Card(HighCard.Ace, Suits.HERTZ),
                new Card(HighCard.King, Suits.HERTZ),
                new Card(HighCard.Queen, Suits.HERTZ),
                new Card(HighCard.Jack, Suits.HERTZ)}));
        final ArrayList<Card[]> expectedAnswer = new ArrayList<>(Arrays.asList(new Card[][]{
                new Card[]{
                        new Card(HighCard.Ace,Suits.HERTZ),
                        new Card(HighCard.King, Suits.HERTZ),
                        new Card(HighCard.Queen, Suits.HERTZ)
                },
                new Card[]{
                        new Card(HighCard.Ace, Suits.HERTZ),
                        new Card(HighCard.King, Suits.HERTZ),
                        new Card(HighCard.Jack, Suits.HERTZ)
                },
                new Card[]{
                        new Card(HighCard.Ace, Suits.HERTZ),
                        new Card(HighCard.Queen, Suits.HERTZ),
                        new Card(HighCard.Jack, Suits.HERTZ)
                },
                new Card[]{
                        new Card(HighCard.King, Suits.HERTZ),
                        new Card(HighCard.Queen, Suits.HERTZ),
                        new Card(HighCard.Jack, Suits.HERTZ)
                }
        }));
        final ArrayList<Card[]> actualAnswer = calculator.smartSample(inputCards, 3);
// Important test and this is second control
//        for (Card[] cards : expectedAnswer) {
//            for (Card card : cards) {
//                System.out.print(card.toString() + " ");
//            }
//            System.out.println();
//        }
//        for (Card[] cards : actualAnswer) {
//            for (Card card : cards) {
//                System.out.print(card.toString() + " ");
//            }
//            System.out.println();
//        }
        assertArrayEquals(expectedAnswer.toArray(new Card[expectedAnswer.size()][]),
                actualAnswer.toArray(new Card[actualAnswer.size()][]));
    }

    @Test
    public void testWhatCombinationWhenRoyalFlush() throws Exception {
        final Calculator calculator = new Calculator();
        final int expectedAnswer = 9;
        final Card[] inputCards = new Card[] {
                new Card(HighCard.Ace, Suits.SPADES),
                new Card(HighCard.King, Suits.SPADES),
                new Card(HighCard.Queen, Suits.SPADES),
                new Card(HighCard.Jack, Suits.SPADES),
                new Card(10, Suits.SPADES)};

        final int actualAnswer = calculator.whatCombination(inputCards);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testWhatCombinationWhenStraightFlush() throws Exception {
        final Calculator calculator = new Calculator();
        final int expectedAnswer = 8;
        final Card[] inputCards = new Card[] {
                new Card(6, Suits.CLUBS),
                new Card(8, Suits.CLUBS),
                new Card(7, Suits.CLUBS),
                new Card(9, Suits.CLUBS),
                new Card(5, Suits.CLUBS)};

        final int actualAnswer = calculator.whatCombination(inputCards);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testWhatCombinationWhenFourOfAKind() throws Exception {
        final Calculator calculator = new Calculator();
        final int expectedAnswer = 7;
        final Card[] inputCards = new Card[] {
                new Card(7, Suits.CLUBS),
                new Card(7, Suits.DIAMONDS),
                new Card(7, Suits.SPADES),
                new Card(7, Suits.HERTZ),
                new Card(9, Suits.CLUBS)};

        final int actualAnswer = calculator.whatCombination(inputCards);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testWhatCombinationWhenFullHouse() throws Exception {
        final Calculator calculator = new Calculator();
        final int expectedAnswer = 6;
        final Card[] inputCards = new Card[] {
                new Card(7, Suits.CLUBS),
                new Card(7, Suits.DIAMONDS),
                new Card(7, Suits.SPADES),
                new Card(9, Suits.HERTZ),
                new Card(9, Suits.CLUBS)};

        final int actualAnswer = calculator.whatCombination(inputCards);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testWhatCombinationWhenFlush() throws Exception {
        final Calculator calculator = new Calculator();
        final int expectedAnswer = 5;
        final Card[] inputCards = new Card[] {
                new Card(7, Suits.HERTZ),
                new Card(4, Suits.HERTZ),
                new Card(5, Suits.HERTZ),
                new Card(9, Suits.HERTZ),
                new Card(2, Suits.HERTZ)};

        final int actualAnswer = calculator.whatCombination(inputCards);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testWhatCombinationWhenStraight() throws Exception {
        final Calculator calculator = new Calculator();
        final int expectedAnswer = 4;
        final Card[] inputCards = new Card[] {
                new Card(6, Suits.CLUBS),
                new Card(4, Suits.HERTZ),
                new Card(5, Suits.SPADES),
                new Card(3, Suits.HERTZ),
                new Card(2, Suits.HERTZ)};

        final int actualAnswer = calculator.whatCombination(inputCards);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testWhatCombinationWhenThreeOfAKind() throws Exception {
        final Calculator calculator = new Calculator();
        final int expectedAnswer = 3;
        final Card[] inputCards = new Card[] {
                new Card(6, Suits.CLUBS),
                new Card(4, Suits.HERTZ),
                new Card(6, Suits.SPADES),
                new Card(3, Suits.HERTZ),
                new Card(6, Suits.HERTZ)};

        final int actualAnswer = calculator.whatCombination(inputCards);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testWhatCombinationWhenTwoPair() throws Exception {
        final Calculator calculator = new Calculator();
        final int expectedAnswer = 2;
        final Card[] inputCards = new Card[] {
                new Card(8, Suits.DIAMONDS),
                new Card(7, Suits.HERTZ),
                new Card(3, Suits.SPADES),
                new Card(3, Suits.DIAMONDS),
                new Card(8, Suits.HERTZ)};

        final int actualAnswer = calculator.whatCombination(inputCards);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testWhatCombinationWhenPair() throws Exception {
        final Calculator calculator = new Calculator();
        final int expectedAnswer = 1;
        final Card[] inputCards = new Card[] {
                new Card(8, Suits.DIAMONDS),
                new Card(7, Suits.HERTZ),
                new Card(3, Suits.SPADES),
                new Card(4, Suits.DIAMONDS),
                new Card(8, Suits.HERTZ)};

        final int actualAnswer = calculator.whatCombination(inputCards);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testWhatCombinationWhenKicker() throws Exception {
        final Calculator calculator = new Calculator();
        final int expectedAnswer = 0;
        final Card[] inputCards = new Card[] {
                new Card(8, Suits.DIAMONDS),
                new Card(7, Suits.HERTZ),
                new Card(3, Suits.SPADES),
                new Card(4, Suits.DIAMONDS),
                new Card(2, Suits.CLUBS)};

        final int actualAnswer = calculator.whatCombination(inputCards);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testIsRoyalFlushWhenTrue() throws Exception {
        final Calculator calculator = new Calculator();
        final Card[] inputCards = new Card[]{
                new Card(HighCard.Ace, Suits.SPADES),
                new Card(HighCard.King, Suits.SPADES),
                new Card(HighCard.Queen, Suits.SPADES),
                new Card(HighCard.Jack, Suits.SPADES),
                new Card(10, Suits.SPADES)};

        final boolean actualAnswer = calculator.isRoyalFlush(inputCards);
        assertTrue(actualAnswer);
    }

    @Test
    public void testIsRoyalFlushWhenFalse() throws Exception {
        final Calculator calculator = new Calculator();
        final Card[] inputCards = new Card[]{
                new Card(HighCard.Ace, Suits.SPADES),
                new Card(HighCard.King, Suits.SPADES),
                new Card(HighCard.Queen, Suits.SPADES),
                new Card(HighCard.Jack, Suits.SPADES),
                new Card(9, Suits.SPADES)};

        final boolean actualAnswer = calculator.isRoyalFlush(inputCards);
        assertFalse(actualAnswer);
    }

    @Test
    public void testIsStraightFlushTrue() throws Exception {
        final Calculator calculator = new Calculator();
        final Card[] inputCards = new Card[]{
                new Card(9, Suits.SPADES),
                new Card(HighCard.King, Suits.SPADES),
                new Card(HighCard.Queen, Suits.SPADES),
                new Card(HighCard.Jack, Suits.SPADES),
                new Card(10, Suits.SPADES)};

        final boolean actualAnswer = calculator.isStraightFlush(inputCards);
        assertTrue(actualAnswer);
    }

    @Test
    public void testIsStraightFlushFalse() throws Exception {
        final Calculator calculator = new Calculator();
        final Card[] inputCards = new Card[]{
                new Card(9, Suits.CLUBS),
                new Card(HighCard.King, Suits.SPADES),
                new Card(HighCard.Queen, Suits.SPADES),
                new Card(HighCard.Jack, Suits.SPADES),
                new Card(10, Suits.SPADES)};

        final boolean actualAnswer = calculator.isStraightFlush(inputCards);
        assertFalse(actualAnswer);
    }

    @Test
    public void testIsFourOFAKindTrue() throws Exception {
        final Calculator calculator = new Calculator();
        final Card[] inputCards = new Card[]{
                new Card(HighCard.Ace, Suits.SPADES),
                new Card(HighCard.King, Suits.SPADES),
                new Card(HighCard.King, Suits.HERTZ),
                new Card(HighCard.King, Suits.CLUBS),
                new Card(HighCard.King, Suits.DIAMONDS)};

        final boolean actualAnswer = calculator.isFourOFAKind(inputCards);
        assertTrue(actualAnswer);
    }

    @Test
    public void testIsFourOFAKindFalse() throws Exception {
        final Calculator calculator = new Calculator();
        final Card[] inputCards = new Card[]{
                new Card(HighCard.Ace, Suits.SPADES),
                new Card(HighCard.King, Suits.SPADES),
                new Card(HighCard.King, Suits.HERTZ),
                new Card(HighCard.King, Suits.CLUBS),
                new Card(HighCard.Ace, Suits.DIAMONDS)};

        final boolean actualAnswer = calculator.isFourOFAKind(inputCards);
        assertFalse(actualAnswer);
    }

    @Test
    public void testIsFullHouseTrue() throws Exception {
        final Calculator calculator = new Calculator();
        final Card[] inputCards = new Card[]{
                new Card(HighCard.Ace, Suits.SPADES),
                new Card(HighCard.Ace, Suits.DIAMONDS),
                new Card(HighCard.Ace, Suits.HERTZ),
                new Card(HighCard.King, Suits.CLUBS),
                new Card(HighCard.King, Suits.DIAMONDS)};

        final boolean actualAnswer = calculator.isFullHouse(inputCards);
        assertTrue(actualAnswer);
    }

    @Test
    public void testIsFullHouseFalse() throws Exception {
        final Calculator calculator = new Calculator();
        final Card[] inputCards = new Card[]{
                new Card(HighCard.Ace, Suits.SPADES),
                new Card(HighCard.Ace, Suits.DIAMONDS),
                new Card(HighCard.Ace, Suits.HERTZ),
                new Card(HighCard.King, Suits.CLUBS),
                new Card(HighCard.Jack, Suits.DIAMONDS)};

        final boolean actualAnswer = calculator.isFullHouse(inputCards);
        assertFalse(actualAnswer);
    }

    @Test
    public void testIsFlushTrue() throws Exception {
        final Calculator calculator = new Calculator();
        final Card[] inputCards = new Card[]{
                new Card(9, Suits.HERTZ),
                new Card(4, Suits.HERTZ),
                new Card(7, Suits.HERTZ),
                new Card(2, Suits.HERTZ),
                new Card(3, Suits.HERTZ)};

        final boolean actualAnswer = calculator.isFlush(inputCards);
        assertTrue(actualAnswer);
    }

    @Test
    public void testIsFlushFalse() throws Exception {
        final Calculator calculator = new Calculator();
        final Card[] inputCards = new Card[]{
                new Card(9, Suits.HERTZ),
                new Card(8, Suits.HERTZ),
                new Card(7, Suits.CLUBS),
                new Card(6, Suits.HERTZ),
                new Card(5, Suits.HERTZ)};

        final boolean actualAnswer = calculator.isFlush(inputCards);
        assertFalse(actualAnswer);
    }

    @Test
    public void testIsStraightTrue() throws Exception {
        final Calculator calculator = new Calculator();
        final Card[] inputCards = new Card[]{
                new Card(9, Suits.DIAMONDS),
                new Card(8, Suits.HERTZ),
                new Card(7, Suits.CLUBS),
                new Card(6, Suits.HERTZ),
                new Card(5, Suits.HERTZ)};

        final boolean actualAnswer = calculator.isStraight(inputCards);
        assertTrue(actualAnswer);
    }

    @Test
    public void testIsStraightFalse() throws Exception {
        final Calculator calculator = new Calculator();
        final Card[] inputCards = new Card[]{
                new Card(9, Suits.DIAMONDS),
                new Card(8, Suits.HERTZ),
                new Card(2, Suits.CLUBS),
                new Card(6, Suits.HERTZ),
                new Card(5, Suits.HERTZ)};

        final boolean actualAnswer = calculator.isStraight(inputCards);
        assertFalse(actualAnswer);
    }

    @Test
    public void testIsThreeOfAKindTrue() throws Exception {
        final Calculator calculator = new Calculator();
        final Card[] inputCards = new Card[]{
                new Card(HighCard.Ace, Suits.DIAMONDS),
                new Card(HighCard.King, Suits.HERTZ),
                new Card(6, Suits.DIAMONDS),
                new Card(6, Suits.HERTZ),
                new Card(6, Suits.CLUBS)};

        final boolean actualAnswer = calculator.isThreeOfAKind(inputCards);
        assertTrue(actualAnswer);
    }

    @Test
    public void testIsThreeOfAKindFalse() throws Exception {
        final Calculator calculator = new Calculator();
        final Card[] inputCards = new Card[]{
                new Card(HighCard.Ace, Suits.DIAMONDS),
                new Card(HighCard.King, Suits.HERTZ),
                new Card(6, Suits.CLUBS),
                new Card(6, Suits.HERTZ),
                new Card(4, Suits.CLUBS)};

        final boolean actualAnswer = calculator.isThreeOfAKind(inputCards);
        assertFalse(actualAnswer);
    }

    @Test
    public void testIsTwoPairTrue() throws Exception {
        final Calculator calculator = new Calculator();
        final Card[] inputCards = new Card[]{
                new Card(HighCard.Ace, Suits.DIAMONDS),
                new Card(9, Suits.HERTZ),
                new Card(9, Suits.CLUBS),
                new Card(6, Suits.HERTZ),
                new Card(6, Suits.CLUBS)};

        final boolean actualAnswer = calculator.isTwoPair(inputCards);
        assertTrue(actualAnswer);
    }

    @Test
    public void testIsTwoPairFalse() throws Exception {
        final Calculator calculator = new Calculator();
        final Card[] inputCards = new Card[]{
                new Card(HighCard.Ace, Suits.DIAMONDS),
                new Card(10, Suits.HERTZ),
                new Card(9, Suits.CLUBS),
                new Card(6, Suits.HERTZ),
                new Card(6, Suits.CLUBS)};

        final boolean actualAnswer = calculator.isTwoPair(inputCards);
        assertFalse(actualAnswer);
    }

    @Test
    public void testIsPairTrue() throws Exception {
        final Calculator calculator = new Calculator();
        final Card[] inputCards = new Card[]{
                new Card(HighCard.Ace, Suits.DIAMONDS),
                new Card(10, Suits.HERTZ),
                new Card(9, Suits.CLUBS),
                new Card(6, Suits.HERTZ),
                new Card(6, Suits.CLUBS)};

        final boolean actualAnswer = calculator.isPair(inputCards);
        assertTrue(actualAnswer);
    }

    @Test
    public void testIsPairFalse() throws Exception {
        final Calculator calculator = new Calculator();
        final Card[] inputCards = new Card[]{
                new Card(HighCard.Ace, Suits.DIAMONDS),
                new Card(10, Suits.HERTZ),
                new Card(9, Suits.CLUBS),
                new Card(6, Suits.HERTZ),
                new Card(3, Suits.CLUBS)};

        final boolean actualAnswer = calculator.isPair(inputCards);
        assertFalse(actualAnswer);
    }

    @Test
    public void testGetAllSortedNumericValuesWhenArrayNotSorted() throws Exception {
        final Calculator calculator = new Calculator();
        final int[] expectedResult = new int[]{3, 6, 9, 10, 14};
        final Card[] inputCards = new Card[]{
                new Card(HighCard.Ace, Suits.DIAMONDS),
                new Card(10, Suits.HERTZ),
                new Card(3, Suits.CLUBS),
                new Card(6, Suits.HERTZ),
                new Card(9, Suits.CLUBS)};

        final int[] actualResult = calculator.getAllSortedNumericValues(inputCards);
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void testGetAllSortedSuitsWhenArrayNotSorted() throws Exception {
        final Calculator calculator = new Calculator();
        final String[] expectedResult = new String[]{"CLUBS", "DIAMONDS", "HERTZ", "HERTZ", "SPADES"};
        final Card[] inputCards = new Card[]{
                new Card(HighCard.Ace, Suits.DIAMONDS),
                new Card(10, Suits.HERTZ),
                new Card(3, Suits.CLUBS),
                new Card(6, Suits.HERTZ),
                new Card(9, Suits.SPADES)};

        final String[] actualResult = calculator.getAllSortedSuits(inputCards);
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void testGetAllSortedNumericValuesWhenArraySorted() throws Exception {
        final Calculator calculator = new Calculator();
        final int[] expectedResult = new int[]{3, 6, 9, 10, 14};
        final Card[] inputCards = new Card[]{
                new Card(3, Suits.CLUBS),
                new Card(6, Suits.HERTZ),
                new Card(9, Suits.CLUBS),
                new Card(10, Suits.HERTZ),
                new Card(HighCard.Ace, Suits.DIAMONDS)};

        final int[] actualResult = calculator.getAllSortedNumericValues(inputCards);
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void testGetAllSortedSuitsWhenArraySorted() throws Exception {
        final Calculator calculator = new Calculator();
        final String[] expectedResult = new String[]{"CLUBS", "DIAMONDS", "HERTZ", "HERTZ", "SPADES"};
        final Card[] inputCards = new Card[]{
                new Card(3, Suits.CLUBS),
                new Card(HighCard.Ace, Suits.DIAMONDS),
                new Card(10, Suits.HERTZ),
                new Card(6, Suits.HERTZ),
                new Card(9, Suits.SPADES)};

        final String[] actualResult = calculator.getAllSortedSuits(inputCards);
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void testCurrentKnownCard() throws Exception {
        final Card[] inputPlayersCards = new Card[]{new Card(8, Suits.CLUBS), new Card(10, Suits.DIAMONDS)};
        final Card[] inputFlopCards = new Card[]{new Card(7, Suits.CLUBS), new Card(2, Suits.DIAMONDS), new Card(9, Suits.HERTZ)};
        final Card[] expectedAnswer = new Card[]{new Card(2, Suits.DIAMONDS), new Card(7, Suits.CLUBS), new Card(8, Suits.CLUBS),
                new Card(9, Suits.HERTZ), new Card(10, Suits.DIAMONDS)};
        final Distribution distribution = new Distribution(null);
        for (int i = 0; i < inputPlayersCards.length; i++) {
            distribution.addPlayersCard(inputPlayersCards[i], i);
        }
        for (int i = 0; i < inputFlopCards.length; i++) {
            distribution.addFlopCard(inputFlopCards[i], i);
        }
        final Card[] actualAnswer = Calculator.currentKnownCards(distribution);
        assertArrayEquals(expectedAnswer, actualAnswer);
    }

}