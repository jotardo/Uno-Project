package com.jotard.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jotard.gui.card.CardButton;
import com.jotard.gui.card.CardButtonFactory;
import com.jotard.gui.card.WildCardButton;
import com.jotard.structure.card.Card;
import com.jotard.structure.player.PlayerManager;

import java.awt.Component;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

public class HumanPlayerUI extends JPanel{

	private static final long serialVersionUID = 1L;
	private JPanel handPanel;
	private JButton drawButton;
	private JButton endButton;

	public HumanPlayerUI(PlayerManager playerManager) {
		super();
		setLayout(new FlowLayout());
		JPanel p1, p2;
		add(p1 = new JPanel());
		p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
		p1.add(new JLabel(playerManager.getPlayerName()));
		p1.add(handPanel = new JPanel());
		add(p2 = new JPanel());
		p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));
		p2.add(drawButton = new JButton("Draw"));
		p2.add(Box.createGlue());
		p2.add(endButton = new JButton("End turn"));
		
		drawHandPanel(playerManager.getPlayerHand());
//		setPlayable(false);
	}

	private void drawHandPanel(List<Card> playerHand) {
		for (Card card:playerHand) {
			handPanel.add(CardButtonFactory.getInstance().createCard(card));
		}
	}
	
	public void setPlayable(boolean isPlayable) {
		for (Component c: handPanel.getComponents()) {
			c.setEnabled(isPlayable);
		}
		drawButton.setEnabled(isPlayable);
		endButton.setEnabled(isPlayable);
	}
}
