package models;

import models.exceptions.ReassigningFieldException;

public class Distribution {

    private final Card[] CardsOfPlayer;

    private Card[] flopCards;

    private Card turnCard;

    private Card riverCard;

    public Distribution(Card[] cardsOfPlayer) {
        this.CardsOfPlayer = cardsOfPlayer;
    }

    public Card[] getCardsOfPlayer() {
        return CardsOfPlayer;
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

    public void setFlopCards(final Card[] flopCards) throws ReassigningFieldException {
        if (this.flopCards != null) {
            throw new ReassigningFieldException();
        }
        this.flopCards = flopCards;
    }

    public void setTurnCard(final Card turnCard) throws ReassigningFieldException{
        if (this.turnCard != null) {
            throw new ReassigningFieldException();
        }
        this.turnCard = turnCard;
    }

    public void setRiverCard(final Card riverCard) throws ReassigningFieldException{
        if (this.riverCard != null) {
            throw new ReassigningFieldException();
        }
        this.riverCard = riverCard;
    }
}
