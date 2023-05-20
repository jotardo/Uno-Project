package com.jotard.controller;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import com.jotard.gui.GameBoard;
import com.jotard.gui.Main;
import com.jotard.image.ImageManager;
import com.jotard.structure.game.Game;
import com.jotard.structure.game.GameManager;

public class GameController implements IController, Runnable, Subscriber{
	private GameManager model;
	private GameBoard view;
	
	public GameController() throws IOException {
		updateGUI();
		loadAndCacheImages();
	}

	private void updateGUI() {
		Font f;
		try {
//			f = Font.createFont(Font.TRUETYPE_FONT, Main.class.getResourceAsStream("/font/Stroud-anB5.ttf"));
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
			UIManager.getLookAndFeelDefaults().forEach((k, v) -> {
				if (k.toString().matches("\\w+.font")) {
					UIManager.getLookAndFeelDefaults().put(k, UIManager.getLookAndFeelDefaults().getFont(k).deriveFont(26f));
				}
			});
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

	private void loadAndCacheImages() throws IOException {
		String[] color = { "Blue", "Green", "Red", "Yellow" };
		String[] number = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "Draw", "Reverse", "Skip", "Wild", "Wild_Draw" };
		ImageManager iManager = ImageManager.getInstance();
		iManager.loadAndCacheImage("/image/Wild.png");
		iManager.loadAndCacheImage("/image/Wild_Draw.png");
		iManager.loadAndCacheImage("/image/Deck.png");
		iManager.loadAndCacheImage("/image/Uno Logo.png");
		for (String c : color)
			for (String n : number)
				iManager.loadAndCacheImage("/image/" + c + "_" + n + ".png");
	}
	
	public static void main(String[] args) throws IOException {
		SwingUtilities.invokeLater(new GameController());
	}

	@Override
	public void run() {
		new Main(this).makeEvents();
	}

	@Override
	public void createGame() {
		this.model = new Game(4);
		this.model.addSubscriber(this);
		this.view = new GameBoard();
		this.view.drawLastPlayedCard(this.model.getLastPlayedCard());
		this.view.drawPlayers(this.model.getPlayersList());
	}

	@Override
	public void notifyFromModelPublisher() {
		
	}
	
}
