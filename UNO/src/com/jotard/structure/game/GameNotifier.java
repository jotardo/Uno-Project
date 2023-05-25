package com.jotard.structure.game;

import com.jotard.structure.player.PlayerManager;

public interface GameNotifier {
	//publisher
	public void addPlayer(PlayerManager s);
	public void removePlayer(PlayerManager s);
	public void notifyDeck();
	public void notifyLastPlayedCard();
}
