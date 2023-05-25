package com.jotard.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import com.jotard.controller.GameController;
import com.jotard.image.ImageManager;

public class Main extends JFrame implements ActionListener, Runnable{

	private static final long serialVersionUID = 1L;
	private static final int SCREEN_WIDTH = 1000;
	private static final int SCREEN_HEIGHT = 640;
	private JButton button1, button4;

	public Main() throws IOException {
		super("UNO!");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		pack();
		setLocationRelativeTo(null);
		editUI();
		loadAndCacheImages();
		createMenu();
		setVisible(true);
	}

	private void createMenu() {
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		add(Box.createGlue());
		JLabel label = new JLabel(
				new ImageIcon(ImageManager.getInstance().getScaledImage("/image/Uno Logo.png", 0.085d)));
		label.setAlignmentX(CENTER_ALIGNMENT);
		add(label);
		add(Box.createGlue());
		makeButton();
		add(Box.createGlue());
	}

	private void makeButton() {
		Border border = BorderFactory.createCompoundBorder(BorderFactory.createRaisedSoftBevelBorder(),
				BorderFactory.createEmptyBorder());
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(border);
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.setAlignmentX(CENTER_ALIGNMENT);
		buttonPanel.setPreferredSize(new Dimension(250, 250));
		buttonPanel.setMaximumSize(new Dimension(250, 250));
		buttonPanel.add(Box.createGlue());
		button1 = new JButton("4-Player Game");
		button1.setAlignmentX(CENTER_ALIGNMENT);
		buttonPanel.add(button1);
		buttonPanel.add(Box.createGlue());
		button4 = new JButton("Exit");
		button4.setAlignmentX(CENTER_ALIGNMENT);
		buttonPanel.add(button4);
		buttonPanel.add(Box.createGlue());
		add(buttonPanel);
		button1.addActionListener(this);
		button4.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button4)
			System.exit(0);
		if (e.getSource() == button1) {
			this.dispose();
			new GameController();
		}
	}

	private void editUI() {
		Font f;
		try {
			f = Font.createFont(Font.TRUETYPE_FONT, Main.class.getResourceAsStream("/font/Stroud-anB5.ttf"));
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
			UIManager.getLookAndFeelDefaults().forEach((k, v) -> {
				if (k.toString().matches("\\w+.font")) {
					UIManager.getLookAndFeelDefaults().put(k, f.deriveFont(28f));
				}
			});
		} catch (FontFormatException | IOException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

	private void loadAndCacheImages() throws IOException {
		String[] color = { "Blue", "Green", "Red", "Yellow" };
		String[] number = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "Draw", "Reverse", "Skip", "Wild", "Wild_Draw" };
		ImageManager iManager = ImageManager.getInstance();
		iManager.loadAndCacheImage("/image/Wild.png");
		iManager.loadAndCacheImage("/image/Wild_Draw.png");
		iManager.loadAndCacheImage("/image/Deck.png");
		iManager.loadAndCacheImage("/image/Uno Logo.png");
		for (String c : color)
			for (String n : number)
				iManager.loadAndCacheImage("/image/" + c + "_" + n + ".png");
	}

	@Override
	public void run() {
	}

}
