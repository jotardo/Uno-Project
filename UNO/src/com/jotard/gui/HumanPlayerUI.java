package com.jotard.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jotard.controller.GameController;
import com.jotard.gui.card.CardButton;
import com.jotard.gui.card.CardButtonFactory;
import com.jotard.structure.card.Card;
import com.jotard.structure.player.PlayerManager;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

public class HumanPlayerUI extends PlayerUI implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel handPanel;
	private JButton drawButton;
	private JButton endButton;
	private List<CardButton> handCardButton;
	private GameViewInterface gameView;

	public HumanPlayerUI(PlayerManager playerManager, GameViewInterface gameView) {
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
		drawButton.addActionListener(this);
		p2.add(Box.createGlue());
		p2.add(endButton = new JButton("End turn"));
		endButton.addActionListener(this);

		this.handCardButton = new ArrayList<>();
		this.gameView = gameView;
		this.drawHandPanel(playerManager.getPlayerHand());
		this.setPlayable(playerManager.isTakingTurn());
	}

	private void drawHandPanel(List<Card> playerHand) {
		for (Card card : playerHand) {
			CardButton cb = CardButtonFactory.getInstance().createCard(card);
			cb.addActionListener(this);
			handPanel.add(cb);
			this.handCardButton.add(cb);
		}
	}

	public void setPlayable(boolean isPlayable) {
		for (CardButton c : handCardButton) {
			c.setEnabled(isPlayable);
		}
		drawButton.setEnabled(isPlayable);
		endButton.setEnabled(isPlayable);
	}

	@Override
	protected void updateDisplay(PlayerManager playerManager) {
		if (playerManager.isTakingTurn())
			setBorder(BorderFactory.createLineBorder(Color.RED));
		else
			setBorder(BorderFactory.createEmptyBorder());
		handPanel.removeAll();
		this.handCardButton.clear();
		drawHandPanel(playerManager.getPlayerHand());
		setPlayable(playerManager.isTakingTurn());
		revalidate();
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == drawButton) {
			gameView.requestDrawCard();
		} else {
			int index = this.handCardButton.indexOf(e.getSource());
			if (index >= 0) {
				if (this.handCardButton.get(index).toString().startsWith("Wild +4")) {
					this.gameView.requestShowWildDraw4Prompt();
				} else if (this.handCardButton.get(index).toString().startsWith("Wild")) {
					this.gameView.requestShowWildPrompt();
				} else {
					this.gameView.requestPlayCard(index);
				}
			}
		}
	}
}
