package com.jotard.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jotard.controller.GameModelAdapter;
import com.jotard.gui.card.CardButton;
import com.jotard.gui.card.CardButtonFactory;
import com.jotard.image.ImageManager;
import com.jotard.structure.card.Card;
import com.jotard.structure.player.PlayerManager;

public class GameView extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JLabel lastPlayedCardGraphic;
	private GameModelAdapter modelAdapter;
	private Box leftPanel, rightPanel, upPanel, downPanel;
	
	public GameView(GameModelAdapter modelAdapter) {
		setSize(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width, GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height);
		setResizable(false);
		setLayout(new BorderLayout());
		setUpCenter();
		add(leftPanel = new Box(BoxLayout.Y_AXIS), BorderLayout.WEST);
		leftPanel.setPreferredSize(new Dimension(130, getPreferredSize().height));
		add(rightPanel = new Box(BoxLayout.Y_AXIS), BorderLayout.EAST);
		rightPanel.setPreferredSize(new Dimension(130, getPreferredSize().height));
		add(upPanel = new Box(BoxLayout.X_AXIS), BorderLayout.NORTH);
		upPanel.setPreferredSize(new Dimension(getPreferredSize().width, 200));
		add(downPanel = new Box(BoxLayout.X_AXIS), BorderLayout.SOUTH);
		downPanel.setPreferredSize(new Dimension(getPreferredSize().width, 200));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		//have model work
		this.modelAdapter = modelAdapter;
		this.modelAdapter.startGame();
		this.drawLastPlayedCard(this.modelAdapter.getLastPlayedCard());
		this.drawPlayers(this.modelAdapter.getPlayersList());
	}

	private void setUpCenter() {
		JPanel p = new JPanel();
		p.setBorder(BorderFactory.createEtchedBorder());
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
		p.add(Box.createGlue());
		p.add(createDrawButton());
		p.add(Box.createGlue());
		p.add(lastPlayedCardGraphic = new JLabel());
		p.add(Box.createGlue());
		
		add(p);
	}
	
	private JButton createDrawButton() {
		return new JButton() {

			private static final long serialVersionUID = 1L;
			private Image i = ImageManager.getInstance().getScaledImage("/image/Deck.png", 0.35d);
			
			{
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				setPreferredSize(new Dimension(i.getWidth(null), i.getHeight(null)));
				setMaximumSize(new Dimension(i.getWidth(null), i.getHeight(null)));
			}
			
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(i, 0, 0, getWidth(), getHeight(), null);
			}
			
		};
	}

	private void drawLastPlayedCard(Card lastPlayedCard) {
		if (lastPlayedCard != null) {
			CardButton c = CardButtonFactory.getInstance().createCard(lastPlayedCard);
			Image i = ImageManager.getInstance().getScaledImage(c.getImageURL(), 0.35d);
			lastPlayedCardGraphic.setIcon(new ImageIcon(i));
		}
	}

	private void drawPlayers(List<PlayerManager> playersList) {
		downPanel.add(Box.createGlue());
		downPanel.add(new HumanPlayerUI(playersList.get(0)));
		downPanel.add(Box.createGlue());
		for (int i = 1; i < playersList.size(); i++) {
			if (i == 1) {
				rightPanel.add(Box.createGlue());
				rightPanel.add(new CPUPlayerUI(playersList.get(i)));
				rightPanel.add(Box.createGlue());
				upPanel.add(Box.createGlue());
			}
			else if (i == playersList.size() - 1) {
				leftPanel.add(Box.createGlue());
				leftPanel.add(new CPUPlayerUI(playersList.get(i)));
				leftPanel.add(Box.createGlue());
			}
			else {
				upPanel.add(new CPUPlayerUI(playersList.get(playersList.size() - i)));
				upPanel.add(Box.createGlue());
			}
		}
	}

}
