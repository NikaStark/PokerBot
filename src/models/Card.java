package models;

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
            this.numericValue = Game.MAX_NUMERIC_VALUE_CARD + 1;
        } else if (symbolicValue == HighCard.Queen){
            this.numericValue = Game.MAX_NUMERIC_VALUE_CARD + 2;
        } else if (symbolicValue == HighCard.King){
            this.numericValue = Game.MAX_NUMERIC_VALUE_CARD + 3;
        } else {
            this.numericValue = Game.MAX_NUMERIC_VALUE_CARD + 4;
        }
        this.suit = suit;
    }

    public int getNumericValue() {
        return numericValue;
    }

    public Suits getSuit() {
        return suit;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (this.getClass() == object.getClass()) {
            Card card = (Card) object;
            if (this.numericValue == card.numericValue && this.suit == card.suit) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return this.numericValue + this.suit.toString();
    }

}
