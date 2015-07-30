package models;

import controller.PokerBot;
import models.Enums.HighCard;
import models.Enums.Suits;

public class Card {

    private final int numericValue;

    private final Suits suit;

    public Card(final int numericValue, final Suits suit) {
        this.numericValue = numericValue;
        this.suit = suit;
    }

    public Card(final HighCard symbolicValue, final Suits suit) {
        if (symbolicValue == HighCard.Jack){
            this.numericValue = PokerBot.MAX_NUMERIC_VALUE_CARD + 1;
        } else if (symbolicValue == HighCard.Queen){
            this.numericValue = PokerBot.MAX_NUMERIC_VALUE_CARD + 2;
        } else if (symbolicValue == HighCard.King){
            this.numericValue = PokerBot.MAX_NUMERIC_VALUE_CARD + 3;
        } else {
            this.numericValue = PokerBot.MAX_NUMERIC_VALUE_CARD + 4;
        }
        this.suit = suit;
    }

    public int getNumericValue() {
        return numericValue;
    }

    public Suits getSuit() {
        return suit;
    }
}
