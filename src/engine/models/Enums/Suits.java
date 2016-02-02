package engine.models.Enums;

import engine.models.exceptions.IllegalSuitException;

public enum Suits {

    SPADES,
    HERTZ,
    CLUBS,
    DIAMONDS;

    public static Suits create(char suit) throws IllegalSuitException{
        switch (suit) {
            case 's':
            case 'S': return Suits.SPADES;
            case 'h':
            case 'H': return Suits.HERTZ;
            case 'c':
            case 'C': return Suits.CLUBS;
            case 'd':
            case 'D': return Suits.DIAMONDS;
            default: throw new IllegalSuitException();
        }
    }

    public static Suits create(String suit) throws IllegalSuitException{
        switch (suit.toLowerCase()) {
            case "spades": return Suits.SPADES;
            case "hertz": return Suits.HERTZ;
            case "clubs": return Suits.CLUBS;
            case "diamonds": return Suits.DIAMONDS;
            default: throw new IllegalSuitException();
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case SPADES: return "SPADES";
            case HERTZ: return "HERTZ";
            case CLUBS: return "CLUBS";
            case DIAMONDS: return "DIAMONDS";
            default: throw new RuntimeException();
        }
    }

}
