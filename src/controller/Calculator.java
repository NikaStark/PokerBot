package controller;

import models.Card;
import models.Distribution;

import java.util.ArrayList;
import java.util.Arrays;

public class Calculator {

    private Distribution distribution;

    private Card[] currentKnownCards;

    private Card[] currentDeckOfCards; // = new Card[PokerBot.COUNT_CARDS_IN_DECK - PokerBot.COUNT_CARDS_AT_PLAYER];

    private ArrayList<Card[]> currentPossibleCombination;

//    public void setDistribution(Distribution distribution) {
//        this.distribution = distribution;
//    }
//
//    public void setCurrentKnownCards(Card[] currentKnownCards) {
//        this.currentKnownCards = currentKnownCards;
//    }
//
//    public ArrayList<Card[]> getCurrentPossibleCombination() {
//        return currentPossibleCombination;
//    }
//
//    public Card[] getCurrentDeckOfCards() {
//        return currentDeckOfCards;
//    }
//
//    public float[] getTableChances() {
//        float[] chances = new float[PokerBot.COUNT_CARDS_AT_DISTRIBUTION];
//        updateCurrentCards();
//
//        return chances;
//    }

    public void createCurrentPossibleCombination() {
        this.currentPossibleCombination = new ArrayList<>(2118760); // TODO
        for (int a = 0; a < this.currentDeckOfCards.length; a++) {
            for (int b = a + 1; b < this.currentDeckOfCards.length; b++) {
                for (int c = b + 1; c < this.currentDeckOfCards.length; c++) {
                    for (int d = c + 1; d < this.currentDeckOfCards.length; d++) {
                        for (int e = d + 1; e < this.currentDeckOfCards.length; e++) {
                            this.currentPossibleCombination.add(new Card[]{this.currentDeckOfCards[a],
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