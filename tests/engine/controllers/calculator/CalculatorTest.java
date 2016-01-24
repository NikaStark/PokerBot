package engine.controllers.calculator;

import engine.models.Card;
import engine.models.Enums.HighCard;
import engine.models.Enums.Suits;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;


public class CalculatorTest {

//    @Test
//    public void testCreateCurrentDeckOfCards() throws Exception {
//        Card[] currentKnownCards = new Card[]{new Card(3, Suits.SPADES), new Card(4, Suits.SPADES)};
//        Distribution distribution = new Distribution(currentKnownCards);
//        calculator calculator = new calculator(distribution);
////        System.out.println(calculator.bestCombination(new Card[]{new Card(2, Suits.SPADES), new Card(3, Suits.SPADES), new Card(4, Suits.SPADES),
////                new Card(5, Suits.SPADES), new Card(6, Suits.SPADES), new Card(9, Suits.SPADES), new Card(10, Suits.SPADES)}));
////        System.out.println();
////        for (int chances : calculator.getTableChances()) {
////            System.out.print(chances);
////        }
//        int[] chances = calculator.getTableChances();
//        for (int i = 0; i < chances.length; i++) {
//            System.out.println(chances[i]);
//        }
////        System.out.println();
////        System.out.print(calculator.getPossibleBoard().get(32333)[0].getDignity());
////        System.out.println(calculator.getPossibleBoard().get(32333)[0].getSuit());
////        System.out.print(calculator.getPossibleBoard().get(2118759)[1].getDignity());
////        System.out.println(calculator.getPossibleBoard().get(2118759)[1].getSuit());
////        System.out.print(calculator.getPossibleBoard().get(18759)[2].getDignity());
////        System.out.println(calculator.getPossibleBoard().get(18759)[2].getSuit());
////        System.out.print(calculator.getPossibleBoard().get(21159)[3].getDignity());
////        System.out.println(calculator.getPossibleBoard().get(21159)[3].getSuit());
////        System.out.print(calculator.getPossibleBoard().get(21)[4].getDignity());
////        System.out.println(calculator.getPossibleBoard().get(21)[4].getSuit());
////
////        System.out.print(calculator.getCurrentDeckOfCards()[0].getDignity());
////        System.out.println(calculator.getCurrentDeckOfCards()[0].getSuit());
////        System.out.print(calculator.getCurrentDeckOfCards()[1].getDignity());
////        System.out.println(calculator.getCurrentDeckOfCards()[1].getSuit());
////        System.out.print(calculator.getCurrentDeckOfCards()[2].getDignity());
////        System.out.println(calculator.getCurrentDeckOfCards()[2].getSuit());
////        System.out.print(calculator.getCurrentDeckOfCards()[3].getDignity());
////        System.out.println(calculator.getCurrentDeckOfCards()[3].getSuit());
////        System.out.print(calculator.getCurrentDeckOfCards()[49].getDignity());
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
//        final calculator calculator = new calculator(null);
//        final int expectedAnswer = 7;
//        final Card[] inputCards = new Card[]{
//                new Card(8, Suits.SPADES),
//                new Card(HighCard.Ace, Suits.DIAMONDS),
//                new Card(8, Suits.CLUBS),
//                new Card(9, Suits.SPADES),
//                new Card(HighCard.Jack, Suits.CLUBS),
//                new Card(10, Suits.SPADES),
//                new Card(HighCard.Ace, Suits.CLUBS)
//        };
//
//        final int actualAnswer = calculator.bestCombination(inputCards);
//        assertEquals(expectedAnswer, actualAnswer);
//    }
//
//    @Test
//    public void testCleverSortOut() throws Exception {
//        final calculator calculator = new calculator(null);
//        final ArrayList<Card[]> expectedAnswer = new ArrayList<>(
//                Arrays.asList(
//                new Card[]{
//                        new Card(HighCard.Ace, Suits.SPADES),
//                        new Card(HighCard.King, Suits.SPADES),
//                        new Card(HighCard.Queen, Suits.SPADES)},
//                new Card[]{
//                        new Card(HighCard.Ace, Suits.SPADES),
//                        new Card(HighCard.King, Suits.SPADES),
//                        new Card(HighCard.Jack, Suits.SPADES)},
//                new Card[]{
//                        new Card(HighCard.Ace, Suits.SPADES),
//                        new Card(HighCard.Queen, Suits.SPADES),
//                        new Card(HighCard.Jack, Suits.SPADES)},
//                new Card[]{
//                        new Card(HighCard.King, Suits.SPADES),
//                        new Card(HighCard.Queen, Suits.SPADES),
//                        new Card(HighCard.Jack, Suits.SPADES)}
//                )
//        );
//        final Card[] inputCards = new Card[]{
//                new Card(HighCard.Ace, Suits.SPADES),
//                new Card(HighCard.King, Suits.SPADES),
//                new Card(HighCard.Queen, Suits.SPADES),
//                new Card(HighCard.Jack, Suits.SPADES)
//        };
//        final ArrayList<Card[]> actualAnswer = new ArrayList<>();
////        calculator.cleverSortOut(inputCards, actualAnswer, null, 0, 1, 3);
//        assertArrayEquals(expectedAnswer.toArray(), actualAnswer.toArray());
//    }
//
//    @Test
//    public void testMethod() throws Exception {
//        final calculator calculator = new calculator(null);
//        final ArrayList<Card[]> expectedAnswer = new ArrayList<>(
//                Arrays.asList(
//                        new Card[]{
//                                new Card(HighCard.Ace, Suits.SPADES),
//                                new Card(HighCard.King, Suits.SPADES),
//                                new Card(HighCard.Queen, Suits.SPADES)},
//                        new Card[]{
//                                new Card(HighCard.Ace, Suits.SPADES),
//                                new Card(HighCard.King, Suits.SPADES),
//                                new Card(HighCard.Jack, Suits.SPADES)},
//                        new Card[]{
//                                new Card(HighCard.Ace, Suits.SPADES),
//                                new Card(HighCard.Queen, Suits.SPADES),
//                                new Card(HighCard.Jack, Suits.SPADES)},
//                        new Card[]{
//                                new Card(HighCard.King, Suits.SPADES),
//                                new Card(HighCard.Queen, Suits.SPADES),
//                                new Card(HighCard.Jack, Suits.SPADES)}
//                )
//        );
//        final Card[] inputCards = new Card[]{
//                new Card(HighCard.Ace, Suits.SPADES),
//                new Card(HighCard.King, Suits.SPADES),
//                new Card(HighCard.Queen, Suits.SPADES),
//                new Card(HighCard.Jack, Suits.SPADES)
//        };
////        final ArrayList<Card[]> actualAnswer = calculator.method(inputCards,3);
////        assertArrayEquals(expectedAnswer.toArray(), actualAnswer.toArray());
//    }

//    @Test
//    public void test() throws Exception {
//        final calculator calculator = new calculator();
//        final ArrayList<Card> inputCards = new ArrayList<>(Arrays.asList(new Card[]{
//                new Card(HighCard.Ace, Suits.HERTZ),
//                new Card(HighCard.King, Suits.HERTZ),
//                new Card(HighCard.Queen, Suits.HERTZ),
//                new Card(HighCard.Jack, Suits.HERTZ)}));
//        final ArrayList<Card[]> expectedAnswer = new ArrayList<>(Arrays.asList(new Card[][]{
//                new Card[]{
//                        new Card(HighCard.Ace,Suits.HERTZ),
//                        new Card(HighCard.King, Suits.HERTZ),
//                        new Card(HighCard.Queen, Suits.HERTZ)
//                },
//                new Card[]{
//                        new Card(HighCard.Ace, Suits.HERTZ),
//                        new Card(HighCard.King, Suits.HERTZ),
//                        new Card(HighCard.Jack, Suits.HERTZ)
//                },
//                new Card[]{
//                        new Card(HighCard.Ace, Suits.HERTZ),
//                        new Card(HighCard.Queen, Suits.HERTZ),
//                        new Card(HighCard.Jack, Suits.HERTZ)
//                },
//                new Card[]{
//                        new Card(HighCard.King, Suits.HERTZ),
//                        new Card(HighCard.Queen, Suits.HERTZ),
//                        new Card(HighCard.Jack, Suits.HERTZ)
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
    public void testGetTableChances() throws Exception {

    }

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
//        for (Card[] cards : expectedAnswer) { // Important test and this is second control
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

}