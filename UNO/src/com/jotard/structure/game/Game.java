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
	
	public void startGame() {
		currentPlayerIndex = 0;
		deck.generateDeck();
		for (int i = 0; i < 7; i++)
			for (int j = 0; j < playerList.size(); j++)
				deck.drawCard(this.playerList.get(j));
		isPlaying = true;
		deck.pickFirstCard(this);
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
		notifyLastCard();
	}

	public void addPlayer(PlayerManager s) {
		this.playerList.add(s);
	}

	public void removePlayer(PlayerManager s) {
		this.playerList.remove(s);
	}

	@Override
	public void notifyDeck() {
		for (PlayerManager pm:playerList)
			pm.notifyDeck(this.deck);
	}
	
	@Override
	public void notifyLastCard() {
		for (PlayerManager pm:playerList)
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
	
}
