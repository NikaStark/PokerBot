package controller;

import models.Card;
import models.Distribution;

import java.util.Arrays;

public class Calculator {

    private Distribution distribution;

    private Card[] currentKnownCards = new Card[PokerBot.AMOUNT_CARDS_AT_DISTRIBUTION];

    public void setDistribution(Distribution distribution) {
        this.distribution = distribution;
    }

    public float[] getTableChances() {
        float[] chances = new float[PokerBot.AMOUNT_CARDS_AT_DISTRIBUTION];
        updateCurrentCards();

        return chances;
    }

    private void updateCurrentCards() {
        Arrays.fill(this.currentKnownCards, null);
        if (this.distribution.getCardsOfPlayer() != null) {
            System.arraycopy(this.distribution.getCardsOfPlayer(), 0,
                             this.currentKnownCards, countNotNullObjects(this.currentKnownCards),
                             PokerBot.AMOUNT_CARDS_AT_PLAYER);
          }
        if (this.distribution.getFlopCards() != null) {
            System.arraycopy(this.distribution.getFlopCards(), 0,
                             this.currentKnownCards, countNotNullObjects(this.currentKnownCards),
                             PokerBot.AMOUNT_CARDS_AT_FLOP);
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
        for(Object element : array) {
            if (element == null) {
                break;
            }
            count++;
        }
        return count;
    }

}