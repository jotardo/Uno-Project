package com.jotard.structure.player;

import java.util.ArrayList;
import java.util.List;

import com.jotard.structure.card.Card;
import com.jotard.structure.deck.Deck;
import com.jotard.structure.game.GameModel;

public class HumanPlayer implements PlayerManager {

	private String name;
	private List<Card> cardHand;
	private Deck deck;
	private Card lastCardPlayed;
	private GameModel gameManager;

	private boolean isBanned;
	private boolean isTakingTurn;
	private int startDraw;
	private String status;

	public HumanPlayer(String name, GameModel gm) {
		this.name = name;
		this.cardHand = new ArrayList<>();
		this.isBanned = false;
		this.gameManager = gm;
		this.startDraw = 0;
		this.isTakingTurn = false;
	}

	@Override
	public void setBanned(boolean state) {
		this.isBanned = state;

	}

	@Override
	public void setDrawWhenTurnStart(int number) {
		this.startDraw = number;
	}

	@Override
	public void drawCard() {
		this.status += " draw a card!";
		this.deck.drawCard(this);
	}

	@Override
	public void drawCard(int number) {
		this.status += " draw "+ number + " card!";
		this.deck.drawCard(this, number);
	}

	@Override
	public void takeTurn() {
		boolean hasDrawn = this.startDraw > 0;
		this.isTakingTurn = true;
		this.status = this.name;
		if (this.startDraw > 0) {
			this.drawCard(startDraw);
			this.startDraw = 0;
		}
		if (this.isBanned) {
			if (hasDrawn)
				this.status += " and";
			this.status += " is currently banned!";
			this.gameManager.notifyStatus(this.status);
			this.isBanned = false;
			this.gameManager.endCurrentPlayerTurn();
		}
	}

	public void promptingAction(boolean hasDrawnFirstCard) {
	}

	@Override
	public void addCardToHand(Card pop) {
		this.cardHand.add(pop);
	}

	@Override
	public void removeCardFromHand(Card pop) {
		this.cardHand.remove(pop);
	}

	public void playCard(int cardIndex) {
		Card card = this.cardHand.get(cardIndex);
		if (card.getColor() == null || card.getColor().equals(this.lastCardPlayed.getColor())
				|| (card.getNumber() != -1 && card.getNumber() == this.lastCardPlayed.getNumber())) {
			gameManager.notifyStatus(name + " played " + this.cardHand.get(cardIndex));
			card.play(this.gameManager);
			removeCardFromHand(card);
			if (this.cardHand.isEmpty())
				this.gameManager.endGame(this);
		}
		else {
			this.gameManager.notifyError("The card you played (" + this.cardHand.get(cardIndex) + ") is invalid");
		}
	}

	@Override
	public boolean hasWon() {
		return this.cardHand.isEmpty();
	}

	@Override
	public void notifyDeck(Deck deck) {
		this.deck = deck;
	}

	@Override
	public void notifyLastPlayedCard(Card previousCard) {
		this.lastCardPlayed = previousCard;
	}

	@Override
	public String toString() {
		return name;
	}

	public String getPlayerName() {
		return this.name;
	}

	@Override
	public List<Card> getPlayerHand() {
		return this.cardHand;
	}

	@Override
	public void endTurn() {
		this.isTakingTurn = false;
	}

	@Override
	public boolean isTakingTurn() {
		return isTakingTurn;
	}

}
