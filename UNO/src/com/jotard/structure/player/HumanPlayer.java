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
		System.out.println(name + ":: draw one card");
		deck.drawCard(this);
	}

	@Override
	public void drawCard(int number) {
		System.out.println(name + ":: draw " + number + " card");
		deck.drawCard(this, number);
	}

	@Override
	public void takeTurn() {
		this.isTakingTurn = true;
		if (this.startDraw > 0) {
			drawCard(startDraw);
			this.startDraw = 0;
		}
		if (this.isBanned) {
			System.out.println(name + ":: currently banned!");
			this.isBanned = false;
			this.gameManager.endCurrentPlayerTurn();
		}
	}

	public void promptingAction(boolean hasDrawnFirstCard) {}

	private void promptDraw(boolean hasDrawnFirstCard) {
		if (hasDrawnFirstCard) {
			System.out.println("Error: you have drawn a card, play a card or end your turn");
			promptingAction(true);
			return;
		} else {
			drawCard();
			hasDrawnFirstCard = true;
			System.out.println(name + "'s hand = " + cardHand);
			promptingAction(true);
			return;
		}
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
				|| card.getNumber() == this.lastCardPlayed.getNumber()) {
			System.out.println(name + ":: played " + this.cardHand.get(cardIndex));
			card.play(this.gameManager);
			removeCardFromHand(card);
		}
		else {
			System.out.println("BRUH REPLAY");
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
