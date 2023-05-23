package com.jotard.gui;

import java.util.List;

import com.jotard.structure.card.Card;
import com.jotard.structure.player.PlayerManager;

public interface GameViewInterface {
	public void updateLastPlayedCard(Card c);
	public void updatePlayerUI(List<PlayerManager> pmList);
	public void playCard(int playerIndex, int index);
}
