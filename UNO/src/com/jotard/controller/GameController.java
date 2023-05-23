package com.jotard.controller;

import java.awt.Font;
import java.io.IOException;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import com.jotard.gui.GameView;
import com.jotard.gui.Main;
import com.jotard.image.ImageManager;
import com.jotard.structure.game.Game;
import com.jotard.structure.game.GameModel;

public class GameController {
	
	private GameModelAdapter gameModelAdapter;
	private GameViewAdapter gameViewAdapter;
	
	public GameController() {
		GameModel model = new Game(4);
		GameView board = new GameView(this);
		gameModelAdapter = new GameModelAdapter(model);
		gameViewAdapter = new GameViewAdapter(board);
		gameModelAdapter.addObservable(gameViewAdapter);
		board.drawPlayers(model.getPlayersList());
		board.setVisible(true);
		gameModelAdapter.startGame();
	}

	public void playCard(int playerIndex, int cardIndex) {
		gameModelAdapter.playCard(playerIndex, cardIndex);
	}
	
}
