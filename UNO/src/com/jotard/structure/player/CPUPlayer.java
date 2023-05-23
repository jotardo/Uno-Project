package com.jotard.structure.player;

import java.util.ArrayList;
import java.util.List;

import com.jotard.structure.card.Card;
import com.jotard.structure.deck.Deck;
import com.jotard.structure.game.GameModel;

public class CPUPlayer implements PlayerManager {

	private String name;
	private List<Card> cardHand;
	private Deck deck;
	private Card lastCardPlayed;
	private GameModel gameManager;

	private boolean isBanned;
	private int startDraw;
	private boolean isTakingTurn;

	public CPUPlayer(String name, GameModel gm) {
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
		deck.drawCard(this);
	}

	@Override
	public void drawCard(int number) {
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
			System.out.println(name + "'s banned!");
			this.isBanned = false;
			endTurn();
		}
	}

	private void promptingAction(boolean hasDrawnFirstCard) {
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
		this.cardHand.get(cardIndex).play(this.gameManager);
		removeCardFromHand(this.cardHand.get(cardIndex));
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
	
	public int getPlayerHandSize() {
		return this.cardHand.size();
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
