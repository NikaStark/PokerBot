package engine.models.Enums;

public enum Combinations {

    KICKER,
    PAIR,
    TWO_PAIR,
    THREE_OF_KIND,
    STRAIGHT,
    FLUSH,
    FULL_HOUSE,
    FOUR_OF_A_KIND,
    STRAIGHT_FLUSH,
    ROYAL_FLUSH;

    @Override
    public String toString() {
        switch (this) {
            case KICKER: return "KICKER";
            case PAIR: return "PAIR";
            case TWO_PAIR: return "TWO_PAIR";
            case THREE_OF_KIND: return "THREE_OF_KIND";
            case STRAIGHT: return "STRAIGHT";
            case FLUSH: return "FLUSH";
            case FULL_HOUSE: return "FULL_HOUSE";
            case FOUR_OF_A_KIND: return "FOUR_OF_A_KIND";
            case STRAIGHT_FLUSH: return "STRAIGHT_FLUSH";
            case ROYAL_FLUSH: return "ROYAL_FLUSH";
            default: throw new RuntimeException();
        }
    }

}
