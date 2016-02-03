package engine.controllers.calculator;

import engine.models.Card;
import engine.models.Distribution;
import engine.models.Enums.Combinations;
import engine.models.Game;

import java.util.ArrayList;
import java.util.Arrays;

public class Calculator {

//TODO Second working method, but this getting much memory and time
//    protected ArrayList<Card[]> smartSample1(ArrayList<Card> src, int amountPosition) {
//        long t1=System.nanoTime();
//        ArrayList<Card[]> dest = new ArrayList<>(counterSmartSample(src.size(), amountPosition));
//        dest = subSmartSample1(src, dest, null, amountPosition, 0, 0);
//        long t2 = System.nanoTime();
//        long elapsedTime = t2-t1;
//        System.out.println("Elapsed time was "+elapsedTime+" ns");
//        return dest;
//    }
//
//    private ArrayList<Card[]> subSmartSample1(ArrayList<Card> src, ArrayList<Card[]> dest, ArrayList<Card> possibleNotKnownCard,
//                                             int amountPosition, int startIndex, int counterRecursion) {
//        if (possibleNotKnownCard == null) {
//            possibleNotKnownCard = new ArrayList<>(amountPosition);
//        }
//        for (int i = startIndex; i < src.size(); i++) {
//            possibleNotKnownCard.add(src.get(i));
//            if (possibleNotKnownCard.size() == amountPosition) {
//                dest.add(possibleNotKnownCard.toArray(new Card[possibleNotKnownCard.size()]));
//            } else {
//                dest = subSmartSample1(src, dest, possibleNotKnownCard, amountPosition, i + 1, counterRecursion + 1);
//            }
//            possibleNotKnownCard.remove(possibleNotKnownCard.size() - 1);
//        }
//        return dest;
//    }

    public int[] getTableChances(Distribution currentDistribution) { // This method is not testing at this version
        int[] results = new int[Combinations.values().length];
        ArrayList<Card[]> possibleCards = smartSample(currentDistribution.getCurrentDeck(),
                Game.AMOUNT_CARDS_AT_DISTRIBUTION - Calculator.currentKnownCards(currentDistribution).length);
        ArrayList<Card[]> possibleCombinations = createPossibleCombinations(possibleCards,
                Calculator.currentKnownCards(currentDistribution));
        for (Card[] cards : possibleCombinations) {
            results[searchMaxCombination(new ArrayList<>(Arrays.asList(cards)))] += 1; //TODO try operation increment
        }
        return results;
    }

    protected ArrayList<Card[]> createPossibleCombinations(ArrayList<Card[]> possibleCards, Card[] knownCards) {
        ArrayList<Card[]> possibleCombinations = new ArrayList<>(possibleCards.size());
        for (Card[] cards : possibleCards) {
            Card[] possibleCombination = new Card[Game.AMOUNT_CARDS_AT_DISTRIBUTION];
            System.arraycopy(cards, 0, possibleCombination, 0, cards.length);
            System.arraycopy(knownCards, 0, possibleCombination, cards.length, knownCards.length);
            possibleCombinations.add(possibleCombination);
        }
        return possibleCombinations;
    }

    protected ArrayList<Card[]> smartSample(ArrayList<Card> src, int amountPosition) {
        ArrayList<Card[]> dest = new ArrayList<>(counterSmartSample(src.size(), amountPosition));
        dest = subSmartSample(src, dest, null, amountPosition, 0, 0);
        return dest;
    }

    protected ArrayList<Card[]> subSmartSample(ArrayList<Card> src, ArrayList<Card[]> dest, Card[] possibleNotKnownCard,
                                            int amountPosition, int startIndex, int counterRecursion) {
        if (possibleNotKnownCard == null) {
            possibleNotKnownCard = new Card[amountPosition];
        }
        for (int i = startIndex; i < src.size(); i++) {
            possibleNotKnownCard[counterRecursion] = src.get(i);
            if (counterRecursion == amountPosition - 1) {
                dest.add(possibleNotKnownCard.clone());
            } else {
                dest = subSmartSample(src, dest, possibleNotKnownCard, amountPosition, i + 1, counterRecursion + 1);
            }
        }
        return dest;
    }

    protected int counterSmartSample(int srcSize, int amountPosition) {
        int result = 1;
        for (int i = 0; i < amountPosition; i++) {
            result *= (srcSize - i) / (amountPosition - i);
        }
        return result;
    }

    protected int searchMaxCombination(ArrayList<Card> possibleCombination) {
        int indexCurrentCombination, indexMaxCombination = 0;
        for (Card[] cards : smartSample(possibleCombination, Game.AMOUNT_CARDS_AT_ANY_COMBINATION)) {
            indexCurrentCombination = whatCombination(cards);
            if (indexCurrentCombination > indexMaxCombination) {
                indexMaxCombination = indexCurrentCombination;
            }
        }
        return indexMaxCombination;
    }

    protected int whatCombination(Card[] possibleCombination) {
        if (isRoyalFlush(possibleCombination)) {
            return Combinations.ROYAL_FLUSH.ordinal();
        } else if (isStraightFlush(possibleCombination)) {
            return Combinations.STRAIGHT_FLUSH.ordinal();
        } else if (isFourOFAKind(possibleCombination)) {
            return Combinations.FOUR_OF_A_KIND.ordinal();
        } else if (isFullHouse(possibleCombination)) {
            return Combinations.FULL_HOUSE.ordinal();
        } else if (isFlush(possibleCombination)) {
            return Combinations.FLUSH.ordinal();
        } else if (isStraight(possibleCombination)) {
            return Combinations.STRAIGHT.ordinal();
        }else if (isThreeOfAKind(possibleCombination)) {
            return Combinations.THREE_OF_KIND.ordinal();
        } else if (isTwoPair(possibleCombination)) {
            return Combinations.TWO_PAIR.ordinal();
        }else if (isPair(possibleCombination)) {
            return Combinations.PAIR.ordinal();
        } else {
            return Combinations.KICKER.ordinal();
        }
    }

    protected boolean isRoyalFlush(Card[] possibleCombination) {
        int[] numericValues = getAllSortedNumericValues(possibleCombination);
        return (isStraightFlush(possibleCombination) && numericValues[0] == Game.MAX_NUMERIC_VALUE_CARD);
    }

    protected boolean isStraightFlush(Card[] possibleCombination) {
        return (isFlush(possibleCombination) && isStraight(possibleCombination));
    }

    protected boolean isFourOFAKind(Card[] possibleCombination) {
        int[] numericValues = getAllSortedNumericValues(possibleCombination);
        int counter = 0;
        for (int i = 0; i < possibleCombination.length - 1; i++) {
            if (numericValues[i] == numericValues[i + 1]) {
                counter++;
            } else {
                counter = 0;
            }
            if (counter == 3) {
                return true;
            }
        }
        return false;
    }

    protected boolean isFullHouse(Card[] possibleCombination) {
        int[] numericValues = getAllSortedNumericValues(possibleCombination);
        int counterForThreeOfKind = 0;
        int numericValueOfThreeOfAKind = 0;
        boolean isThreeOfAKind = false;
        boolean isPair = false;
        for (int i = 0; i < possibleCombination.length - 1; i++) {
            if (numericValues[i] == numericValues[i + 1]) {
                counterForThreeOfKind++;
            } else {
                counterForThreeOfKind = 0;
            }
            if (counterForThreeOfKind == 2) {
                isThreeOfAKind = true;
                numericValueOfThreeOfAKind = numericValues[i];
            }
        }
        for (int i = 0; i < numericValues.length - 1; i++) {
            if (numericValues[i] != numericValueOfThreeOfAKind) {
                if (numericValues[i] == numericValues[i + 1]) {
                    isPair = true;
                }
            }
        }
        return (isThreeOfAKind && isPair);
    }

    protected boolean isFlush(Card[] possibleCombination) {
        String[] suits = getAllSortedSuits(possibleCombination);
        int counter = 0;
        for (int i = 0; i < possibleCombination.length - 1; i++) {
            if (suits[i].equals(suits[i + 1])) {
                counter++;
            }
        }
        return (counter == 4);
    }

    protected boolean isStraight(Card[] possibleCombination) {
        int[] numericValues = getAllSortedNumericValues(possibleCombination);
        int counter = 0;
        for (int i = 0; i < possibleCombination.length - 1; i++) {
            if (numericValues[i] + 1 == numericValues[i + 1]) {
                counter++;
            }
        }
        return (counter == 4);
    }

    protected boolean isThreeOfAKind(Card[] possibleCombination) {
        int[] numericValues = getAllSortedNumericValues(possibleCombination);
        int counter = 0;
        for (int i = 0; i < possibleCombination.length - 1; i++) {
            if (numericValues[i] == numericValues[i + 1]) {
                counter++;
            } else {
                counter = 0;
            }
            if (counter == 2) {
                return true;
            }
        }
        return false;
    }

    protected boolean isTwoPair(Card[] possibleCombination) {
        int[] numericValues = getAllSortedNumericValues(possibleCombination);
        int counter = 0;
        for (int i = 0; i < possibleCombination.length - 1; i++) {
            if (numericValues[i] == numericValues[i + 1]) {
                counter++;
                i++;
            }
        }
        return (counter == 2);
    }

    protected boolean isPair(Card[] possibleCombination) {
        int[] numericValues = getAllSortedNumericValues(possibleCombination);
        for (int i = 0; i < numericValues.length - 1; i++) {
            if (numericValues[i] == numericValues[i + 1]) {
                return true;
            }
        }
        return false;
    }

    protected int[] getAllSortedNumericValues(Card[] possibleCombination) {
        int[] numericValues = new int[possibleCombination.length];
        for (int i = 0; i < possibleCombination.length; i++) {
            numericValues[i] = possibleCombination[i].getDignity();
        }
        Arrays.sort(numericValues);
        return numericValues;
    }

    protected String[] getAllSortedSuits(Card[] possibleCombination) {
        String[] suits = new String[possibleCombination.length];
        for (int i = 0; i < possibleCombination.length; i++) {
            suits[i] = possibleCombination[i].getSuit().toString();
        }
        Arrays.sort(suits);
        return suits;
    }

    protected static Card[] currentKnownCards(Distribution currentDistribution) {
        ArrayList<Card> deckOfCards = new ArrayList<>(Arrays.asList(Game.DECK_OF_CARDS));
        deckOfCards.removeAll(currentDistribution.getCurrentDeck());
        return deckOfCards.toArray(new Card[deckOfCards.size()]);
    }

}