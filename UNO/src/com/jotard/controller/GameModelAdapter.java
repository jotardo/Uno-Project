package com.jotard.controller;

import java.util.ArrayList;
import java.util.List;

import javax.naming.AuthenticationException;

import com.jotard.structure.card.Card;
import com.jotard.structure.game.GameModel;
import com.jotard.structure.player.PlayerManager;

public class GameModelAdapter implements GameModel, GameModelObserver {

	private List<GameViewUpdater> observees;
	private GameModel model;

	public GameModelAdapter(GameModel model) {
		this.model = model;
		this.observees = new ArrayList<>();
	}

	@Override
	public void addViewUpdater(GameViewUpdater o) {
		this.observees.add(o);
	}

	@Override
	public void removeViewUpdate(GameViewUpdater o) {
		this.observees.remove(o);
	}

	@Override
	public void requestUpdateView() {
		for (GameViewUpdater gmo : this.observees)
			gmo.receiveViewUpdate(this.getPlayersList(), this.getLastPlayedCard());
	}

	public void startGame() {
		this.model.startGame();
		this.requestUpdateView();
	}
	
	public void takeCurrentPlayerTurn() {
		this.model.takeCurrentPlayerTurn();
		this.requestUpdateView();
		this.promptCurrentPlayerAction();
	}

	public void endCurrentPlayerTurn() {
		this.model.endCurrentPlayerTurn();
		this.requestUpdateView();
		this.advanceToNextPlayer();
		this.takeCurrentPlayerTurn();
	}
	
	public void advanceToNextPlayer() {
		this.model.advanceToNextPlayer();
	}

	@Override
	public void setUpPlayer(int playerNum, GameModel gameModel) {
		this.model.setUpPlayer(playerNum, gameModel);
	}

	@Override
	public void setLastPlayedCard(Card card) {
		this.model.setLastPlayedCard(card);
	}

	@Override
	public PlayerManager getCurrentPlayer() {
		return this.model.getCurrentPlayer();
	}

	@Override
	public PlayerManager getNextPlayer() {
		return this.model.getNextPlayer();
	}

	@Override
	public void reverseTurn() {
		this.model.reverseTurn();
	}

	@Override
	public List<PlayerManager> getPlayersList() {
		return this.model.getPlayersList();
	}

	@Override
	public Card getLastPlayedCard() {
		return this.model.getLastPlayedCard();
	}

	@Override
	public void humanPlayCard(int cardIndex) {
		this.model.humanPlayCard(cardIndex);
		this.requestUpdateView();
	}

	@Override
	public void humanDrawCard() {
		this.model.humanDrawCard();
		this.requestUpdateView();
	}

	@Override
	public void promptCurrentPlayerAction() {
		this.model.promptCurrentPlayerAction();
		this.requestUpdateView();
	}

}
