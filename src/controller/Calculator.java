package controller;

import models.Card;
import models.Distribution;
import models.Enums.Combinations;

import java.util.ArrayList;
import java.util.Arrays;

public class Calculator {

    private Distribution distribution;

    private Card[] currentKnownCards;

    private Card[] currentDeckOfCards; // = new Card[PokerBot.COUNT_CARDS_IN_DECK - PokerBot.COUNT_CARDS_AT_PLAYER];

    private ArrayList<Card[]> possibleBoard;

    public void setDistribution(Distribution distribution) {
        this.distribution = distribution;
    }

    public void setCurrentKnownCards(Card[] currentKnownCards) {
        this.currentKnownCards = currentKnownCards;
    }

    public ArrayList<Card[]> getPossibleBoard() {
        return possibleBoard;
    }

    public Card[] getCurrentDeckOfCards() {
        return currentDeckOfCards;
    }

    public float[] getTableChances() {
        float[] chances = new float[PokerBot.COUNT_CARDS_AT_DISTRIBUTION];
        updateCurrentCards();

        return chances;
    }

    public void createPossibleBoard() {
        this.possibleBoard = new ArrayList<>(2118760); // TODO
        for (int a = 0; a < this.currentDeckOfCards.length; a++) {
            for (int b = a + 1; b < this.currentDeckOfCards.length; b++) {
                for (int c = b + 1; c < this.currentDeckOfCards.length; c++) {
                    for (int d = c + 1; d < this.currentDeckOfCards.length; d++) {
                        for (int e = d + 1; e < this.currentDeckOfCards.length; e++) {
                            this.possibleBoard.add(new Card[]{this.currentDeckOfCards[a],
                                    this.currentDeckOfCards[b],
                                    this.currentDeckOfCards[c],
                                    this.currentDeckOfCards[d],
                                    this.currentDeckOfCards[e]});
                        }
                    }
                }
            }
        }
    }

    public void createCurrentDeckOfCards() {
        ArrayList<Card> newCurrentDeckOfCards = new ArrayList<>(Arrays.asList(PokerBot.DECK_OF_CARDS));
        for (Card currentKnownCard : this.currentKnownCards) {
            if (currentKnownCard == null) {
                continue;
            }
            for (int i = 0; i < newCurrentDeckOfCards.size(); i++) {
                if (currentKnownCard.getNumericValue() == newCurrentDeckOfCards.get(i).getNumericValue() &&
                        currentKnownCard.getSuit() == newCurrentDeckOfCards.get(i).getSuit()) {
                    newCurrentDeckOfCards.remove(i);
                }
            }
        }
        this.currentDeckOfCards = newCurrentDeckOfCards.toArray(new Card[newCurrentDeckOfCards.size()]);
    }

    private void updateCurrentCards() {
        this.currentKnownCards = new Card[PokerBot.COUNT_CARDS_AT_DISTRIBUTION];      //TODO
        if (this.distribution.getCardsOfPlayer() != null) {
            System.arraycopy(this.distribution.getCardsOfPlayer(), 0,
                            this.currentKnownCards, countNotNullObjects(this.currentKnownCards),
                            PokerBot.COUNT_CARDS_AT_PLAYER);
        }
        if (this.distribution.getFlopCards() != null) {
            System.arraycopy(this.distribution.getFlopCards(), 0,
                            this.currentKnownCards, countNotNullObjects(this.currentKnownCards),
                            PokerBot.COUNT_CARDS_AT_FLOP);
        }
        if (this.distribution.getTurnCard() != null) {
            this.currentKnownCards[countNotNullObjects(this.currentKnownCards)] = this.distribution.getTurnCard();
        }
        if (this.distribution.getRiverCard() != null) {
            this.currentKnownCards[countNotNullObjects(this.currentKnownCards)] = this.distribution.getRiverCard();
        }
    }

    private int isCombination(Card[] possibleCombination) {
        if (isRoyalFlush(possibleCombination)) {
            return (new ArrayList<>(Arrays.asList(Combinations.values()))).indexOf(Combinations.RoyalFlush);
        } else if (isStraightFlush(possibleCombination)) {
            return (new ArrayList<>(Arrays.asList(Combinations.values()))).indexOf(Combinations.StraightFlush);
        } else if (isFourOFAKind(possibleCombination)) {
            return (new ArrayList<>(Arrays.asList(Combinations.values()))).indexOf(Combinations.FourOFAKind);
        } else if (isFullHose(possibleCombination)) {
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
        } else if (isKicker(possibleCombination)) {
            return (new ArrayList<>(Arrays.asList(Combinations.values()))).indexOf(Combinations.Kicker);
        }
        return Combinations.values().length + 1; // TODO return Exception
    }

    private boolean isRoyalFlush(Card[] possibleCombination) {
        return false;
    }

    private boolean isStraightFlush(Card[] possibleCombination) {
        return false;
    }

    private boolean isFourOFAKind(Card[] possibleCombination) {
        return false;
    }

    private boolean isFullHose(Card[] possibleCombination) {
        return false;
    }

    private boolean isFlush(Card[] PossibleCombination) {
        return false;
    }

    private boolean isStraight(Card[] possibleCombination) {
        return false;
    }

    private boolean isThreeOfAKind(Card[] possibleCombination) {
        return false;
    }

    private boolean isTwoPair(Card[] possibleCombination) {
        return false;
    }

    private boolean isPair(Card[] possibleCombination) {
        return false;
    }

    private boolean isKicker(Card[] possibleCombination) {
        return false;
    }

    private int countNotNullObjects(Object[] array) {
        int count = 0;
        if (array == null) {
            return count;
        }
        for (Object element : array) {
            if (element == null) {
                break;
            }
            count++;
        }
        return count;
    }

}