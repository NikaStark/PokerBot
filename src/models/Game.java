package models;

import models.Enums.HighCard;
import models.Enums.Suits;

public class Game {

    public static final int MAX_NUMERIC_VALUE_CARD = 10;

    public static final int MIN_NUMERIC_VALUE_CARD = 2;

    public static final int AMOUNT_CARDS_AT_PLAYER = 2;

    public static final int AMOUNT_CARDS_AT_FLOP = 3;

    public static final int AMOUNT_CARDS_AT_DISTRIBUTION = 7;

    public static final int AMOUNT_CARDS_IN_DECK = 52;

    public static final Card[] DECK_OF_CARDS = new Card[AMOUNT_CARDS_IN_DECK];

    static {
        int index = 0;
        for (int i = MIN_NUMERIC_VALUE_CARD; i <= MAX_NUMERIC_VALUE_CARD; i++) {
            for (Suits suit : Suits.values()) {
                DECK_OF_CARDS[index++] = new Card(i, suit);
            }
        }
        for (HighCard highCard : HighCard.values()) {
            for (Suits suit : Suits.values()) {
                DECK_OF_CARDS[index++] = new Card(highCard, suit);
            }
        }
    }

}
