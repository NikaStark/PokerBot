package controller;

import models.Card;
import models.Distribution;
import models.Enums.Combinations;
import models.Game;

import java.util.ArrayList;
import java.util.Arrays;

public class Calculator {

//    private Distribution distribution;
//
//    private ArrayList<Card> currentKnownCards;
//
//    private Card[] currentDeckOfCards;
//
//    private ArrayList<Card[]> possibleBoard = new ArrayList<>();

    public int[] getTableChances(Distribution currentDistribution) {
        return null;
    }

//    public ArrayList<Card[]> method(Card[] src, int amountEmptyPosition) {
//        ArrayList<Card[]> dest = new ArrayList<>();
//        subMethod(src, dest, null, 0, amountEmptyPosition);
//        return dest;
//    }
//
//    private void subMethod(Card[] src, ArrayList<Card[]> dest, ArrayList<Card> currentCards,
//                           int startIndex, int counterNeed) {
//        if (currentCards == null) {
//            currentCards = new ArrayList<>(counterNeed);
//        }
//        for (int i = startIndex; i < src.length; i++) {
//            currentCards.add(src[i]);
//            if (currentCards.size() == counterNeed) {
//                dest.add(currentCards.toArray(new Card[currentCards.size()]));
//                currentCards.remove(currentCards.size() - 1);
//            } else {
//                subMethod(src, dest, currentCards, i + 1, counterNeed);
//            }
//        }
//    }
//
//    public void cleverSortOut(Card[] src, ArrayList<Card[]> dest, Card[] currentCards,
//                               int startIndex, int startCounter, int counterNeed) {
//        if (startCounter == 1) {
//            currentCards = new Card[counterNeed];
//        }
//        for (int i = startIndex; i < src.length; i++) {
//            currentCards[startCounter - 1] = src[i];
//            if (startCounter == counterNeed) {
//                Card[] newCurrentCards = new Card[counterNeed];
//                System.arraycopy(currentCards, 0, newCurrentCards,0, counterNeed);
//                dest.add(newCurrentCards);
//            } else {
//                cleverSortOut(src, dest, currentCards, i + 1, startCounter + 1, counterNeed);
//            }
//        }
//    }
//
//    private void createPossibleBoard() {
//        this.possibleBoard = new ArrayList<>(2118760); // TODO
//        for (int a = 0; a < this.currentDeckOfCards.length; a++) {
//            for (int b = a + 1; b < this.currentDeckOfCards.length; b++) {
//                for (int c = b + 1; c < this.currentDeckOfCards.length; c++) {
//                    for (int d = c + 1; d < this.currentDeckOfCards.length; d++) {
//                        for (int e = d + 1; e < this.currentDeckOfCards.length; e++) {
//                            this.possibleBoard.add(new Card[]{this.currentDeckOfCards[a],
//                                                            this.currentDeckOfCards[b],
//                                                            this.currentDeckOfCards[c],
//                                                            this.currentDeckOfCards[d],
//                                                            this.currentDeckOfCards[e]});
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    public void createCurrentDeckOfCards() {
//        ArrayList<Card> newCurrentDeckOfCards = new ArrayList<>(Arrays.asList(Game.DECK_OF_CARDS));
//        for (Card currentKnownCard : this.currentKnownCards) {
//            if (currentKnownCard == null) {
//                continue;
//            }
//            for (int i = 0; i < newCurrentDeckOfCards.size(); i++) {
//                if (currentKnownCard.getNumericValue() == newCurrentDeckOfCards.get(i).getNumericValue() &&
//                        currentKnownCard.getSuit() == newCurrentDeckOfCards.get(i).getSuit()) {
//                    newCurrentDeckOfCards.remove(i);
//                }
//            }
//        }
//        this.currentDeckOfCards = newCurrentDeckOfCards.toArray(new Card[newCurrentDeckOfCards.size()]);
//    }
//
//    public void updateCurrentKnownCards() {
//        this.currentKnownCards = new ArrayList<>(Game.AMOUNT_CARDS_AT_PLAYER);
//        if (this.distribution.getCardsOfPlayer() != null) {
//            this.currentKnownCards.addAll(Arrays.asList(this.distribution.getCardsOfPlayer()));
//        }
//        if (this.distribution.getFlopCards() != null) {
//            this.currentKnownCards.ensureCapacity(Game.AMOUNT_CARDS_AT_FLOP);
//            this.currentKnownCards.addAll(Arrays.asList(this.distribution.getFlopCards()));
//        }
//        if (this.distribution.getTurnCard() != null) {
//            this.currentKnownCards.ensureCapacity(1);
//            this.currentKnownCards.add(this.distribution.getTurnCard());
//        }
//        if (this.distribution.getRiverCard() != null) {
//            this.currentKnownCards.ensureCapacity(1);
//            this.currentKnownCards.add(this.distribution.getRiverCard());
//        }
//    }
//
//    public int bestCombination(Card[] possibleBoard) {
//        ArrayList<Card[]> possibleCombination = new ArrayList<>(); //TODO initialize not in method
//        int indexOfBestCombination = 0;
//        for (int a = 0; a < possibleBoard.length; a++) {
//            for (int b = a + 1; b < possibleBoard.length; b++) {
//                for (int c = b + 1; c < possibleBoard.length; c++) {
//                    for (int d = c + 1; d < possibleBoard.length; d++) {
//                        for (int e = d + 1; e < possibleBoard.length; e++) {
//                            possibleCombination.add(new Card[]{possibleBoard[a],
//                                    possibleBoard[b],
//                                    possibleBoard[c],
//                                    possibleBoard[d],
//                                    possibleBoard[e]});
//                        }
//                    }
//                }
//            }
//        }
////        cleverSortOut(possibleBoard, possibleCombination, null, 0, 1, 5);
//        for (Card[] cards : possibleCombination) {
//            int currentIndex = whatCombination(cards);
//            if (currentIndex > indexOfBestCombination) {
//                indexOfBestCombination = currentIndex;
//            }
//        }
//        return indexOfBestCombination;
//    }

    public int whatCombination(Card[] possibleCombination) {
        if (isRoyalFlush(possibleCombination)) {
            return (new ArrayList<>(Arrays.asList(Combinations.values()))).indexOf(Combinations.RoyalFlush);
        } else if (isStraightFlush(possibleCombination)) {
            return (new ArrayList<>(Arrays.asList(Combinations.values()))).indexOf(Combinations.StraightFlush);
        } else if (isFourOFAKind(possibleCombination)) {
            return (new ArrayList<>(Arrays.asList(Combinations.values()))).indexOf(Combinations.FourOFAKind);
        } else if (isFullHouse(possibleCombination)) {
            return (new ArrayList<>(Arrays.asList(Combinations.values()))).indexOf(Combinations.FullHose);
        } else if (isFlush(possibleCombination)) {
            return (new ArrayList<>(Arrays.asList(Combinations.values()))).indexOf(Combinations.Flush);
        } else if (isStraight(possibleCombination)) {
            return (new ArrayList<>(Arrays.asList(Combinations.values()))).indexOf(Combinations.Straight);
        }else if (isThreeOfAKind(possibleCombination)) {
            return (new ArrayList<>(Arrays.asList(Combinations.values()))).indexOf(Combinations.ThreeOfAKind);
        } else if (isTwoPair(possibleCombination)) {
            return (new ArrayList<>(Arrays.asList(Combinations.values()))).indexOf(Combinations.TwoPair);
        }else if (isPair(possibleCombination)) {
            return (new ArrayList<>(Arrays.asList(Combinations.values()))).indexOf(Combinations.Pair);
        } else {
            return (new ArrayList<>(Arrays.asList(Combinations.values()))).indexOf(Combinations.Kicker);
        }
    }

    public boolean isRoyalFlush(Card[] possibleCombination) {
        int[] numericValues = getAllSortedNumericValues(possibleCombination);
        return (isStraightFlush(possibleCombination) && numericValues[0] == Game.MAX_NUMERIC_VALUE_CARD);
    }

    public boolean isStraightFlush(Card[] possibleCombination) {
        return (isFlush(possibleCombination) && isStraight(possibleCombination));
    }

    public boolean isFourOFAKind(Card[] possibleCombination) {
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

    public boolean isFullHouse(Card[] possibleCombination) {
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

    public boolean isFlush(Card[] possibleCombination) {
        String[] suits = getAllSortedSuits(possibleCombination);
        int counter = 0;
        for (int i = 0; i < possibleCombination.length - 1; i++) {
            if (suits[i].equals(suits[i + 1])) {
                counter++;
            }
        }
        return (counter == 4);
    }

    public boolean isStraight(Card[] possibleCombination) {
        int[] numericValues = getAllSortedNumericValues(possibleCombination);
        int counter = 0;
        for (int i = 0; i < possibleCombination.length - 1; i++) {
            if (numericValues[i] + 1 == numericValues[i + 1]) {
                counter++;
            }
        }
        return (counter == 4);
    }

    public boolean isThreeOfAKind(Card[] possibleCombination) {
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

    public boolean isTwoPair(Card[] possibleCombination) {
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

    public boolean isPair(Card[] possibleCombination) {
        int[] numericValues = getAllSortedNumericValues(possibleCombination);
        for (int i = 0; i < numericValues.length - 1; i++) {
            if (numericValues[i] == numericValues[i + 1]) {
                return true;
            }
        }
        return false;
    }

    public int[] getAllSortedNumericValues(Card[] possibleCombination) {
        int[] numericValues = new int[possibleCombination.length];
        for (int i = 0; i < possibleCombination.length; i++) {
            numericValues[i] = possibleCombination[i].getNumericValue();
        }
        Arrays.sort(numericValues);
        return numericValues;
    }

    public String[] getAllSortedSuits(Card[] possibleCombination) {
        String[] suits = new String[possibleCombination.length];
        for (int i = 0; i < possibleCombination.length; i++) {
            suits[i] = possibleCombination[i].getSuit().toString();
        }
        Arrays.sort(suits);
        return suits;
    }

}