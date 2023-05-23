package com.jotard.controller;

import java.util.ArrayList;
import java.util.List;

import com.jotard.structure.card.Card;
import com.jotard.structure.game.GameModel;
import com.jotard.structure.player.PlayerManager;

public class GameModelAdapter implements GameModel, GameModelObserver{

	private List<GameModelObservable> observees;
	private GameModel oldModel;
	
	public GameModelAdapter(GameModel model) {
		this.oldModel = model;
		this.observees = new ArrayList<>();
	}
	
	@Override
	public void addObservable(GameModelObservable o) {
		this.observees.add(o);
	}

	@Override
	public void removeObservable(GameModelObservable o) {
		this.observees.remove(o);
	}

	@Override
	public void notifyBoardDraw() {
		for (GameModelObservable gmo: this.observees)
			gmo.notifyPlayers(this.oldModel.getPlayersList(), this.oldModel.getLastPlayedCard());
	}

	@Override
	public void startGame() {
		this.oldModel.startGame();
		this.takeTurn(0);
	}

	@Override
	public void setLastPlayedCard(Card card) {
		this.oldModel.setLastPlayedCard(card);
		this.notifyBoardDraw();
	}

	@Override
	public PlayerManager getCurrentPlayer() {
		return this.oldModel.getCurrentPlayer();
	}

	@Override
	public PlayerManager getNextPlayer() {
		return this.oldModel.getNextPlayer();
	}

	@Override
	public void reverseTurn() {
		this.oldModel.reverseTurn();
		this.notifyBoardDraw();
	}

	@Override
	public List<PlayerManager> getPlayersList() {
		return this.oldModel.getPlayersList();
	}

	@Override
	public Card getLastPlayedCard() {
		return this.oldModel.getLastPlayedCard();
	}

	@Override
	public void addPlayer(PlayerManager s) {
		this.oldModel.addPlayer(s);
		this.notifyBoardDraw();
	}

	@Override
	public void removePlayer(PlayerManager s) {
		this.oldModel.removePlayer(s);
		this.notifyBoardDraw();
	}

	@Override
	public void notifyDeck() {
		this.oldModel.notifyDeck();
	}

	@Override
	public void notifyLastPlayedCard() {
		this.oldModel.notifyLastPlayedCard();
	}

	@Override
	public void takeTurn(int playerIndex) {
		this.oldModel.takeTurn(playerIndex);
		this.notifyBoardDraw();
	}

	@Override
	public void playCard(int playerIndex, int cardIndex) {
		this.oldModel.playCard(playerIndex, cardIndex);
		this.notifyBoardDraw();		
	}

}
