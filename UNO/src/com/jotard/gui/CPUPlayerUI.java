package com.jotard.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jotard.gui.card.CardButton;
import com.jotard.gui.card.CardButtonFactory;
import com.jotard.gui.card.WildCardButton;
import com.jotard.image.ImageManager;
import com.jotard.structure.card.Card;
import com.jotard.structure.deck.Deck;
import com.jotard.structure.player.PlayerManager;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class CPUPlayerUI extends JPanel{

	private static final long serialVersionUID = 1L;
	private String handSize;

	public CPUPlayerUI(PlayerManager playerManager) {
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(new JLabel(playerManager.getPlayerName()));
		add(new JLabel(new ImageIcon(ImageManager.getInstance().getScaledImage("/image/Deck.png", 0.25d))));
		for (Component c: getComponents()) {
			((JComponent) c).setAlignmentX(CENTER_ALIGNMENT);
		}
		this.handSize = String.format("%02d", playerManager.getPlayerHand().size()) ;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.black);
		g.drawOval(getWidth() - 35, getHeight() - 35, 35, 35);
		g.setColor(Color.white);
		g.fillOval(getWidth() - 35, getHeight() - 35, 35, 35);
		g.setColor(Color.black);
		g.setFont(g.getFont().deriveFont(18f));
		g.drawString(this.handSize + "", getWidth() - 27, getHeight() - 11);
	}

}