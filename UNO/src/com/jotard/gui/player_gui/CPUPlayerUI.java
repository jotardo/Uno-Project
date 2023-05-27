package com.jotard.gui.player_gui;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.jotard.gui.Main;
import com.jotard.image.ImageManager;
import com.jotard.structure.card.Card;
import com.jotard.structure.deck.Deck;
import com.jotard.structure.player.PlayerManager;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class CPUPlayerUI extends PlayerUI {

	private static final long serialVersionUID = 1L;
	private static final int SIZE = 35;
	private static final int LINE_WIDTH = 6;
	private String handSize;

	public CPUPlayerUI(PlayerManager playerManager) {
		super();
		this.handSize = String.format("%02d", playerManager.getPlayerHand().size());
		setOpaque(false);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(new JLabel(playerManager.getPlayerName()));
		add(new JLabel(new ImageIcon(ImageManager.getInstance().getScaledImage("/image/Deck.png", 0.25d))));
		for (Component c : getComponents()) {
			((JComponent) c).setAlignmentX(CENTER_ALIGNMENT);
		}
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setColor(Color.BLUE);
		g2d.setStroke(new BasicStroke(LINE_WIDTH));
		g2d.drawOval(getWidth() - SIZE - LINE_WIDTH / 2, getHeight() - SIZE - LINE_WIDTH / 2, 35, 35);
		g2d.setColor(Color.white);
		g2d.fillOval(getWidth() - SIZE - LINE_WIDTH / 2, getHeight() - SIZE - LINE_WIDTH / 2, 35, 35);
		g2d.setColor(Color.black);
		g2d.setFont(g.getFont().deriveFont(23f));
		if (this.handSize.equals("00")) {
			g2d.drawString(this.handSize, getWidth() - 36, getHeight() - 12);
		}
		else {
			g2d.drawString(this.handSize, getWidth() - 35, getHeight() - 12);
		}
		g2d.dispose();
	}
	
	@Override
	public void updateDisplay(PlayerManager playerManager) {
		if (playerManager.isTakingTurn())
			setBorder(BorderFactory.createLineBorder(Color.RED, 5));
		else
			setBorder(BorderFactory.createEtchedBorder());
		this.handSize = String.format("%02d", playerManager.getPlayerHand().size());
		repaint();
	}

}
