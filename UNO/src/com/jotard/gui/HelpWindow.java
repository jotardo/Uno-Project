package com.jotard.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.jotard.image.ImageManager;

public class HelpWindow extends JDialog {

	private static final long serialVersionUID = 1L;

	public HelpWindow() {
		super();
		setTitle("Help");
		createMenu();
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setResizable(false);
		setVisible(true);
	}

	private void createMenu() {
		JPanel p = new JPanel(new BorderLayout());
		JScrollPane sP = new JScrollPane();
		JPanel content = new JPanel();
		JPanel downPanel = new JPanel();
		JButton b;
		JLabel l;

		sP.setPreferredSize(new Dimension(900, 540));
		sP.setViewportView(content);
		sP.getVerticalScrollBar().setUnitIncrement(20);
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
		content.add(l = new JLabel("1. Enter the game"));
		l.setFont(l.getFont().deriveFont(l.getFont().getSize2D() * 1.618f));

		content.add(Box.createVerticalStrut(20));

		content.add(l = new JLabel(new ImageIcon(ImageManager.getInstance().getScaledImage("/image/Tutorial_1.png", 0.75))));
		l.setBorder(BorderFactory.createRaisedBevelBorder());

		content.add(new JLabel(
				"<html>- Click on the \"4-Player Game\" for a game of you against 3 computer players<br>- Click on the \"Custom Game\" to choose how many CPU you want to play against, how many card all players<br>start with.</html>"));

		content.add(l = new JLabel(new ImageIcon(ImageManager.getInstance().getScaledImage("/image/Tutorial_2.png", 0.75))));
		l.setBorder(BorderFactory.createRaisedBevelBorder());

		content.add(new JLabel("<html>Then pressed \"Confirm!\"</html>"));

		content.add(new JLabel(
				"<html>**Notes**<br>- The number of CPU you can fight range from 1 - 9 CPU(s)<br>- The number of cards you can draw can't be lower than 1</html>"));

		content.add(Box.createVerticalStrut(20));

		content.add(l = new JLabel("2. Play the game"));
		l.setFont(l.getFont().deriveFont(l.getFont().getSize2D() * 1.618f));

		content.add(Box.createVerticalStrut(20));

		content.add(l = new JLabel(
				new ImageIcon(ImageManager.getInstance().getScaledImage("/image/Tutorial_3.png", 0.45))));
		l.setBorder(BorderFactory.createRaisedBevelBorder());

		content.add(new JLabel("- There's arrow pointing around the table, this is the turn cycle for the board", new ImageIcon(ImageManager.getInstance().getScaledImage("/image/Arrow_Right.png", 0.05d)), JLabel.CENTER));
		
		content.add(new JLabel("- There's two object inside the center table:"));

		content.add(new JLabel("+ The UNO \"Deck\": This serves as a decoration only", new ImageIcon(ImageManager.getInstance().getScaledImage("/image/Deck.png", 0.09)), JLabel.LEADING));

		content.add(new JLabel("+ The last played card: This displays the last card being played", new ImageIcon(ImageManager.getInstance().getScaledImage("/image/Wild.png", 0.09)), JLabel.LEADING));

		content.add(new JLabel("- There's a status bar under the two objects"));

		content.add(new JLabel(
				"<html>- You will have a hand containing cards on the bottom, while the CPU will have a number indicating how<br>many card is in their hand</html>"));

		content.add(new JLabel("- When the player take their turn, a red border will appear"));

		content.add(new JLabel("- Your objective is to empty your hand before any other player do."));

		content.add(new JLabel("- Unless it's a Wild card or Wild +4 card, you can only play a card that either:"));
		content.add(new JLabel("+ Has the same color as the last played card"));
		content.add(new JLabel("+ Has the same number as the last played card"));
		content.add(new JLabel(
				"+ Has the same function as the last played card (i.e. when Green-Draw is the last played card, Yellow-Draw is playable"));
		content.add(
				new JLabel("- If you tried to play a card in the incorrect condition, a error message will appears!"));
		content.add(new JLabel(
				"- If you don't have any card playable, try pressing \"Draw a card\" to draw a card (You can only draw one per your current turn)"));
		content.add(new JLabel(
				"- If you don't have any card playable after that, press the \"End turn\" to end your turn"));
		content.add(new JLabel(
				"- When you play Wild or Wild +4 card, there will be a dialog asking you to pick the color, choose the color wisely"));


		content.add(Box.createVerticalStrut(20));

		content.add(l = new JLabel("3. End game"));
		l.setFont(l.getFont().deriveFont(l.getFont().getSize2D() * 1.618f));

		content.add(Box.createVerticalStrut(20));
		
		content.add(l = new JLabel(new ImageIcon(ImageManager.getInstance().getScaledImage("/image/Tutorial_4.png", 0.4))));
		l.setBorder(BorderFactory.createRaisedBevelBorder());
		content.add(new JLabel(
				"- When a player emptied their hand, it will pop up a screen like the picture above, you can either:"));
		content.add(new JLabel(
				"+ Replay: Replay the match with the same ruleset as before"));
		content.add(new JLabel(
				"+ Return to main menu: Back to main menu to choose different game set"));
		
		p.add(new JLabel("Help Guide", JLabel.CENTER), BorderLayout.NORTH);
		p.add(sP);
		p.add(downPanel, BorderLayout.SOUTH);
		downPanel.add(b = new JButton("Got it!"));
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		add(p);
	}

}
