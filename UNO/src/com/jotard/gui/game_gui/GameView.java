package com.jotard.gui.game_gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.jotard.controller.GameControllerInterface;
import com.jotard.gui.HelpWindow;
import com.jotard.gui.Main;
import com.jotard.gui.card_button.CardButton;
import com.jotard.gui.card_button.CardButtonFactory;
import com.jotard.gui.player_gui.CPUPlayerUI;
import com.jotard.gui.player_gui.HumanPlayerUI;
import com.jotard.gui.player_gui.PlayerUI;
import com.jotard.image.ImageManager;
import com.jotard.structure.card.Card;
import com.jotard.structure.player.PlayerManager;

public class GameView extends JFrame implements GameViewInterface {

	private static final long serialVersionUID = 1L;
	private JLabel lastPlayedCardGraphic;
	private JLabel whatHappenLabel;
	private Box leftPanel, rightPanel, upPanel, downPanel;
	private OrderPanel leftOrderPanel, rightOrderPanel, upOrderPanel, downOrderPanel;
	private GameControllerInterface gameController;
	private List<PlayerUI> uiList;

	public GameView(GameControllerInterface gameController) {
		setTitle("Board");
		setSize(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width,
				GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height);
		setResizable(false);
		setLayout(new BorderLayout());
		setUpCenter();
		setUpPlayerHand();
		setUpSettings();
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				promptingQuitGame();
			}
		});
		setVisible(true);
		this.gameController = gameController;
		this.uiList = new ArrayList<>();
	}

	private void setUpCenter() {
		JPanel center = new JPanel(new BorderLayout());
		center.setBorder(BorderFactory.createEtchedBorder());
		JPanel pParent = new JPanel(new BorderLayout());
		pParent.setBorder(BorderFactory.createTitledBorder(""));
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
		p.add(Box.createGlue());
		p.add(createDrawImage());
		p.add(Box.createGlue());
		p.add(lastPlayedCardGraphic = new JLabel());
		p.add(Box.createGlue());
		pParent.add(p);
		pParent.add(whatHappenLabel = new JLabel("", JLabel.CENTER), BorderLayout.SOUTH);
		center.add(pParent);
		center.add(leftOrderPanel = new OrderPanel(OrderPanel.LEFT), BorderLayout.WEST);
		center.add(rightOrderPanel = new OrderPanel(OrderPanel.RIGHT), BorderLayout.EAST);
		center.add(upOrderPanel = new OrderPanel(OrderPanel.UP), BorderLayout.NORTH);
		center.add(downOrderPanel = new OrderPanel(OrderPanel.DOWN), BorderLayout.SOUTH);
		add(center);
	}

	private void setUpPlayerHand() {
		add(leftPanel = new Box(BoxLayout.Y_AXIS), BorderLayout.WEST);
		leftPanel.setPreferredSize(new Dimension(130, getPreferredSize().height));
		add(rightPanel = new Box(BoxLayout.Y_AXIS), BorderLayout.EAST);
		rightPanel.setPreferredSize(new Dimension(130, getPreferredSize().height));
		add(upPanel = new Box(BoxLayout.X_AXIS), BorderLayout.NORTH);
		upPanel.setPreferredSize(new Dimension(getPreferredSize().width, 190));
		add(downPanel = new Box(BoxLayout.X_AXIS), BorderLayout.SOUTH);
		downPanel.setPreferredSize(new Dimension(getPreferredSize().width, 190));
	}

	private void setUpSettings() {
		JLayeredPane pane = getLayeredPane();
		JButton settingsBtn = new JButton() {

			private static final long serialVersionUID = 1L;
			
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image i = ImageManager.getInstance().getScaledImage("/image/Home_Button.png", 2d);
				g.drawImage(i, 5, 5, getWidth() - 10, getHeight() - 10, null);
			}
			
		};
		settingsBtn.setBounds(5, 5, 65, 65);
		settingsBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gameController.doPause();
				showPauseMenu();
			}
		});
		pane.add(settingsBtn, BorderLayout.NORTH);
	}

	protected void showPauseMenu() {
		JPanel fadeBG = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(new Color(0, 0, 0, 64));
				g.fillRect(0, 0, getWidth(), getHeight());
			}
		};
		
		fadeBG.setOpaque(false);
		fadeBG.setBounds(getBounds());
		getLayeredPane().add(fadeBG, JLayeredPane.MODAL_LAYER);

		JPanel panel;
		JButton resumeBtn, returnToMain, exitBtn, helpBtn;
		JDialog diag = new JDialog();
		ActionListener al;
		diag.setTitle("Paused");
		diag.setResizable(false);
		diag.setModalityType(ModalityType.APPLICATION_MODAL);
		diag.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		diag.add(panel = new JPanel());
		diag.add(Box.createHorizontalStrut(60), BorderLayout.WEST);
		diag.add(Box.createHorizontalStrut(60), BorderLayout.EAST);
		diag.add(Box.createVerticalStrut(30), BorderLayout.NORTH);
		diag.add(Box.createVerticalStrut(30), BorderLayout.SOUTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(new JLabel("Pause Menu", JLabel.CENTER));
		panel.add(Box.createVerticalStrut(20));
		panel.add(resumeBtn = new JButton("Resume"));
		panel.add(Box.createVerticalStrut(10));
		panel.add(helpBtn = new JButton("Help"));
		panel.add(Box.createVerticalStrut(10));
		panel.add(returnToMain = new JButton("Back to menu"));
		panel.add(Box.createVerticalStrut(10));
		panel.add(exitBtn = new JButton("Quit"));
		for (Component c : panel.getComponents()) {
			c.setMaximumSize(new Dimension(200, 40));
			((JComponent) c).setAlignmentX(CENTER_ALIGNMENT);
		}
		al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == resumeBtn) {
					diag.dispose();
					gameController.doResume();
				} else if (e.getSource() == returnToMain) {
					diag.dispose();
					gameController.doDestroyView();
					new Main();
				}  else if (e.getSource() == helpBtn) {
					new HelpWindow();
				} else {
					promptingQuitGame();
				}
				revalidate();
				repaint();
			}
		};
		resumeBtn.addActionListener(al);
		helpBtn.addActionListener(al);
		returnToMain.addActionListener(al);
		exitBtn.addActionListener(al);
		diag.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				super.windowClosed(e);
				getLayeredPane().remove(fadeBG);
				revalidate();
				repaint();
			}
		});
		diag.pack();
		diag.setLocationRelativeTo(null);
		diag.setVisible(true);
	}

	private JLabel createDrawImage() {
		return new JLabel() {
			private static final long serialVersionUID = 1L;
			private Image i = ImageManager.getInstance().getScaledImage("/image/Deck.png", 0.35d);

			{
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
			lastPlayedCardGraphic.setToolTipText(c.toString());
		}
	}

	@Override
	public void popupError(String message) {
		JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void updateStatus(String message) {
		this.whatHappenLabel.setText(message);
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
				rightPanel.add(ui = new CPUPlayerUI(playersList.get(i)));
				rightPanel.add(Box.createGlue());
				upPanel.add(Box.createGlue());
			} else if (i != playersList.size() - 1) {
				upPanel.add(ui = new CPUPlayerUI(playersList.get(playersList.size() - i)));
				upPanel.add(Box.createGlue());
			} else {
				leftPanel.add(Box.createGlue());
				leftPanel.add(ui = new CPUPlayerUI(playersList.get(i)));
				leftPanel.add(Box.createGlue());
			}
			this.uiList.add(i, ui);
		}
	}

	public void updateLastPlayedCard(Card lastPlayedCard) {
		this.drawLastPlayedCard(lastPlayedCard);
	}

	public void updatePlayerUI(List<PlayerManager> pmList) {
		for (int i = 0; i < pmList.size(); i++)
			if (i == 0 || i == 1 || i == pmList.size() - 1)
				this.uiList.get(i).updateDisplay(pmList.get(i));
			else
				this.uiList.get(i).updateDisplay(pmList.get(pmList.size() - i));
		revalidate();
		repaint();
	}

	public void updateTurnOrder(boolean normalTurn) {
		leftOrderPanel.updateNormalOrientation(normalTurn);
		rightOrderPanel.updateNormalOrientation(normalTurn);
		upOrderPanel.updateNormalOrientation(normalTurn);
		downOrderPanel.updateNormalOrientation(normalTurn);
	}

	@Override
	public void requestPlayCard(int cardIndex) {
		gameController.doPlayCard(cardIndex);
	}

	public void requestShowWildPrompt(int index) {
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
					gameController.doActivateWild(CardButton.RED, index);
				else if (e.getSource() == green)
					gameController.doActivateWild(CardButton.GREEN, index);
				else if (e.getSource() == blue)
					gameController.doActivateWild(CardButton.BLUE, index);
				else if (e.getSource() == yellow)
					gameController.doActivateWild(CardButton.YELLOW, index);
			}
		};
		red.addActionListener(ac);
		green.addActionListener(ac);
		blue.addActionListener(ac);
		yellow.addActionListener(ac);
		jdiag.setTitle("Choose the color of this wild card");
		jdiag.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		jdiag.add(panel);
		jdiag.pack();
		jdiag.setLocationRelativeTo(null);
		jdiag.setResizable(false);
		jdiag.setModalityType(ModalityType.APPLICATION_MODAL);
		jdiag.setVisible(true);
	}

	public void requestShowWildDraw4Prompt(int index) {
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
					gameController.doActivateWildDraw4(CardButton.RED, index);
				else if (e.getSource() == green)
					gameController.doActivateWildDraw4(CardButton.GREEN, index);
				else if (e.getSource() == blue)
					gameController.doActivateWildDraw4(CardButton.BLUE, index);
				else if (e.getSource() == yellow)
					gameController.doActivateWildDraw4(CardButton.YELLOW, index);
			}
		};
		red.addActionListener(ac);
		green.addActionListener(ac);
		blue.addActionListener(ac);
		yellow.addActionListener(ac);
		jdiag.setTitle("Choose the color of this wild draw 4 card");
		jdiag.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		jdiag.add(panel);
		jdiag.pack();
		jdiag.setLocationRelativeTo(null);
		jdiag.setResizable(false);
		jdiag.setModalityType(ModalityType.APPLICATION_MODAL);
		jdiag.setVisible(true);
	}

	@Override
	public void requestDrawCard() {
		this.gameController.doDrawCard();
	}

	@Override
	public void requestEndTurn() {
		this.gameController.doEndTurn();
	}

	@Override
	public void drawEndGame(PlayerManager pm) {
		JDialog winDiag = new JDialog();
		JPanel panel = new JPanel();
		JButton replayBtn = new JButton("Replay");
		JButton menuBtn = new JButton("Back to main menu");
		GridBagConstraints gbc = new GridBagConstraints();
		winDiag.setResizable(false);
		winDiag.setModalityType(ModalityType.APPLICATION_MODAL);
		winDiag.add(Box.createHorizontalStrut(60), BorderLayout.WEST);
		winDiag.add(Box.createHorizontalStrut(60), BorderLayout.EAST);
		winDiag.add(Box.createVerticalStrut(30), BorderLayout.NORTH);
		winDiag.add(Box.createVerticalStrut(30), BorderLayout.SOUTH);
		winDiag.add(panel);
		panel.setLayout(new GridBagLayout());
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.insets.left = 5;
		gbc.insets.right = 5;
		gbc.insets.top = 3;
		gbc.insets.bottom = 3;
		panel.add(new JLabel(pm.getPlayerName() + " wins!", JLabel.CENTER), gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		panel.add(replayBtn, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		panel.add(menuBtn, gbc);
		replayBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				winDiag.dispose();
				destroyView();
				gameController.doRestart();
			}
		});
		menuBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				winDiag.dispose();
				destroyView();
				new Main();
			}
		});
		winDiag.pack();
		winDiag.setLocationRelativeTo(null);
		winDiag.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		winDiag.setVisible(true);
	}

	@Override
	public void destroyView() {
		this.dispose();
	}
	
	private void promptingQuitGame() {
		int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Quitting", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
		if (result == JOptionPane.OK_OPTION)
			System.exit(0);
	}

}
