package com.jotard.gui;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jotard.gui.card.CardButtonFactory;
import com.jotard.image.ImageManager;
import com.jotard.structure.card.Card;
import com.jotard.structure.player.PlayerManager;

public class GameBoard extends JFrame {

	private CardButtonFactory cbf;
	
	private static final long serialVersionUID = 1L;
	private JLabel lastPlayedCardGraphic;
	
	public GameBoard() {
		setLayout(new BorderLayout());
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUpCenter();
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		cbf = new CardButtonFactory();
	}

	private void setUpCenter() {
		JPanel p = new JPanel();
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
				// TODO Auto-generated method stub
				super.paintComponent(g);
				g.drawImage(i, 0, 0, getWidth(), getHeight(), null);
			}
			
		};
	}

	public void drawLastPlayedCard(Card lastPlayedCard) {
		lastPlayedCardGraphic.setIcon(new ImageIcon(cbf.createCard(lastPlayedCard).getImage()));
	}

	public void drawPlayers(List<PlayerManager> playersList) {
		
	}

}
