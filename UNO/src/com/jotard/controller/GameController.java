package com.jotard.controller;

import com.jotard.gui.GameView;
import com.jotard.structure.card.Card;
import com.jotard.structure.card.CardFactory;
import com.jotard.structure.game.Game;
import com.jotard.structure.game.GameModel;

public class GameController {
	
	private GameModelAdapter gameModelAdapter;
	private GameViewAdapter gameViewAdapter;
	
	public GameController() {
		GameModel model = new Game();
		gameModelAdapter = new GameModelAdapter(model);
		GameView board = new GameView(this);
		gameViewAdapter = new GameViewAdapter(board);
		gameModelAdapter.addViewUpdater(gameViewAdapter);
		board.setVisible(true);
		gameModelAdapter.setUpPlayer(6, gameModelAdapter);
		gameViewAdapter.drawPlayers(model.getPlayersList());
		gameModelAdapter.startGame();
	}

	public void doPlayCard(int cardIndex) {
		gameModelAdapter.humanPlayCard(cardIndex);
	}

	public void doDrawCard() {
		gameModelAdapter.humanDrawCard();
	}
	
	public void doActivateWild(String color) {
		Card c = CardFactory.getInstance().createColoredWildCard(color);
		c.play(gameModelAdapter);
		System.out.println("You:: played " + c);
		gameModelAdapter.endCurrentPlayerTurn();
	}
	
	public void doActivateWildDraw4(String color) {
		Card c = CardFactory.getInstance().createColoredWildDraw4Card(color);
		c.play(gameModelAdapter);
		System.out.println("You:: played " + c);
		gameModelAdapter.endCurrentPlayerTurn();
	}
	
}
