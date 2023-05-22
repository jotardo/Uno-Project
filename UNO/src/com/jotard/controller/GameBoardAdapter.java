package com.jotard.controller;

import java.util.List;

import com.jotard.gui.GameView;
import com.jotard.structure.card.Card;
import com.jotard.structure.game.GameModel;
import com.jotard.structure.player.PlayerManager;

public class GameBoardAdapter implements GameModelAdapter {
	
	private GameModel gameManager;

	public GameBoardAdapter(GameModel gm) {
		this.gameManager = gm;
	}

	@Override
	public List<PlayerManager> getPlayersList() {
		return this.gameManager.getPlayersList();
	}

	@Override
	public Card getLastPlayedCard() {
		return this.gameManager.getLastPlayedCard();
	}
	
	@Override
	public void startGame() {
		this.gameManager.startGame();
	}
	
}
