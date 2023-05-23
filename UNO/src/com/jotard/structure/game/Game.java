package com.jotard.structure.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.jotard.structure.card.Card;
import com.jotard.structure.deck.Deck;
import com.jotard.structure.player.CPUPlayer;
import com.jotard.structure.player.HumanPlayer;
import com.jotard.structure.player.PlayerManager;

public class Game implements GameModel{
		
	private Deck deck;
	private Card lastPlayedCard;
	private boolean isPlaying;
	private List<PlayerManager> playerList;
	private boolean normalOrder;
	private int currentPlayerIndex;

	public Game(int numPlayers) {
		super();
		this.deck = new Deck(this);
		this.isPlaying = false;
		this.normalOrder = true;
		this.playerList = new ArrayList<>();
		this.addPlayer(new HumanPlayer("You", this));
		for (int i = 1; i < numPlayers; i++)
			this.addPlayer(new CPUPlayer("Player " + (i+1), this));
	}
	
	public void addPlayer(PlayerManager player) {
		this.playerList.add(player);
	}
	
	public void removePlayer(PlayerManager player) {
		this.playerList.remove(player);
	}

	public void startGame() {
		isPlaying = true;
		deck.generateDeck();
		currentPlayerIndex = 0;
		for (int i = 0; i < 7; i++)
			for (int j = 0; j < playerList.size(); j++)
				deck.drawCard(this.playerList.get(j));
		deck.pickFirstCard(this);
//		updateGame();
	}
	
	public void updateGame() {
		getCurrentPlayer().takeTurn();
		while (getCurrentPlayer().isTakingTurn());
		if (!getCurrentPlayer().hasWon()) {
			advanceToNextPlayer();
			updateGame();
		}
		else {
			isPlaying = false;
		}
	}

	public PlayerManager getCurrentPlayer() {
		return this.playerList.get(this.currentPlayerIndex);
	}

	private void advanceToNextPlayer() {
		this.currentPlayerIndex += this.normalOrder ? 1 : -1;
		if (this.currentPlayerIndex < 0)
			this.currentPlayerIndex = this.playerList.size() - 1;
		if (this.currentPlayerIndex > this.playerList.size() - 1)
			this.currentPlayerIndex = 0;
	}

	public void setLastPlayedCard(Card card) {
		this.lastPlayedCard = card;
		this.notifyLastPlayedCard();
	}

	public void notifyDeck() {
		for (PlayerManager pm: this.playerList)
			pm.notifyDeck(this.deck);
	}
	
	public void notifyLastPlayedCard() {
		for (PlayerManager pm: this.playerList)
			pm.notifyLastPlayedCard(this.lastPlayedCard);
	}
	
	public PlayerManager getNextPlayer() {
		int index = this.currentPlayerIndex;
		index += this.normalOrder ? 1 : -1;
		if (index < 0)
			index = this.playerList.size() - 1;
		if (index > this.playerList.size() - 1)
			index = 0;
		return this.playerList.get(index);
	}
	
	@Override
	public void reverseTurn() {
		this.normalOrder = !this.normalOrder;
	}

	@Override
	public List<PlayerManager> getPlayersList() {
		return this.playerList;
	}

	@Override
	public Card getLastPlayedCard() {
		return this.lastPlayedCard;
	}
	
	@Override
	public void playCard(int playerIndex, int cardIndex) {
		getPlayersList().get(playerIndex).playCard(cardIndex);
	}

	@Override
	public void takeTurn(int playerIndex) {
		getPlayersList().get(playerIndex).takeTurn();
	}
	
}
