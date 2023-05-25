package com.jotard.structure.game;

import java.util.List;
import java.util.Scanner;

import com.jotard.controller.GameModelAdapter;
import com.jotard.structure.card.Card;
import com.jotard.structure.player.PlayerManager;

public interface GameModel {
	
	//game
	public void setUpPlayer(int playerNum, GameModel gameModel);
	public void startGame();
	public void setLastPlayedCard(Card card);
	public PlayerManager getCurrentPlayer();
	public PlayerManager getNextPlayer();
	public void reverseTurn();
	//metadata
	public List<PlayerManager> getPlayersList();
	public Card getLastPlayedCard();
	//???
	public void takeCurrentPlayerTurn();
	public void promptCurrentPlayerAction();
	public void endCurrentPlayerTurn();
	public void advanceToNextPlayer();
	public void humanPlayCard(int cardIndex);
	public void humanDrawCard();
	
}
