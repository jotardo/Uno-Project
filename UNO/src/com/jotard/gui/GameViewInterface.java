package com.jotard.gui;

import java.util.List;

import com.jotard.structure.card.Card;
import com.jotard.structure.player.PlayerManager;

public interface GameViewInterface {
	
	public void updateLastPlayedCard(Card c);
	public void updatePlayerUI(List<PlayerManager> pmList);
	public void requestPlayCard(int cardIndex);
	public void requestShowWildPrompt();
	public void requestShowWildDraw4Prompt();
	public void drawPlayers(List<PlayerManager> playersList);
	public void requestDrawCard();
	
}
