package com.jotard.controller;

import java.util.List;

import com.jotard.gui.game_gui.GameViewInterface;
import com.jotard.structure.card.Card;
import com.jotard.structure.player.PlayerManager;

public class GameViewAdapter implements GameViewInterface, GameViewUpdater {
	
	private GameViewInterface view;
	
	public GameViewAdapter(GameViewInterface view) {
		this.view = view;
	}

	@Override
	public void receiveViewUpdate(List<PlayerManager> pList, Card lastPlayedCard, boolean normalOrder) {
		this.updateLastPlayedCard(lastPlayedCard);
		this.updatePlayerUI(pList);
		this.updateTurnOrder(normalOrder);
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
	public void requestShowWildPrompt(int index) {
		this.view.requestShowWildPrompt(index);
	}

	@Override
	public void requestShowWildDraw4Prompt(int index) {
		this.view.requestShowWildDraw4Prompt(index);
	}

	@Override
	public void requestDrawCard() {
		this.view.requestDrawCard();
	}

	@Override
	public void updateTurnOrder(boolean normalTurn) {
		this.view.updateTurnOrder(normalTurn);
	}

	@Override
	public void requestEndTurn() {
		this.view.requestEndTurn();
	}

	@Override
	public void drawEndGame(PlayerManager pm) {
		this.view.drawEndGame(pm);
	}

	@Override
	public void receiveEndGameUpdate(PlayerManager pm) {
		this.drawEndGame(pm);
	}
	
	@Override
	public void destroyView() {
		this.view.destroyView();
	}

	@Override
	public void receiveNotifyError(String message) {
		this.popupError(message);
	}

	@Override
	public void popupError(String message) {
		this.view.popupError(message);
	}
	
	@Override
	public void receiveNotifyStatus(String message) {
		this.updateStatus(message);
	}
	
	@Override
	public void updateStatus(String message) {
		this.view.updateStatus(message);
	}

}
