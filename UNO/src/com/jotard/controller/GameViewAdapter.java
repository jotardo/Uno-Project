package com.jotard.controller;

import java.util.List;

import com.jotard.gui.GameViewInterface;
import com.jotard.structure.card.Card;
import com.jotard.structure.player.PlayerManager;

public class GameViewAdapter implements GameViewInterface, GameViewUpdater {
	
	private GameViewInterface view;
	
	public GameViewAdapter(GameViewInterface view) {
		this.view = view;
	}

	@Override
	public void receiveViewUpdate(List<PlayerManager> pList, Card lastPlayedCard) {
		this.updateLastPlayedCard(lastPlayedCard);
		this.updatePlayerUI(pList);
	}

	public void drawPlayers(List<PlayerManager> playersList) {
		this.view.drawPlayers(playersList);
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
	public void requestPlayCard(int cardIndex) {
		this.view.requestPlayCard(cardIndex);
	}

	@Override
	public void requestShowWildPrompt() {
		this.view.requestShowWildPrompt();
	}

	@Override
	public void requestShowWildDraw4Prompt() {
		this.view.requestShowWildDraw4Prompt();
	}

	@Override
	public void requestDrawCard() {
		this.view.requestDrawCard();
	}
	

}
