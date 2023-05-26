package com.jotard.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import com.jotard.gui.game_gui.GameView;
import com.jotard.structure.card.Card;
import com.jotard.structure.card.CardFactory;
import com.jotard.structure.game.Game;
import com.jotard.structure.game.GameModel;

public class GameController implements Runnable {
	
	private GameModelAdapter gameModelAdapter;
	private GameViewAdapter gameViewAdapter;
	private Timer t;
	
	public GameController() {
		t = new Timer(1500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (gameModelAdapter == null)
					t.stop();
				else if (!gameModelAdapter.isPlaying())
					t.stop();
				else if (gameModelAdapter.getPlayersList().get(0).isTakingTurn()) {
					return;
				}
				else if (gameModelAdapter.getCurrentPlayer().isTakingTurn()) {
					gameModelAdapter.promptCurrentPlayerAction(false);
				}
				else {
					gameModelAdapter.takeCurrentPlayerTurn();
				}
			}
		});
		t.setRepeats(true);
		t.setInitialDelay(500);
	}

	public void doPlayCard(int cardIndex) {
		gameModelAdapter.getCurrentPlayerPlayCard(cardIndex);
	}

	public void doDrawCard() {
		gameModelAdapter.getCurrentPlayerDrawCard();
	}
	
	public void doActivateWild(String color, int oldCardIndex) {
		Card c = CardFactory.getInstance().createColoredWildCard(color);
		gameModelAdapter.getCurrentPlayer().removeCardFromHand(gameModelAdapter.getCurrentPlayer().getPlayerHand().get(oldCardIndex));
		c.play(gameModelAdapter);
		System.out.println("You:: played " + c);
		if (gameModelAdapter.getCurrentPlayer().getPlayerHand().isEmpty())
			gameModelAdapter.endGame(gameModelAdapter.getCurrentPlayer());
		else
			doEndTurn();
	}
	
	public void doActivateWildDraw4(String color, int oldCardIndex) {
		Card c = CardFactory.getInstance().createColoredWildDraw4Card(color);
		gameModelAdapter.getCurrentPlayer().removeCardFromHand(gameModelAdapter.getCurrentPlayer().getPlayerHand().get(oldCardIndex));
		c.play(gameModelAdapter);
		System.out.println("You:: played " + c);
		if (gameModelAdapter.getCurrentPlayer().getPlayerHand().isEmpty())
			gameModelAdapter.endGame(gameModelAdapter.getCurrentPlayer());
		else
			doEndTurn();
	}

	public void doEndTurn() {
		gameModelAdapter.endCurrentPlayerTurn();
	}

	public void doPause() {
		t.stop();
	}

	public void doResume() {
		t.restart();
	}

	public void doRestart() {
		this.run();
	}

	@Override
	public void run() {
		GameModel model = new Game();
		gameModelAdapter = new GameModelAdapter(model);
		GameView board = new GameView(this);
		gameViewAdapter = new GameViewAdapter(board);
		
		gameModelAdapter.addViewUpdater(gameViewAdapter);
		gameModelAdapter.setUpPlayer(4, gameModelAdapter);
		gameViewAdapter.drawPlayers(gameModelAdapter.getPlayersList());
		gameModelAdapter.startGame();
		this.doResume();
	}

	public void doDestroyView() {
		gameModelAdapter.removeViewUpdate(gameViewAdapter);
		gameViewAdapter.destroyView();
		gameModelAdapter = null;
		gameViewAdapter = null;
	}
	
}
