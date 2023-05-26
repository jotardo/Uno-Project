package com.jotard.structure.game;

import java.util.ArrayList;
import java.util.List;

import com.jotard.structure.card.Card;
import com.jotard.structure.deck.Deck;
import com.jotard.structure.player.CPUPlayer;
import com.jotard.structure.player.HumanPlayer;
import com.jotard.structure.player.PlayerManager;

public class Game implements GameModel, GameNotifier{
		
	private Deck deck;
	private Card lastPlayedCard;
	private boolean isPlaying;
	private List<PlayerManager> playerList;
	private boolean normalOrder;
	private int currentPlayerIndex;

	public Game() {
		super();
		this.deck = new Deck(this);
		this.isPlaying = false;
		this.normalOrder = true;
		this.playerList = new ArrayList<>();
	}
	
	@Override
	public void setUpPlayer(int numPlayers, GameModel gm) {
		while (!this.playerList.isEmpty())
			this.removePlayer(this.playerList.get(0));
		this.addPlayer(new HumanPlayer("You", gm));
		for (int i = 1; i < numPlayers; i++)
			this.addPlayer(new CPUPlayer("Player " + (i+1), gm));
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
	}

	public PlayerManager getCurrentPlayer() {
		return this.playerList.get(this.currentPlayerIndex);
	}

	public void advanceToNextPlayer() {
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
	
	public boolean isNormalOrder() {
		return this.normalOrder;
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
	public void getCurrentPlayerPlayCard(int cardIndex) {
		getCurrentPlayer().playCard(cardIndex);
	}

	@Override
	public void takeCurrentPlayerTurn() {
		getCurrentPlayer().takeTurn();
	}

	@Override
	public void endCurrentPlayerTurn() {
		getCurrentPlayer().endTurn();
	}

	@Override
	public void getCurrentPlayerDrawCard() {
		getCurrentPlayer().drawCard();
	}

	@Override
	public void promptCurrentPlayerAction(boolean hasDrawnFirst) {
		getCurrentPlayer().promptingAction(hasDrawnFirst);
	}
	
	public boolean isPlaying() {
		return isPlaying;
	}
	
	public void endGame(PlayerManager pm) {
		this.isPlaying = false;
	}

	@Override
	public void notifyError(String message) {
		System.err.println(message);
	}

	@Override
	public void notifyStatus(String message) {
		System.out.println(message);
	}
	
}
