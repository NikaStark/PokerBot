package engine.models.Enums;

import engine.models.exceptions.IllegalDignityException;

public enum Dignities {

    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(11),
    QUEEN(12),
    KING(13),
    ACE(14);

    private final int numericValue;

    Dignities(int numericValue) {
        this.numericValue = numericValue;
    }

    public int getNumericValue() {
        return this.numericValue;
    }

    public static Dignities create(int numericValue) throws IllegalDignityException {
        switch (numericValue) {
            case 2: return Dignities.TWO;
            case 3: return Dignities.THREE;
            case 4: return Dignities.FOUR;
            case 5: return Dignities.FIVE;
            case 6: return Dignities.SIX;
            case 7: return Dignities.SEVEN;
            case 8: return Dignities.EIGHT;
            case 9: return Dignities.NINE;
            case 10: return Dignities.TEN;
            case 11: return Dignities.JACK;
            case 12: return Dignities.QUEEN;
            case 13: return Dignities.KING;
            case 14: return Dignities.ACE;
            default: throw new IllegalDignityException();
        }
    }

    @Override
    public String toString() {
        if (this.numericValue <= 10) {
            return String.valueOf(this.numericValue);
        } else if (this.numericValue == 11) {
            return "J";
        } else if (this.numericValue == 12) {
            return "Q";
        } else if (this.numericValue == 13) {
            return "K";
        } else {
            return "A";
        }
    }

}
