package com.jotard.structure.game;

import java.util.List;
import java.util.Scanner;

import com.jotard.structure.card.Card;
import com.jotard.structure.player.PlayerManager;

public interface GameModel {
	
	//game
	public void startGame();
	public void setLastPlayedCard(Card card);
	public PlayerManager getCurrentPlayer();
	public PlayerManager getNextPlayer();
	public void reverseTurn();
	//metadata
	public List<PlayerManager> getPlayersList();
	public Card getLastPlayedCard();
	//publisher
	public void addPlayer(PlayerManager s);
	public void removePlayer(PlayerManager s);
	public void notifyDeck();
	public void notifyLastPlayedCard();
	//???
	public void takeTurn(int playerIndex);
	public void playCard(int playerIndex, int cardIndex);
	
}
