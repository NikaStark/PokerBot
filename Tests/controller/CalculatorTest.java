package controller;

import models.Card;
import models.Enums.HighCard;
import models.Enums.Suits;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


public class CalculatorTest {

//    @Test
//    public void testCreateCurrentDeckOfCards() throws Exception {
//        Card[] currentKnownCards = new Card[]{new Card(3, Suits.Spades), new Card(4, Suits.Spades)};
//        Distribution distribution = new Distribution(currentKnownCards);
//        Calculator calculator = new Calculator(distribution);
////        System.out.println(calculator.bestCombination(new Card[]{new Card(2, Suits.Spades), new Card(3, Suits.Spades), new Card(4, Suits.Spades),
////                new Card(5, Suits.Spades), new Card(6, Suits.Spades), new Card(9, Suits.Spades), new Card(10, Suits.Spades)}));
////        System.out.println();
////        for (int chances : calculator.getTableChances()) {
////            System.out.print(chances);
////        }
//        int[] chances = calculator.getTableChances();
//        for (int i = 0; i < chances.length; i++) {
//            System.out.println(chances[i]);
//        }
////        System.out.println();
////        System.out.print(calculator.getPossibleBoard().get(32333)[0].getNumericValue());
////        System.out.println(calculator.getPossibleBoard().get(32333)[0].getSuit());
////        System.out.print(calculator.getPossibleBoard().get(2118759)[1].getNumericValue());
////        System.out.println(calculator.getPossibleBoard().get(2118759)[1].getSuit());
////        System.out.print(calculator.getPossibleBoard().get(18759)[2].getNumericValue());
////        System.out.println(calculator.getPossibleBoard().get(18759)[2].getSuit());
////        System.out.print(calculator.getPossibleBoard().get(21159)[3].getNumericValue());
////        System.out.println(calculator.getPossibleBoard().get(21159)[3].getSuit());
////        System.out.print(calculator.getPossibleBoard().get(21)[4].getNumericValue());
////        System.out.println(calculator.getPossibleBoard().get(21)[4].getSuit());
////
////        System.out.print(calculator.getCurrentDeckOfCards()[0].getNumericValue());
////        System.out.println(calculator.getCurrentDeckOfCards()[0].getSuit());
////        System.out.print(calculator.getCurrentDeckOfCards()[1].getNumericValue());
////        System.out.println(calculator.getCurrentDeckOfCards()[1].getSuit());
////        System.out.print(calculator.getCurrentDeckOfCards()[2].getNumericValue());
////        System.out.println(calculator.getCurrentDeckOfCards()[2].getSuit());
////        System.out.print(calculator.getCurrentDeckOfCards()[3].getNumericValue());
////        System.out.println(calculator.getCurrentDeckOfCards()[3].getSuit());
////        System.out.print(calculator.getCurrentDeckOfCards()[49].getNumericValue());
////        System.out.println(calculator.getCurrentDeckOfCards()[49].getSuit());
//    }
//
//    @Test
//    public void testUpdateCurrentKnownCards() throws Exception {
//
//    }
//
//    @Test
//    public void testBestCombination() throws Exception {
//        final Calculator calculator = new Calculator(null);
//        final int expectedAnswer = 7;
//        final Card[] inputCards = new Card[]{
//                new Card(8, Suits.Spades),
//                new Card(HighCard.Ace, Suits.Diamonds),
//                new Card(8, Suits.Clubs),
//                new Card(9, Suits.Spades),
//                new Card(HighCard.Jack, Suits.Clubs),
//                new Card(10, Suits.Spades),
//                new Card(HighCard.Ace, Suits.Clubs)
//        };
//
//        final int actualAnswer = calculator.bestCombination(inputCards);
//        assertEquals(expectedAnswer, actualAnswer);
//    }
//
//    @Test
//    public void testCleverSortOut() throws Exception {
//        final Calculator calculator = new Calculator(null);
//        final ArrayList<Card[]> expectedAnswer = new ArrayList<>(
//                Arrays.asList(
//                new Card[]{
//                        new Card(HighCard.Ace, Suits.Spades),
//                        new Card(HighCard.King, Suits.Spades),
//                        new Card(HighCard.Queen, Suits.Spades)},
//                new Card[]{
//                        new Card(HighCard.Ace, Suits.Spades),
//                        new Card(HighCard.King, Suits.Spades),
//                        new Card(HighCard.Jack, Suits.Spades)},
//                new Card[]{
//                        new Card(HighCard.Ace, Suits.Spades),
//                        new Card(HighCard.Queen, Suits.Spades),
//                        new Card(HighCard.Jack, Suits.Spades)},
//                new Card[]{
//                        new Card(HighCard.King, Suits.Spades),
//                        new Card(HighCard.Queen, Suits.Spades),
//                        new Card(HighCard.Jack, Suits.Spades)}
//                )
//        );
//        final Card[] inputCards = new Card[]{
//                new Card(HighCard.Ace, Suits.Spades),
//                new Card(HighCard.King, Suits.Spades),
//                new Card(HighCard.Queen, Suits.Spades),
//                new Card(HighCard.Jack, Suits.Spades)
//        };
//        final ArrayList<Card[]> actualAnswer = new ArrayList<>();
////        calculator.cleverSortOut(inputCards, actualAnswer, null, 0, 1, 3);
//        assertArrayEquals(expectedAnswer.toArray(), actualAnswer.toArray());
//    }
//
//    @Test
//    public void testMethod() throws Exception {
//        final Calculator calculator = new Calculator(null);
//        final ArrayList<Card[]> expectedAnswer = new ArrayList<>(
//                Arrays.asList(
//                        new Card[]{
//                                new Card(HighCard.Ace, Suits.Spades),
//                                new Card(HighCard.King, Suits.Spades),
//                                new Card(HighCard.Queen, Suits.Spades)},
//                        new Card[]{
//                                new Card(HighCard.Ace, Suits.Spades),
//                                new Card(HighCard.King, Suits.Spades),
//                                new Card(HighCard.Jack, Suits.Spades)},
//                        new Card[]{
//                                new Card(HighCard.Ace, Suits.Spades),
//                                new Card(HighCard.Queen, Suits.Spades),
//                                new Card(HighCard.Jack, Suits.Spades)},
//                        new Card[]{
//                                new Card(HighCard.King, Suits.Spades),
//                                new Card(HighCard.Queen, Suits.Spades),
//                                new Card(HighCard.Jack, Suits.Spades)}
//                )
//        );
//        final Card[] inputCards = new Card[]{
//                new Card(HighCard.Ace, Suits.Spades),
//                new Card(HighCard.King, Suits.Spades),
//                new Card(HighCard.Queen, Suits.Spades),
//                new Card(HighCard.Jack, Suits.Spades)
//        };
////        final ArrayList<Card[]> actualAnswer = calculator.method(inputCards,3);
////        assertArrayEquals(expectedAnswer.toArray(), actualAnswer.toArray());
//    }

//    @Test
//    public void test() throws Exception {
//        final Calculator calculator = new Calculator();
//        final ArrayList<Card> inputCards = new ArrayList<>(Arrays.asList(new Card[]{
//                new Card(HighCard.Ace, Suits.Hertz),
//                new Card(HighCard.King, Suits.Hertz),
//                new Card(HighCard.Queen, Suits.Hertz),
//                new Card(HighCard.Jack, Suits.Hertz)}));
//        final ArrayList<Card[]> expectedAnswer = new ArrayList<>(Arrays.asList(new Card[][]{
//                new Card[]{
//                        new Card(HighCard.Ace,Suits.Hertz),
//                        new Card(HighCard.King, Suits.Hertz),
//                        new Card(HighCard.Queen, Suits.Hertz)
//                },
//                new Card[]{
//                        new Card(HighCard.Ace, Suits.Hertz),
//                        new Card(HighCard.King, Suits.Hertz),
//                        new Card(HighCard.Jack, Suits.Hertz)
//                },
//                new Card[]{
//                        new Card(HighCard.Ace, Suits.Hertz),
//                        new Card(HighCard.Queen, Suits.Hertz),
//                        new Card(HighCard.Jack, Suits.Hertz)
//                },
//                new Card[]{
//                        new Card(HighCard.King, Suits.Hertz),
//                        new Card(HighCard.Queen, Suits.Hertz),
//                        new Card(HighCard.Jack, Suits.Hertz)
//                }
//        }));
//        final ArrayList<Card[]> actualAnswer2 = calculator.smartSample1(inputCards, 3);
//        final ArrayList<Card[]> actualAnswer1 = calculator.smartSample(inputCards, 3);
//        for (Card[] cards : expectedAnswer) {
//            for (Card card : cards) {
//                System.out.print(card.toString() + " ");
//            }
//            System.out.println();
//        }
//        for (Card[] cards : actualAnswer1) {
//            for (Card card : cards) {
//                System.out.print(card.toString() + " ");
//            }
//            System.out.println();
//        }
//        for (Card[] cards : actualAnswer2) {
//            for (Card card : cards) {
//                System.out.print(card.toString() + " ");
//            }
//            System.out.println();
//        }
//        assertArrayEquals(expectedAnswer.toArray(new Card[expectedAnswer.size()][]),
//                actualAnswer1.toArray(new Card[actualAnswer1.size()][]));
//        assertArrayEquals(expectedAnswer.toArray(new Card[expectedAnswer.size()][]),
//                actualAnswer2.toArray(new Card[actualAnswer2.size()][]));
//    }

    @Test
    public void testSmartSample() throws Exception {
        final Calculator calculator = new Calculator();
        final ArrayList<Card> inputCards = new ArrayList<>(Arrays.asList(new Card[]{
                new Card(HighCard.Ace, Suits.Hertz),
                new Card(HighCard.King, Suits.Hertz),
                new Card(HighCard.Queen, Suits.Hertz),
                new Card(HighCard.Jack, Suits.Hertz)}));
        final ArrayList<Card[]> expectedAnswer = new ArrayList<>(Arrays.asList(new Card[][]{
                new Card[]{
                        new Card(HighCard.Ace,Suits.Hertz),
                        new Card(HighCard.King, Suits.Hertz),
                        new Card(HighCard.Queen, Suits.Hertz)
                },
                new Card[]{
                        new Card(HighCard.Ace, Suits.Hertz),
                        new Card(HighCard.King, Suits.Hertz),
                        new Card(HighCard.Jack, Suits.Hertz)
                },
                new Card[]{
                        new Card(HighCard.Ace, Suits.Hertz),
                        new Card(HighCard.Queen, Suits.Hertz),
                        new Card(HighCard.Jack, Suits.Hertz)
                },
                new Card[]{
                        new Card(HighCard.King, Suits.Hertz),
                        new Card(HighCard.Queen, Suits.Hertz),
                        new Card(HighCard.Jack, Suits.Hertz)
                }
        }));
        final ArrayList<Card[]> actualAnswer = calculator.smartSample(inputCards, 3);
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
        final int expectedAnswer = 0;
        final Card[] inputCards = new Card[] {
                new Card(HighCard.Ace, Suits.Spades),
                new Card(HighCard.King, Suits.Spades),
                new Card(HighCard.Queen, Suits.Spades),
                new Card(HighCard.Jack, Suits.Spades),
                new Card(10, Suits.Spades)};

        final int actualAnswer = calculator.whatCombination(inputCards);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testWhatCombinationWhenStraightFlush() throws Exception {
        final Calculator calculator = new Calculator();
        final int expectedAnswer = 1;
        final Card[] inputCards = new Card[] {
                new Card(6, Suits.Clubs),
                new Card(8, Suits.Clubs),
                new Card(7, Suits.Clubs),
                new Card(9, Suits.Clubs),
                new Card(5, Suits.Clubs)};

        final int actualAnswer = calculator.whatCombination(inputCards);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testWhatCombinationWhenFourOfAKind() throws Exception {
        final Calculator calculator = new Calculator();
        final int expectedAnswer = 2;
        final Card[] inputCards = new Card[] {
                new Card(7, Suits.Clubs),
                new Card(7, Suits.Diamonds),
                new Card(7, Suits.Spades),
                new Card(7, Suits.Hertz),
                new Card(9, Suits.Clubs)};

        final int actualAnswer = calculator.whatCombination(inputCards);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testWhatCombinationWhenFullHouse() throws Exception {
        final Calculator calculator = new Calculator();
        final int expectedAnswer = 3;
        final Card[] inputCards = new Card[] {
                new Card(7, Suits.Clubs),
                new Card(7, Suits.Diamonds),
                new Card(7, Suits.Spades),
                new Card(9, Suits.Hertz),
                new Card(9, Suits.Clubs)};

        final int actualAnswer = calculator.whatCombination(inputCards);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testWhatCombinationWhenFlush() throws Exception {
        final Calculator calculator = new Calculator();
        final int expectedAnswer = 4;
        final Card[] inputCards = new Card[] {
                new Card(7, Suits.Hertz),
                new Card(4, Suits.Hertz),
                new Card(5, Suits.Hertz),
                new Card(9, Suits.Hertz),
                new Card(2, Suits.Hertz)};

        final int actualAnswer = calculator.whatCombination(inputCards);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testWhatCombinationWhenStraight() throws Exception {
        final Calculator calculator = new Calculator();
        final int expectedAnswer = 5;
        final Card[] inputCards = new Card[] {
                new Card(6, Suits.Clubs),
                new Card(4, Suits.Hertz),
                new Card(5, Suits.Spades),
                new Card(3, Suits.Hertz),
                new Card(2, Suits.Hertz)};

        final int actualAnswer = calculator.whatCombination(inputCards);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testWhatCombinationWhenThreeOfAKind() throws Exception {
        final Calculator calculator = new Calculator();
        final int expectedAnswer = 6;
        final Card[] inputCards = new Card[] {
                new Card(6, Suits.Clubs),
                new Card(4, Suits.Hertz),
                new Card(6, Suits.Spades),
                new Card(3, Suits.Hertz),
                new Card(6, Suits.Hertz)};

        final int actualAnswer = calculator.whatCombination(inputCards);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testWhatCombinationWhenTwoPair() throws Exception {
        final Calculator calculator = new Calculator();
        final int expectedAnswer = 7;
        final Card[] inputCards = new Card[] {
                new Card(8, Suits.Diamonds),
                new Card(7, Suits.Hertz),
                new Card(3, Suits.Spades),
                new Card(3, Suits.Diamonds),
                new Card(8, Suits.Hertz)};

        final int actualAnswer = calculator.whatCombination(inputCards);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testWhatCombinationWhenPair() throws Exception {
        final Calculator calculator = new Calculator();
        final int expectedAnswer = 8;
        final Card[] inputCards = new Card[] {
                new Card(8, Suits.Diamonds),
                new Card(7, Suits.Hertz),
                new Card(3, Suits.Spades),
                new Card(4, Suits.Diamonds),
                new Card(8, Suits.Hertz)};

        final int actualAnswer = calculator.whatCombination(inputCards);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testWhatCombinationWhenKicker() throws Exception {
        final Calculator calculator = new Calculator();
        final int expectedAnswer = 9;
        final Card[] inputCards = new Card[] {
                new Card(8, Suits.Diamonds),
                new Card(7, Suits.Hertz),
                new Card(3, Suits.Spades),
                new Card(4, Suits.Diamonds),
                new Card(2, Suits.Clubs)};

        final int actualAnswer = calculator.whatCombination(inputCards);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testIsRoyalFlushWhenTrue() throws Exception {
        final Calculator calculator = new Calculator();
        final boolean expectedAnswer = true;
        final Card[] inputCards = new Card[]{
                new Card(HighCard.Ace, Suits.Spades),
                new Card(HighCard.King, Suits.Spades),
                new Card(HighCard.Queen, Suits.Spades),
                new Card(HighCard.Jack, Suits.Spades),
                new Card(10, Suits.Spades)};

        final boolean actualAnswer = calculator.isRoyalFlush(inputCards);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testIsRoyalFlushWhenFalse() throws Exception {
        final Calculator calculator = new Calculator();
        final boolean expectedAnswer = false;
        final Card[] inputCards = new Card[]{
                new Card(HighCard.Ace, Suits.Spades),
                new Card(HighCard.King, Suits.Spades),
                new Card(HighCard.Queen, Suits.Spades),
                new Card(HighCard.Jack, Suits.Spades),
                new Card(9, Suits.Spades)};

        final boolean actualAnswer = calculator.isRoyalFlush(inputCards);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testIsStraightFlushTrue() throws Exception {
        final Calculator calculator = new Calculator();
        final boolean expectedAnswer = true;
        final Card[] inputCards = new Card[]{
                new Card(9, Suits.Spades),
                new Card(HighCard.King, Suits.Spades),
                new Card(HighCard.Queen, Suits.Spades),
                new Card(HighCard.Jack, Suits.Spades),
                new Card(10, Suits.Spades)};

        final boolean actualAnswer = calculator.isStraightFlush(inputCards);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testIsStraightFlushFalse() throws Exception {
        final Calculator calculator = new Calculator();
        final boolean expectedAnswer = false;
        final Card[] inputCards = new Card[]{
                new Card(9, Suits.Clubs),
                new Card(HighCard.King, Suits.Spades),
                new Card(HighCard.Queen, Suits.Spades),
                new Card(HighCard.Jack, Suits.Spades),
                new Card(10, Suits.Spades)};

        final boolean actualAnswer = calculator.isStraightFlush(inputCards);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testIsFourOFAKindTrue() throws Exception {
        final Calculator calculator = new Calculator();
        final boolean expectedAnswer = true;
        final Card[] inputCards = new Card[]{
                new Card(HighCard.Ace, Suits.Spades),
                new Card(HighCard.King, Suits.Spades),
                new Card(HighCard.King, Suits.Hertz),
                new Card(HighCard.King, Suits.Clubs),
                new Card(HighCard.King, Suits.Diamonds)};

        final boolean actualAnswer = calculator.isFourOFAKind(inputCards);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testIsFourOFAKindFalse() throws Exception {
        final Calculator calculator = new Calculator();
        final boolean expectedAnswer = false;
        final Card[] inputCards = new Card[]{
                new Card(HighCard.Ace, Suits.Spades),
                new Card(HighCard.King, Suits.Spades),
                new Card(HighCard.King, Suits.Hertz),
                new Card(HighCard.King, Suits.Clubs),
                new Card(HighCard.Ace, Suits.Diamonds)};

        final boolean actualAnswer = calculator.isFourOFAKind(inputCards);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testIsFullHouseTrue() throws Exception {
        final Calculator calculator = new Calculator();
        final boolean expectedAnswer = true;
        final Card[] inputCards = new Card[]{
                new Card(HighCard.Ace, Suits.Spades),
                new Card(HighCard.Ace, Suits.Diamonds),
                new Card(HighCard.Ace, Suits.Hertz),
                new Card(HighCard.King, Suits.Clubs),
                new Card(HighCard.King, Suits.Diamonds)};

        final boolean actualAnswer = calculator.isFullHouse(inputCards);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testIsFullHouseFalse() throws Exception {
        final Calculator calculator = new Calculator();
        final boolean expectedAnswer = false;
        final Card[] inputCards = new Card[]{
                new Card(HighCard.Ace, Suits.Spades),
                new Card(HighCard.Ace, Suits.Diamonds),
                new Card(HighCard.Ace, Suits.Hertz),
                new Card(HighCard.King, Suits.Clubs),
                new Card(HighCard.Jack, Suits.Diamonds)};

        final boolean actualAnswer = calculator.isFullHouse(inputCards);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testIsFlushTrue() throws Exception {
        final Calculator calculator = new Calculator();
        final boolean expectedAnswer = true;
        final Card[] inputCards = new Card[]{
                new Card(9, Suits.Hertz),
                new Card(4, Suits.Hertz),
                new Card(7, Suits.Hertz),
                new Card(2, Suits.Hertz),
                new Card(3, Suits.Hertz)};

        final boolean actualAnswer = calculator.isFlush(inputCards);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testIsFlushFalse() throws Exception {
        final Calculator calculator = new Calculator();
        final boolean expectedAnswer = false;
        final Card[] inputCards = new Card[]{
                new Card(9, Suits.Hertz),
                new Card(8, Suits.Hertz),
                new Card(7, Suits.Clubs),
                new Card(6, Suits.Hertz),
                new Card(5, Suits.Hertz)};

        final boolean actualAnswer = calculator.isFlush(inputCards);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testIsStraightTrue() throws Exception {
        final Calculator calculator = new Calculator();
        final boolean expectedAnswer = true;
        final Card[] inputCards = new Card[]{
                new Card(9, Suits.Diamonds),
                new Card(8, Suits.Hertz),
                new Card(7, Suits.Clubs),
                new Card(6, Suits.Hertz),
                new Card(5, Suits.Hertz)};

        final boolean actualAnswer = calculator.isStraight(inputCards);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testIsStraightFalse() throws Exception {
        final Calculator calculator = new Calculator();
        final boolean expectedAnswer = false;
        final Card[] inputCards = new Card[]{
                new Card(9, Suits.Diamonds),
                new Card(8, Suits.Hertz),
                new Card(2, Suits.Clubs),
                new Card(6, Suits.Hertz),
                new Card(5, Suits.Hertz)};

        final boolean actualAnswer = calculator.isStraight(inputCards);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testIsThreeOfAKindTrue() throws Exception {
        final Calculator calculator = new Calculator();
        final boolean expectedAnswer = true;
        final Card[] inputCards = new Card[]{
                new Card(HighCard.Ace, Suits.Diamonds),
                new Card(HighCard.King, Suits.Hertz),
                new Card(6, Suits.Diamonds),
                new Card(6, Suits.Hertz),
                new Card(6, Suits.Clubs)};

        final boolean actualAnswer = calculator.isThreeOfAKind(inputCards);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testIsThreeOfAKindFalse() throws Exception {
        final Calculator calculator = new Calculator();
        final boolean expectedAnswer = false;
        final Card[] inputCards = new Card[]{
                new Card(HighCard.Ace, Suits.Diamonds),
                new Card(HighCard.King, Suits.Hertz),
                new Card(6, Suits.Clubs),
                new Card(6, Suits.Hertz),
                new Card(4, Suits.Clubs)};

        final boolean actualAnswer = calculator.isThreeOfAKind(inputCards);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testIsTwoPairTrue() throws Exception {
        final Calculator calculator = new Calculator();
        final boolean expectedAnswer = true;
        final Card[] inputCards = new Card[]{
                new Card(HighCard.Ace, Suits.Diamonds),
                new Card(9, Suits.Hertz),
                new Card(9, Suits.Clubs),
                new Card(6, Suits.Hertz),
                new Card(6, Suits.Clubs)};

        final boolean actualAnswer = calculator.isTwoPair(inputCards);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testIsTwoPairFalse() throws Exception {
        final Calculator calculator = new Calculator();
        final boolean expectedAnswer = false;
        final Card[] inputCards = new Card[]{
                new Card(HighCard.Ace, Suits.Diamonds),
                new Card(10, Suits.Hertz),
                new Card(9, Suits.Clubs),
                new Card(6, Suits.Hertz),
                new Card(6, Suits.Clubs)};

        final boolean actualAnswer = calculator.isTwoPair(inputCards);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testIsPairTrue() throws Exception {
        final Calculator calculator = new Calculator();
        final boolean expectedAnswer = true;
        final Card[] inputCards = new Card[]{
                new Card(HighCard.Ace, Suits.Diamonds),
                new Card(10, Suits.Hertz),
                new Card(9, Suits.Clubs),
                new Card(6, Suits.Hertz),
                new Card(6, Suits.Clubs)};

        final boolean actualAnswer = calculator.isPair(inputCards);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testIsPairFalse() throws Exception {
        final Calculator calculator = new Calculator();
        final boolean expectedAnswer = false;
        final Card[] inputCards = new Card[]{
                new Card(HighCard.Ace, Suits.Diamonds),
                new Card(10, Suits.Hertz),
                new Card(9, Suits.Clubs),
                new Card(6, Suits.Hertz),
                new Card(3, Suits.Clubs)};

        final boolean actualAnswer = calculator.isPair(inputCards);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void testGetAllSortedNumericValuesWhenArrayNotSorted() throws Exception {
        final Calculator calculator = new Calculator();
        final int[] expectedResult = new int[]{3, 6, 9, 10, 14};
        final Card[] inputCards = new Card[]{
                new Card(HighCard.Ace, Suits.Diamonds),
                new Card(10, Suits.Hertz),
                new Card(3, Suits.Clubs),
                new Card(6, Suits.Hertz),
                new Card(9, Suits.Clubs)};

        final int[] actualResult = calculator.getAllSortedNumericValues(inputCards);
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void testGetAllSortedSuitsWhenArrayNotSorted() throws Exception {
        final Calculator calculator = new Calculator();
        final String[] expectedResult = new String[]{"Clubs", "Diamonds", "Hertz", "Hertz", "Spades"};
        final Card[] inputCards = new Card[]{
                new Card(HighCard.Ace, Suits.Diamonds),
                new Card(10, Suits.Hertz),
                new Card(3, Suits.Clubs),
                new Card(6, Suits.Hertz),
                new Card(9, Suits.Spades)};

        final String[] actualResult = calculator.getAllSortedSuits(inputCards);
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void testGetAllSortedNumericValuesWhenArraySorted() throws Exception {
        final Calculator calculator = new Calculator();
        final int[] expectedResult = new int[]{3, 6, 9, 10, 14};
        final Card[] inputCards = new Card[]{
                new Card(3, Suits.Clubs),
                new Card(6, Suits.Hertz),
                new Card(9, Suits.Clubs),
                new Card(10, Suits.Hertz),
                new Card(HighCard.Ace, Suits.Diamonds)};

        final int[] actualResult = calculator.getAllSortedNumericValues(inputCards);
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void testGetAllSortedSuitsWhenArraySorted() throws Exception {
        final Calculator calculator = new Calculator();
        final String[] expectedResult = new String[]{"Clubs", "Diamonds", "Hertz", "Hertz", "Spades"};
        final Card[] inputCards = new Card[]{
                new Card(3, Suits.Clubs),
                new Card(HighCard.Ace, Suits.Diamonds),
                new Card(10, Suits.Hertz),
                new Card(6, Suits.Hertz),
                new Card(9, Suits.Spades)};

        final String[] actualResult = calculator.getAllSortedSuits(inputCards);
        assertArrayEquals(expectedResult, actualResult);
    }

}