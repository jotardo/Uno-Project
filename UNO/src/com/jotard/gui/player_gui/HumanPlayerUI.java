package com.jotard.gui.player_gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jotard.controller.GameController;
import com.jotard.gui.card_button.CardButton;
import com.jotard.gui.card_button.CardButtonFactory;
import com.jotard.gui.game_gui.GameViewInterface;
import com.jotard.structure.card.Card;
import com.jotard.structure.player.PlayerManager;

import java.awt.Color;
import java.awt.Dialog.ModalityType;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;

public class HumanPlayerUI extends PlayerUI implements ActionListener {

	public static final int MAX_HAND_SIZE = 10;
	private static final long serialVersionUID = 1L;
	private JPanel handPanel;
	private JButton drawButton;
	private JButton endButton;
	private JButton showFullHandButton;
	private List<CardButton> handCardButton;
	private List<CardButton> moreHandCardButton;
	private GameViewInterface gameView;
	private boolean hasDrawnOncePerTurn;

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
		p2.add(Box.createGlue());
		p2.add(drawButton = new JButton("Draw"));
		drawButton.addActionListener(this);
		p2.add(Box.createGlue());
		p2.add(endButton = new JButton("End turn"));
		endButton.addActionListener(this);
		p2.add(Box.createGlue());
		p2.add(showFullHandButton = new JButton("Show all cards"));
		showFullHandButton.addActionListener(this);
		p2.add(Box.createGlue());

		this.handCardButton = new ArrayList<>();
		this.moreHandCardButton = new ArrayList<>();
		this.gameView = gameView;
		this.hasDrawnOncePerTurn = false;
		this.drawHandPanel(playerManager.getPlayerHand());
		this.setPlayable(playerManager.isTakingTurn(), playerManager.getPlayerHand().size() > MAX_HAND_SIZE);
	}

	private void drawHandPanel(List<Card> playerHand) {
		Card card = null;
		CardButton cb = null;
		for (int i = 0; i < playerHand.size(); i++) {
			card = playerHand.get(i);
			cb = CardButtonFactory.getInstance().createCard(card);
			if (i < MAX_HAND_SIZE) {
				this.handPanel.add(cb);
				this.handCardButton.add(cb);
				cb.addActionListener(this);
			}
			else {
				this.moreHandCardButton.add(cb);
				this.handCardButton.add(cb);
			}
		}
	}

	private void setPlayable(boolean isPlayable, boolean tooMuchCard) {
		if (isPlayable)
			this.hasDrawnOncePerTurn = false;
		for (CardButton c : handCardButton) {
			c.setEnabled(isPlayable);
		}
		drawButton.setEnabled(isPlayable && !this.hasDrawnOncePerTurn);
		endButton.setEnabled(isPlayable && this.hasDrawnOncePerTurn);
		showFullHandButton.setEnabled(isPlayable && tooMuchCard);
	}

	@Override
	public void updateDisplay(PlayerManager playerManager) {
		if (playerManager.isTakingTurn())
			setBorder(BorderFactory.createLineBorder(Color.RED));
		else
			setBorder(BorderFactory.createEmptyBorder());
		handPanel.removeAll();
		this.handCardButton.clear();
		this.moreHandCardButton.clear();
		drawHandPanel(playerManager.getPlayerHand());
		setPlayable(playerManager.isTakingTurn(), playerManager.getPlayerHand().size() > MAX_HAND_SIZE);
		revalidate();
		repaint();
	}
	
	private void triggerActionEventParent(ActionEvent e) {
		this.actionPerformed(e);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == drawButton) {
			if (!this.hasDrawnOncePerTurn) {
				gameView.requestDrawCard();
				drawButton.setEnabled(false);
				endButton.setEnabled(true);
				this.hasDrawnOncePerTurn = true;
			}
		} else if (e.getSource() == endButton) {
			gameView.requestEndTurn();
		}  else if (e.getSource() == showFullHandButton) {
			showFullHand();
		} else {
			int index = this.handCardButton.indexOf(e.getSource());
			if (index >= 0) {
				if (this.handCardButton.get(index).toString().startsWith("Wild +4")) {
					this.gameView.requestShowWildDraw4Prompt(index);
				} else if (this.handCardButton.get(index).toString().startsWith("Wild")) {
					this.gameView.requestShowWildPrompt(index);
				} else {
					this.gameView.requestPlayCard(index);
				}
			}
		}
	}

	private void showFullHand() {
		JDialog d = new JDialog();
		ActionListener al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				d.dispose();
				triggerActionEventParent(e);
			}
		};
		d.setTitle("All Cards");
		d.setLayout(new GridLayout(4, 13, 5, 5));
		d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		for (CardButton card:this.moreHandCardButton) {
			card.addActionListener(al);
			d.add(card);
		}
		d.pack();
		d.setLocationRelativeTo(null);
		d.setResizable(false);
		d.setModalityType(ModalityType.APPLICATION_MODAL);
		d.setVisible(true);
	}
}
