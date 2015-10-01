package models;

import models.Enums.StreetPoker;
import models.exceptions.ReassigningFieldException;

import java.util.ArrayList;
import java.util.Arrays;

public class Distribution {

    private final Card[] cardsOfPlayer;

    private Card[] flopCards;

    private Card turnCard;

    private Card riverCard;

    private ArrayList<Card> currentDeck;

    public Distribution(Card[] cardsOfPlayer) {
        this.cardsOfPlayer = cardsOfPlayer;
        this.currentDeck = new ArrayList<>(Arrays.asList(Game.DECK_OF_CARDS));
        removeKnownCardsFromDeck(StreetPoker.PreFlop);
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

    public void setFlopCards(final Card[] flopCards) throws ReassigningFieldException {
        if (this.flopCards != null) {
            throw new ReassigningFieldException();
        }
        this.flopCards = flopCards;
        removeKnownCardsFromDeck(StreetPoker.Flop);
    }

    public void setTurnCard(final Card turnCard) throws ReassigningFieldException{
        if (this.turnCard != null) {
            throw new ReassigningFieldException();
        }
        this.turnCard = turnCard;
        removeKnownCardsFromDeck(StreetPoker.Turn);
    }

    public void setRiverCard(final Card riverCard) throws ReassigningFieldException{
        if (this.riverCard != null) {
            throw new ReassigningFieldException();
        }
        this.riverCard = riverCard;
        removeKnownCardsFromDeck(StreetPoker.River);
    }

    public void removeKnownCardsFromDeck(StreetPoker streetPoker) {
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

    public Card[] currentKnownCards(StreetPoker streetPoker) {
        if (streetPoker == StreetPoker.PreFlop) {
            return this.cardsOfPlayer;
        }
        if (streetPoker == StreetPoker.Flop) {
            return this.flopCards;
        }
        if (streetPoker == StreetPoker.Turn) {
            return new Card[]{this.getTurnCard()};
        }
        return new Card[]{this.getRiverCard()};
    }

    public Card[] allCurrentKnownCards(StreetPoker streetPoker) {
        Card[] allCurrentKnownCards = new Card[counterKnownCards(streetPoker)];
        if (streetPoker == StreetPoker.PreFlop) {
            System.arraycopy(this.cardsOfPlayer, 0, allCurrentKnownCards, 0, this.cardsOfPlayer.length);
            return allCurrentKnownCards;
        }
        if (streetPoker == StreetPoker.Flop) {
            System.arraycopy(this.cardsOfPlayer, 0, allCurrentKnownCards, 0, this.cardsOfPlayer.length);
            System.arraycopy(this.flopCards, 0, allCurrentKnownCards, this.cardsOfPlayer.length, this.flopCards.length);
            return allCurrentKnownCards;
        }
        if (streetPoker == StreetPoker.Turn) {
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
        if (streetPoker == StreetPoker.PreFlop) {
            return Game.AMOUNT_CARDS_AT_PLAYER;
        }
        if (streetPoker == StreetPoker.Flop) {
            return Game.AMOUNT_CARDS_AT_PLAYER +
                    Game.AMOUNT_CARDS_AT_FLOP;
        }
        if (streetPoker == StreetPoker.Turn) {
            return Game.AMOUNT_CARDS_AT_PLAYER +
                    Game.AMOUNT_CARDS_AT_FLOP +
                    Game.AMOUNT_CARDS_AT_TURN;
        }
        return Game.AMOUNT_CARDS_AT_DISTRIBUTION;
    }

}
