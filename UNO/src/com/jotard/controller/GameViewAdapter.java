package com.jotard.controller;

import java.util.List;

import com.jotard.gui.GameView;
import com.jotard.gui.GameViewInterface;
import com.jotard.structure.card.Card;
import com.jotard.structure.player.PlayerManager;

public class GameViewAdapter implements GameViewInterface, GameModelObservable {
	
	private GameViewInterface view;
	
	public GameViewAdapter(GameViewInterface view) {
		this.view = view;
	}

	@Override
	public void notifyPlayers(List<PlayerManager> pList, Card lastPlayedCard) {
		this.updateLastPlayedCard(lastPlayedCard);
		this.updatePlayerUI(pList);
	}

	@Override
	public void updateLastPlayedCard(Card c) {
		this.view.updateLastPlayedCard(c);
	}

	@Override
	public void updatePlayerUI(List<PlayerManager> pmList) {
		this.view.updatePlayerUI(pmList);
	}

	@Override
	public void playCard(int playerIndex, int index) {
		this.view.playCard(playerIndex, index);
	}
	
	

}
