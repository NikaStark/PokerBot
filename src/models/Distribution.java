package models;

import controller.PokerBot;

public class Distribution {

    private Card[] CardsOfPlayer = new Card[PokerBot.AMOUNT_CARDS_AT_PLAYER];

    private Card[] flopCards = new Card[PokerBot.AMOUNT_CARDS_AT_FLOP];

    private Card turnCard;

    private Card riverCard;

    public Distribution(Card[] cardsOfPlayer) {
        CardsOfPlayer = cardsOfPlayer;
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

    public void setFlopCards(Card[] flopCards) {
        this.flopCards = flopCards;
    }

    public void setTurnCard(Card turnCard) {
        this.turnCard = turnCard;
    }

    public void setRiverCard(Card riverCard) {
        this.riverCard = riverCard;
    }
}
