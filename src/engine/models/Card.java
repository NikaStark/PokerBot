package engine.models;

import engine.models.Enums.Dignities;
import engine.models.Enums.Suits;

public class Card {

    private final Dignities dignity;
    private final Suits suit;

    public Card(final Dignities dignity, final Suits suit) {
        this.dignity = dignity;
        this.suit = suit;
    }

    public Dignities getDignity() {
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
        return this.dignity + " " + this.suit.toString();
    }

}
