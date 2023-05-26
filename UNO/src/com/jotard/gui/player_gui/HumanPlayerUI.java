package com.jotard.gui.player_gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jotard.gui.card_button.CardButton;
import com.jotard.gui.card_button.CardButtonFactory;
import com.jotard.gui.game_gui.GameViewInterface;
import com.jotard.structure.card.Card;
import com.jotard.structure.player.PlayerManager;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;

public class HumanPlayerUI extends PlayerUI implements ActionListener {

	public static final int MAX_HAND_SIZE = 12;
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
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		JPanel p1, p2;
		add(p1 = new JPanel());
		p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
		p1.add(new JLabel(playerManager.getPlayerName(), JLabel.CENTER));
		p1.add(handPanel = new JPanel());
		handPanel.add(CardButtonFactory.getInstance().createNormalWildCard());
		add(p2 = new JPanel());
		p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));
		p2.add(Box.createGlue());
		p2.add(drawButton = new JButton("Draw a card"));
		drawButton.addActionListener(this);
		p2.add(Box.createGlue());
		p2.add(endButton = new JButton("End turn"));
		endButton.addActionListener(this);
		p2.add(Box.createGlue());
		p2.add(showFullHandButton = new JButton("Show more cards"));
		showFullHandButton.addActionListener(this);
		p2.add(Box.createGlue());
		for (Component c : p1.getComponents()) {
			((JComponent) c).setAlignmentX(CENTER_ALIGNMENT);
		}
		Dimension d = new Dimension(showFullHandButton.getMaximumSize().width + 25, showFullHandButton.getMaximumSize().height);
		p2.setPreferredSize(d);
		for (Component c : p2.getComponents()) {
			((JComponent) c).setAlignmentX(CENTER_ALIGNMENT);
			c.setMaximumSize(d);
		}

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
			}
			else {
				this.moreHandCardButton.add(cb);
			}
			this.handCardButton.add(cb);
			cb.addActionListener(this);
		}
	}

	private void setPlayable(boolean isPlayable, boolean tooMuchCard) {
		if (isPlayable)
			this.hasDrawnOncePerTurn = false;
		for (CardButton c : handCardButton) {
			c.setEnabled(isPlayable);
		}
		drawButton.setEnabled(isPlayable);
		endButton.setEnabled(isPlayable && this.hasDrawnOncePerTurn);
		showFullHandButton.setEnabled(isPlayable && tooMuchCard);
	}

	@Override
	public void updateDisplay(PlayerManager playerManager) {
		if (playerManager.isTakingTurn())
			setBorder(BorderFactory.createLineBorder(Color.RED));
		else
			setBorder(BorderFactory.createEtchedBorder());
		handPanel.removeAll();
		this.handCardButton.clear();
		this.moreHandCardButton.clear();
		drawHandPanel(playerManager.getPlayerHand());
		setPlayable(playerManager.isTakingTurn(), playerManager.getPlayerHand().size() > MAX_HAND_SIZE);
		revalidate();
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.drawButton) {
			if (!this.hasDrawnOncePerTurn) {
				this.gameView.requestDrawCard();
				this.drawButton.setEnabled(false);
				this.endButton.setEnabled(true);
				this.hasDrawnOncePerTurn = true;
			}
		} else if (e.getSource() == endButton) {
			this.gameView.requestEndTurn();
		}  else if (e.getSource() == showFullHandButton) {
			this.showTheRemainingCard();
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

	private void showTheRemainingCard() {
		JDialog d = new JDialog();
		ActionListener al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				d.dispose();
			}
		};
		d.setTitle(this.moreHandCardButton.size() + " other cards");
		d.setLayout(new GridBagLayout());
		d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(5, 5, 5, 5);
		for (int i = 0; i < this.moreHandCardButton.size(); i++) {
			this.moreHandCardButton.get(i).addActionListener(al);
			d.add(this.moreHandCardButton.get(i), gbc);
			gbc.gridx++;
			if (gbc.gridx >= 14) {
				gbc.gridx = 0;
				gbc.gridy++;
			}
		}
		d.pack();
		d.setLocationRelativeTo(null);
		d.setResizable(false);
		d.setModalityType(ModalityType.APPLICATION_MODAL);
		d.setVisible(true);
	}
}
