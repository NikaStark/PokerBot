package engine.models;

import engine.models.Enums.HighCard;
import engine.models.Enums.Suits;
import engine.models.exceptions.IllegalSuitException;

public class Card {

    private final int dignity;
    private final Suits suit;

    public Card(final int dignity, final Suits suit) {
        this.dignity = dignity;
        this.suit = suit;
    }

    public Card(final int dignity, final char suit)  throws IllegalSuitException {
        if (suit == 's' || suit == 'S'){
            this.suit = Suits.SPADES;
        } else if (suit == 'h' || suit == 'H'){
            this.suit = Suits.HERTZ;
        } else if (suit == 'c' || suit == 'C'){
            this.suit = Suits.CLUBS;
        } else if (suit == 'd' || suit == 'D'){
            this.suit = Suits.DIAMONDS;
        } else {
            throw new IllegalSuitException();
        }
        this.dignity = dignity;
    }

    public Card(final HighCard symbolicValue, final Suits suit) {
        if (symbolicValue == HighCard.Jack){
            this.dignity = Game.MAX_NUMERIC_VALUE_CARD + 1;
        } else if (symbolicValue == HighCard.Queen){
            this.dignity = Game.MAX_NUMERIC_VALUE_CARD + 2;
        } else if (symbolicValue == HighCard.King){
            this.dignity = Game.MAX_NUMERIC_VALUE_CARD + 3;
        } else {
            this.dignity = Game.MAX_NUMERIC_VALUE_CARD + 4;
        }
        this.suit = suit;
    }

    public int getDignity() {
        return dignity;
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
            if (this.dignity == card.dignity && this.suit == card.suit) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return this.dignity + this.suit.toString();
    }

}
