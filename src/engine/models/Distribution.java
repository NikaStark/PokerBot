package engine.models;

import com.sun.jna.platform.win32.WinDef;
import engine.models.exceptions.ReassigningFieldException;
import outer.Outer;

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

    public Distribution(String numberOfDistribution) {
        this.cardsOfPlayer = new Card[Game.AMOUNT_CARDS_AT_PLAYER];
        this.flopCards = new Card[Game.AMOUNT_CARDS_AT_FLOP];
        this.currentDeck = new ArrayList<>(Arrays.asList(Game.DECK_OF_CARDS));
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

    public void addPlayersCard(Card newCard, int index) throws ReassigningFieldException {
        if (this.cardsOfPlayer[index] == null) {
            this.cardsOfPlayer[index] = newCard;
            removeKnownCardFromDeck(newCard);
        } else {
            throw new ReassigningFieldException();
        }
    }

    public void addFlopCard(Card newCard, int index) throws ReassigningFieldException {
        if (this.flopCards[index] == null) {
            this.flopCards[index] = newCard;
            removeKnownCardFromDeck(newCard);
        } else {
            throw new ReassigningFieldException();
        }
    }

    public void setTurnCard(final Card turnCard) throws ReassigningFieldException {
        if (this.turnCard == null) {
            this.turnCard = turnCard;
            this.rate = 0;
            removeKnownCardFromDeck(turnCard);
        } else {
            throw new ReassigningFieldException();
        }
    }

    public void setRiverCard(final Card riverCard) throws ReassigningFieldException {
        if (this.riverCard == null) {
            this.riverCard = riverCard;
            this.rate = 0;
            removeKnownCardFromDeck(riverCard);
        } else {
            throw new ReassigningFieldException();
        }
    }

    public void calculateBank(WinDef.HWND hwnd) {
        this.bank = Outer.getCurrentBank(hwnd);
    }

    public void calculateMyStack() {
        this.myStack = !this.currentPossibleSteps.containsKey(PossibleSteps.RAISE) ?
                this.currentPossibleSteps.get(PossibleSteps.CALL)[0] :
                this.currentPossibleSteps.get(PossibleSteps.RAISE).length != 1 ?
                        this.currentPossibleSteps.get(PossibleSteps.RAISE)[1] :
                        this.currentPossibleSteps.get(PossibleSteps.RAISE)[0] -
                                this.currentPossibleSteps.get(PossibleSteps.CALL)[0] * 2 - this.rate ;
    }

    public void setRate() {
        // TODO Write implementing when package "DSS" been finish
    }

    private void removeKnownCardFromDeck(Card knownCard) {
        for (Card notKnownCard : this.currentDeck) {
            if (knownCard.equals(notKnownCard)) {
                this.currentDeck.remove(notKnownCard);
                break;
            }
        }
        this.currentDeck.trimToSize();
    }

}
