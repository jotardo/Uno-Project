package com.jotard.structure.player;

import java.util.ArrayList;
import java.util.List;

import com.jotard.structure.card.Card;
import com.jotard.structure.deck.Deck;
import com.jotard.structure.game.GameManager;

public class CPUPlayer implements PlayerManager {

	private String name;
	private List<Card> cardHand;
	private Deck deck;
	private Card lastCardPlayed;
	private GameManager gameManager;

	private boolean isBanned;
	private int startDraw;

	public CPUPlayer(String name, GameManager gm) {
		this.name = name;
		this.cardHand = new ArrayList<>();
		this.isBanned = false;
		this.gameManager = gm;
		this.startDraw = 0;
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
		if (this.startDraw > 0) {
			drawCard(startDraw);
			this.startDraw = 0;
		}
		if (this.isBanned) {
			System.out.println(name + "'s banned!");
			this.isBanned = false;
			return;
		}
		System.out.println("Last played card : " + lastCardPlayed);
		System.out.println(name + "'s hand = " + cardHand);
		promptingAction(false);
		System.out.println(name + "'s turn ended.");
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
	
	private void playCard(Card c) {
		c.play(this.gameManager);
		removeCardFromHand(c);
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
	public boolean isCPU() {
		return true;
	}

	@Override
	public List<Card> getPlayerHand() {
		return this.cardHand;
	}

}
