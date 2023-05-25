package com.jotard.gui.game_gui;

import java.util.List;

import com.jotard.structure.card.Card;
import com.jotard.structure.player.PlayerManager;

public interface GameViewInterface {
	
	public void updateLastPlayedCard(Card c);
	public void updatePlayerUI(List<PlayerManager> pmList);
	public void updateTurnOrder(boolean normalTurn);
	public void requestPlayCard(int cardIndex);
	public void requestShowWildPrompt(int index);
	public void requestShowWildDraw4Prompt(int index);
	public void drawPlayers(List<PlayerManager> playersList);
	public void requestDrawCard();
	public void requestEndTurn();
	
}
