package com.jotard.controller;

import java.util.List;

import com.jotard.structure.card.Card;
import com.jotard.structure.player.PlayerManager;

public interface GameModelAdapter {
	
	public List<PlayerManager> getPlayersList();
	public Card getLastPlayedCard();
	public void startGame();
	
}
