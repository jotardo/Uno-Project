package com.jotard.gui.player_gui;

import javax.swing.JLabel;

import com.jotard.image.ImageManager;
import com.jotard.structure.player.PlayerManager;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class CPUPlayerUI extends PlayerUI {

	private static final long serialVersionUID = 1L;
	private static final int SIZE = 35;
	private static final int LINE_WIDTH = 6;
	private String handSize;

	public CPUPlayerUI(PlayerManager playerManager) {
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(new JLabel(playerManager.getPlayerName()));
		add(new JLabel(new ImageIcon(ImageManager.getInstance().getScaledImage("/image/Deck.png", 0.25d))));
		for (Component c : getComponents()) {
			((JComponent) c).setAlignmentX(CENTER_ALIGNMENT);
		}
		this.handSize = String.format("%02d", playerManager.getPlayerHand().size());
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setColor(Color.black);
		g2d.setStroke(new BasicStroke(LINE_WIDTH));
		g2d.drawOval(getWidth() - SIZE - LINE_WIDTH / 2, getHeight() - SIZE - LINE_WIDTH / 2, SIZE, SIZE);
		g2d.setColor(Color.white);
		g2d.fillOval(getWidth() - SIZE - LINE_WIDTH / 2, getHeight() - SIZE - LINE_WIDTH / 2, SIZE, SIZE);
		g2d.setColor(Color.black);
		g2d.setFont(g.getFont().deriveFont(23f));
		g2d.drawString(this.handSize, getWidth() - 34, getHeight() - 12);
		if (this.handSize.equals("00")) {
			g2d.drawString(this.handSize, getWidth() - 36, getHeight() - 12);
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
