package com.jotard.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.jotard.controller.GameController;
import com.jotard.gui.card.CardButton;
import com.jotard.gui.card.CardButtonFactory;
import com.jotard.image.ImageManager;
import com.jotard.structure.card.Card;
import com.jotard.structure.player.PlayerManager;

public class GameView extends JFrame implements GameViewInterface {

	private static final long serialVersionUID = 1L;
	private JLabel lastPlayedCardGraphic;
	private Box leftPanel, rightPanel, upPanel, downPanel;
	private GameController gameController;
	private List<PlayerUI> uiList;

	public GameView(GameController gameController) {
		setSize(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width,
				GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height);
		setResizable(false);
		setLayout(new BorderLayout());
		setUpCenter();
		add(leftPanel = new Box(BoxLayout.Y_AXIS), BorderLayout.WEST);
		leftPanel.setPreferredSize(new Dimension(130, getPreferredSize().height));
		add(rightPanel = new Box(BoxLayout.Y_AXIS), BorderLayout.EAST);
		rightPanel.setPreferredSize(new Dimension(130, getPreferredSize().height));
		add(upPanel = new Box(BoxLayout.X_AXIS), BorderLayout.NORTH);
		upPanel.setPreferredSize(new Dimension(getPreferredSize().width, 200));
		add(downPanel = new Box(BoxLayout.X_AXIS), BorderLayout.SOUTH);
		downPanel.setPreferredSize(new Dimension(getPreferredSize().width, 200));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.gameController = gameController;
		this.uiList = new ArrayList<>();
	}

	private void setUpCenter() {
		JPanel p = new JPanel();
		p.setBorder(BorderFactory.createEtchedBorder());
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
		p.add(Box.createGlue());
		p.add(createDrawButton());
		p.add(Box.createGlue());
		p.add(lastPlayedCardGraphic = new JLabel());
		p.add(Box.createGlue());
		add(p);
	}

	private JLabel createDrawButton() {
		return new JLabel() {
			private static final long serialVersionUID = 1L;
			private Image i = ImageManager.getInstance().getScaledImage("/image/Deck.png", 0.35d);

			{
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				setPreferredSize(new Dimension(i.getWidth(null), i.getHeight(null)));
				setMaximumSize(new Dimension(i.getWidth(null), i.getHeight(null)));
			}

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(i, 0, 0, getWidth(), getHeight(), null);
			}
		};
	}

	private void drawLastPlayedCard(Card lastPlayedCard) {
		if (lastPlayedCard != null) {
			CardButton c = CardButtonFactory.getInstance().createCard(lastPlayedCard);
			Image i = ImageManager.getInstance().getScaledImage(c.getImageURL(), 0.35d);
			lastPlayedCardGraphic.setIcon(new ImageIcon(i));
		}
	}

	public void drawPlayers(List<PlayerManager> playersList) {
		PlayerUI ui = null;
		downPanel.add(Box.createGlue());
		downPanel.add(ui = new HumanPlayerUI(playersList.get(0), this));
		this.uiList.add(0, ui);
		downPanel.add(Box.createGlue());
		for (int i = 1; i < playersList.size(); i++) {
			if (i == 1) {
				rightPanel.add(Box.createGlue());
				rightPanel.add(ui = new CPUPlayerUI(playersList.get(i), this));
				rightPanel.add(Box.createGlue());
				upPanel.add(Box.createGlue());
			} else if (i != playersList.size() - 1){
				System.out.println(playersList.size() - i);
				upPanel.add(ui =
						new CPUPlayerUI(playersList.get(playersList.size() - i), this));
				upPanel.add(Box.createGlue());
			} else {
				leftPanel.add(Box.createGlue());
				leftPanel.add(ui = new CPUPlayerUI(playersList.get(i), this));
				leftPanel.add(Box.createGlue());
			}
			this.uiList.add(i, ui);
		}
	}

	public void updateLastPlayedCard(Card lastPlayedCard) {
		drawLastPlayedCard(lastPlayedCard);
		revalidate();
		repaint();
	}

	public void updatePlayerUI(List<PlayerManager> pmList) {
		for (int i = 0; i < pmList.size(); i++)
			this.uiList.get(i).updateDisplay(pmList.get(i));
		revalidate();
		repaint();
	}

	@Override
	public void requestPlayCard(int cardIndex) {
		gameController.doPlayCard(cardIndex);
	}

	public void requestShowWildPrompt() {
		JDialog jdiag = new JDialog();
		JPanel panel = new JPanel();
		CardButton red = CardButtonFactory.getInstance().createColoredWildCard(CardButton.RED);
		CardButton blue = CardButtonFactory.getInstance().createColoredWildCard(CardButton.BLUE);
		CardButton green = CardButtonFactory.getInstance().createColoredWildCard(CardButton.GREEN);
		CardButton yellow = CardButtonFactory.getInstance().createColoredWildCard(CardButton.YELLOW);
		panel.add(red);
		panel.add(green);
		panel.add(blue);
		panel.add(yellow);
		ActionListener ac = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jdiag.dispose();
				if (e.getSource() == red)
					gameController.doActivateWild(CardButton.RED);
				else if (e.getSource() == green)
					gameController.doActivateWild(CardButton.GREEN);
				else if (e.getSource() == blue)
					gameController.doActivateWild(CardButton.BLUE);
				else if (e.getSource() == yellow)
					gameController.doActivateWild(CardButton.YELLOW);
			}
		};
		red.addActionListener(ac);
		green.addActionListener(ac);
		blue.addActionListener(ac);
		yellow.addActionListener(ac);
		jdiag.setTitle("Choose the color of this wild card");
		jdiag.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		jdiag.add(panel);
		jdiag.setLocationRelativeTo(null);
		jdiag.pack();
		jdiag.setResizable(false);
		jdiag.setModalityType(ModalityType.APPLICATION_MODAL);
		jdiag.setVisible(true);
	}

	public void requestShowWildDraw4Prompt() {
		JDialog jdiag = new JDialog();
		JPanel panel = new JPanel();
		CardButton red = CardButtonFactory.getInstance().createColoredWildDraw4Card(CardButton.RED);
		CardButton blue = CardButtonFactory.getInstance().createColoredWildDraw4Card(CardButton.BLUE);
		CardButton green = CardButtonFactory.getInstance().createColoredWildDraw4Card(CardButton.GREEN);
		CardButton yellow = CardButtonFactory.getInstance().createColoredWildDraw4Card(CardButton.YELLOW);
		panel.add(red);
		panel.add(green);
		panel.add(blue);
		panel.add(yellow);
		ActionListener ac = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jdiag.dispose();
				if (e.getSource() == red)
					gameController.doActivateWildDraw4(CardButton.RED);
				else if (e.getSource() == green)
					gameController.doActivateWildDraw4(CardButton.GREEN);
				else if (e.getSource() == blue)
					gameController.doActivateWildDraw4(CardButton.BLUE);
				else if (e.getSource() == yellow)
					gameController.doActivateWildDraw4(CardButton.YELLOW);
			}
		};
		red.addActionListener(ac);
		green.addActionListener(ac);
		blue.addActionListener(ac);
		yellow.addActionListener(ac);
		jdiag.setTitle("Choose the color of this wild draw 4 card");
		jdiag.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		jdiag.add(panel);
		jdiag.setLocationRelativeTo(null);
		jdiag.pack();
		jdiag.setResizable(false);
		jdiag.setModalityType(ModalityType.APPLICATION_MODAL);
		jdiag.setVisible(true);
	}
	
	@Override
	public void requestDrawCard() {
		this.gameController.doDrawCard();
	}

}
