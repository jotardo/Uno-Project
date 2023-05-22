package com.jotard.structure.game;

import java.util.List;
import java.util.Scanner;

import com.jotard.structure.card.Card;
import com.jotard.structure.player.PlayerManager;

public interface GameModel {
	
	//game
	public void startGame();
	public void notifyDeck();
	public void notifyLastCard();
	public void setLastPlayedCard(Card card);
	public PlayerManager getNextPlayer();
	public void reverseTurn();
	//metadata
	public List<PlayerManager> getPlayersList();
	public Card getLastPlayedCard();
	
}