package com.jotard.gui.player_gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jotard.gui.game_gui.GameViewInterface;
import com.jotard.image.ImageManager;
import com.jotard.structure.card.Card;
import com.jotard.structure.deck.Deck;
import com.jotard.structure.player.PlayerManager;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class CPUPlayerUI extends PlayerUI{

	private static final long serialVersionUID = 1L;
	private String handSize;
	private GameViewInterface gameView;

	public CPUPlayerUI(PlayerManager playerManager, GameViewInterface gameView) {
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(new JLabel(playerManager.getPlayerName()));
		add(new JLabel(new ImageIcon(ImageManager.getInstance().getScaledImage("/image/Deck.png", 0.25d))));
		for (Component c: getComponents()) {
			((JComponent) c).setAlignmentX(CENTER_ALIGNMENT);
		}
		this.handSize = String.format("%02d", playerManager.getPlayerHand().size());
		this.gameView = gameView;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setColor(Color.black);
		g2d.setStroke(new BasicStroke(5));
		g2d.drawOval(getWidth() - 35, getHeight() - 35, 35, 35);
		g2d.setColor(Color.white);
		g2d.fillOval(getWidth() - 35, getHeight() - 35, 35, 35);
		g2d.setColor(Color.black);
		g2d.setFont(g.getFont().deriveFont(25f));
		g2d.drawString(this.handSize + "", getWidth() - 27, getHeight() - 11);
		g2d.dispose();
	}

	@Override
	public void updateDisplay(PlayerManager playerManager) {
		if (playerManager.isTakingTurn())
			setBorder(BorderFactory.createLineBorder(Color.RED));
		else
			setBorder(BorderFactory.createEmptyBorder());
		this.handSize = String.format("%02d", playerManager.getPlayerHand().size());
		repaint();
	}

}
