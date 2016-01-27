package engine.models;

import engine.models.exceptions.ReassigningFieldException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;

public class Distribution {

    public enum StreetPoker {

        PRE_FLOP, FLOP, TURN, RIVER

    }

    public enum PossibleSteps {

        FOLD, CHECK, CALL, RAISE

    }

    private final String numberOfDistribution;
    private int dealerPos;
    private final Card[] cardsOfPlayer;
    private Card[] flopCards;
    private Card turnCard;
    private Card riverCard;
    private ArrayList<Card> currentDeck;
    private int myStack;
    private int bank;
    private int rate;
    private EnumMap<PossibleSteps, Integer[]> currentPossibleSteps;

    public Distribution(Card[] cardsOfPlayer, String numberOfDistribution) {
        this.cardsOfPlayer = cardsOfPlayer;
        this.currentDeck = new ArrayList<>(Arrays.asList(Game.DECK_OF_CARDS));
        removeKnownCardsFromDeck(currentStreetPoker());
        this.numberOfDistribution = numberOfDistribution;
        this.rate = 0;
        this.currentPossibleSteps = new EnumMap<>(PossibleSteps.class);
    }

    public String getNumberOfDistribution() {
        return numberOfDistribution;
    }

    public int getDealerPos() {
        return dealerPos;
    }

    public Card[] getCardsOfPlayer() {
        return cardsOfPlayer;
    }

    public Card[] getFlopCards() {
        return flopCards;
    }

    public Card getTurnCard() {
        return turnCard;
    }

    public Card getRiverCard() {
        return riverCard;
    }

    public ArrayList<Card> getCurrentDeck() {
        return currentDeck;
    }

    public int getMyStack() {
        return myStack;
    }

    public int getBank() {
        return bank;
    }

    public int getRate() {
        return rate;
    }

    public EnumMap<PossibleSteps, Integer[]> getCurrentPossibleSteps() {
        return currentPossibleSteps;
    }

    public void setDealerPos(int dealerPos) {
        this.dealerPos = dealerPos;
    }

    public void setFlopCards(final Card[] flopCards) throws ReassigningFieldException {
        if (this.flopCards != null) {
            throw new ReassigningFieldException();
        }
        this.flopCards = flopCards;
        this.rate = 0;
        removeKnownCardsFromDeck(currentStreetPoker());
    }

    public void setTurnCard(final Card turnCard) throws ReassigningFieldException{
        if (this.turnCard != null) {
            throw new ReassigningFieldException();
        }
        this.turnCard = turnCard;
        this.rate = 0;
        removeKnownCardsFromDeck(currentStreetPoker());
    }

    public void setRiverCard(final Card riverCard) throws ReassigningFieldException{
        if (this.riverCard != null) {
            throw new ReassigningFieldException();
        }
        this.riverCard = riverCard;
        this.rate = 0;
        removeKnownCardsFromDeck(currentStreetPoker());
    }

    public void setBank(int bank) {
        this.bank = bank;
    }

    public void setRate(final int rate) {
        this.rate = rate;
    }

    public void rateMyStack() {
        this.myStack = this.currentPossibleSteps.containsKey(Distribution.PossibleSteps.RAISE) ?
                this.currentPossibleSteps.get(Distribution.PossibleSteps.RAISE)[2] :
                this.currentPossibleSteps.get(Distribution.PossibleSteps.CALL)[0];
    }

    public Card[] allCurrentKnownCards(StreetPoker streetPoker) {
        Card[] allCurrentKnownCards = new Card[counterKnownCards(streetPoker)];
        if (streetPoker == StreetPoker.PRE_FLOP) {
            System.arraycopy(this.cardsOfPlayer, 0, allCurrentKnownCards, 0, this.cardsOfPlayer.length);
            return allCurrentKnownCards;
        } else if (streetPoker == StreetPoker.FLOP) {
            System.arraycopy(this.cardsOfPlayer, 0, allCurrentKnownCards, 0, this.cardsOfPlayer.length);
            System.arraycopy(this.flopCards, 0, allCurrentKnownCards, this.cardsOfPlayer.length, this.flopCards.length);
            return allCurrentKnownCards;
        } else if (streetPoker == StreetPoker.TURN) {
            System.arraycopy(this.cardsOfPlayer, 0, allCurrentKnownCards, 0, this.cardsOfPlayer.length);
            System.arraycopy(this.flopCards, 0, allCurrentKnownCards, this.cardsOfPlayer.length, this.flopCards.length);
            allCurrentKnownCards[allCurrentKnownCards.length - 1] = this.turnCard;
            return allCurrentKnownCards;
        } else {
            System.arraycopy(this.cardsOfPlayer, 0, allCurrentKnownCards, 0, this.cardsOfPlayer.length);
            System.arraycopy(this.flopCards, 0, allCurrentKnownCards, this.cardsOfPlayer.length, this.flopCards.length);
            allCurrentKnownCards[allCurrentKnownCards.length - 2] = this.turnCard;
            allCurrentKnownCards[allCurrentKnownCards.length - 1] = this.riverCard;
            return allCurrentKnownCards;
        }
    }

    public int counterKnownCards(StreetPoker streetPoker) {
        if (streetPoker == StreetPoker.PRE_FLOP) {
            return Game.AMOUNT_CARDS_AT_PLAYER;
        } else if (streetPoker == StreetPoker.FLOP) {
            return Game.AMOUNT_CARDS_AT_PLAYER +
                    Game.AMOUNT_CARDS_AT_FLOP;
        } else if (streetPoker == StreetPoker.TURN) {
            return Game.AMOUNT_CARDS_AT_PLAYER +
                    Game.AMOUNT_CARDS_AT_FLOP +
                    Game.AMOUNT_CARDS_AT_TURN;
        }
        return Game.AMOUNT_CARDS_AT_DISTRIBUTION;
    }

    public StreetPoker currentStreetPoker() {
        if (this.flopCards == null && this.cardsOfPlayer != null) {
           return StreetPoker.PRE_FLOP;
        } else if (this.turnCard == null && this.flopCards != null) {
            return StreetPoker.FLOP;
        } else if (this.riverCard == null && this.turnCard != null) {
            return StreetPoker.TURN;
        } else {
            return StreetPoker.RIVER;
        }
    }

    private void removeKnownCardsFromDeck(StreetPoker streetPoker) {
        for (Card knownCard : currentKnownCards(streetPoker)) {
            for (Card notKnownCard : this.currentDeck) {
                if (knownCard.equals(notKnownCard)) {
                    this.currentDeck.remove(notKnownCard);
                    break;
                }
            }
        }
        this.currentDeck.trimToSize();
    }

    private Card[] currentKnownCards(StreetPoker streetPoker) {
        if (streetPoker == StreetPoker.PRE_FLOP) {
            return this.cardsOfPlayer;
        } else if (streetPoker == StreetPoker.FLOP) {
            return this.flopCards;
        } else if (streetPoker == StreetPoker.TURN) {
            return new Card[]{this.getTurnCard()};
        }
        return new Card[]{this.getRiverCard()};
    }

}
