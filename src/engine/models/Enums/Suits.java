package engine.models.Enums;

public enum Suits {

    Spades, Hertz, Clubs, Diamonds;

    @Override
    public String toString() {
        if (this == Spades) {
            return "Spades";
        } else if (this == Hertz) {
            return "Hertz";
        } else if (this == Clubs) {
            return "Clubs";
        } else {
            return "Diamonds";
        }
    }

}
