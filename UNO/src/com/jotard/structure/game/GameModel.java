package com.jotard.structure.game;

import java.util.List;

import com.jotard.structure.card.Card;
import com.jotard.structure.player.PlayerManager;

public interface GameModel {

	// game
	public void setUpPlayer(int playerNum, GameModel gameModel);

	public void startGame();
	
	public boolean isPlaying();
	
	public void endGame(PlayerManager pm);

	public boolean isNormalOrder();

	public void setLastPlayedCard(Card card);

	public PlayerManager getCurrentPlayer();

	public PlayerManager getNextPlayer();

	public void reverseTurn();

	// metadata
	public List<PlayerManager> getPlayersList();

	public Card getLastPlayedCard();

	// ???
	public void takeCurrentPlayerTurn();

	public void promptCurrentPlayerAction(boolean hasDrawnFirst);

	public void endCurrentPlayerTurn();

	public void advanceToNextPlayer();

	public void getCurrentPlayerPlayCard(int cardIndex);

	public void getCurrentPlayerDrawCard();

}
