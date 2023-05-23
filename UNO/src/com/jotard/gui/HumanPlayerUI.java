package com.jotard.gui;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.jotard.controller.GameController;
import com.jotard.gui.card.CardButton;
import com.jotard.gui.card.CardButtonFactory;
import com.jotard.gui.card.WildCardButton;
import com.jotard.structure.card.Card;
import com.jotard.structure.player.PlayerManager;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

public class HumanPlayerUI extends PlayerUI implements ActionListener{

	private static final long serialVersionUID = 1L;
	private int playerIndex;
	private JPanel handPanel;
	private JButton drawButton;
	private JButton endButton;
	private List<CardButton> handCardButton;
	private GameViewInterface gameView;

	public HumanPlayerUI(int playerIndex, PlayerManager playerManager, GameViewInterface gameView) {
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
		
		this.handCardButton = new ArrayList<>();
		this.gameView = gameView;
		this.playerIndex = playerIndex;
		this.drawHandPanel(playerManager.getPlayerHand());
		this.setPlayable(playerManager.isTakingTurn());
		if (playerManager.isTakingTurn())
		setBorder(BorderFactory.createEtchedBorder());
	}

	private void drawHandPanel(List<Card> playerHand) {
		for (Card card:playerHand) {
			CardButton cb = CardButtonFactory.getInstance().createCard(card);
			cb.addActionListener(this);
			handPanel.add(cb);
			this.handCardButton.add(cb);
		}
	}
	
	public void setPlayable(boolean isPlayable) {
		for (CardButton c: handCardButton) {
			c.setEnabled(isPlayable);
		}
		drawButton.setEnabled(isPlayable);
		endButton.setEnabled(isPlayable);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int index = this.handCardButton.indexOf(e.getSource());
		if (index >= 0) {
			System.out.println("Founded card index: " + index);
			gameView.playCard(playerIndex, index);
		}
	}

	@Override
	protected void updateDisplay(PlayerManager playerManager) {
		handPanel.removeAll();
		this.handCardButton.clear();
		drawHandPanel(playerManager.getPlayerHand());
		setPlayable(playerManager.isTakingTurn());
		revalidate();
		repaint();
	}
}
