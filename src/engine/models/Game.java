package engine.models;

import engine.models.Enums.HighCard;
import engine.models.Enums.Suits;

import java.util.*;

public class Game {

    public static final int MAX_NUMERIC_VALUE_CARD = 10;

    public static final int MIN_NUMERIC_VALUE_CARD = 2;

    public static final int AMOUNT_CARDS_AT_PLAYER = 2;

    public static final int AMOUNT_CARDS_AT_FLOP = 3;

    public static final int AMOUNT_CARDS_AT_TURN = 1;

    public static final int AMOUNT_CARDS_AT_RIVER = 1;

    public static final int AMOUNT_CARDS_AT_ANY_COMBINATION = 5;

    public static final int AMOUNT_POSSIBLE_COMBINATIONS = 10;

    public static final int AMOUNT_CARDS_IN_DECK = 52;

    public static final int START_AMOUNT_TABLES = 3;

    public static final int AMOUNT_CARDS_AT_DISTRIBUTION =
            AMOUNT_CARDS_AT_PLAYER +
            AMOUNT_CARDS_AT_FLOP +
            AMOUNT_CARDS_AT_TURN +
            AMOUNT_CARDS_AT_RIVER;

    public static final Card[] DECK_OF_CARDS;

    static {
        DECK_OF_CARDS = new Card[AMOUNT_CARDS_IN_DECK];
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

    public static final Map<String, Integer> COMBINATIONS;
    static {
        Map<String, Integer> tempMap = new HashMap<>(AMOUNT_POSSIBLE_COMBINATIONS);
        tempMap.put("Kicker", 0);
        tempMap.put("Pair", 1);
        tempMap.put("TwoPair", 2);
        tempMap.put("ThreeOfAKind", 3);
        tempMap.put("Straight", 4);
        tempMap.put("Flush", 5);
        tempMap.put("FullHose", 6);
        tempMap.put("FourOFAKind", 7);
        tempMap.put("StraightFlush", 8);
        tempMap.put("RoyalFlush", 9);
        COMBINATIONS = Collections.unmodifiableMap(tempMap);
    }

    public static final ArrayList<Table> tables = new ArrayList<>(START_AMOUNT_TABLES); // TODO Maybe do this variable public and destroyed static methods which are down

//    public static ArrayList<Table> getTables() {
//        return tables;
//    }
//
//    public static void addTable(final Table table) {
//        tables.add(table);
//    }
//
//    public static void removeTable(final Table table) {
//        tables.remove(table);
//    }

}
