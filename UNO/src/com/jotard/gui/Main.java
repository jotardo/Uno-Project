package com.jotard.gui;

import java.awt.Dialog.ModalExclusionType;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;

import javax.imageio.ImageIO;
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
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.plaf.metal.MetalButtonUI;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.plaf.synth.SynthButtonUI;

import com.jotard.image.ImageManager;

public class Main extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static final int SCREEN_WIDTH = 1000;
	private static final int SCREEN_HEIGHT = 640;
	private JButton button1, button4;

	public Main() {
		super("UNO!");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		pack();
		setLocationRelativeTo(null);
		createMenu();
		setVisible(true);
	}

	private void createMenu() {
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		add(Box.createGlue());
		JLabel label = new JLabel(new ImageIcon(ImageManager.getInstance().getImage("Uno Logo")));
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

//		JButton button2 = new JButton("Instructions");
//		button2.setAlignmentX(CENTER_ALIGNMENT);
//		buttonPanel.add(button2);
//		
//		buttonPanel.add(Box.createGlue());
//		
//		JButton button3 = new JButton("Settings");
//		button3.setAlignmentX(CENTER_ALIGNMENT);
//		buttonPanel.add(button3);
//		
//		buttonPanel.add(Box.createGlue());

		button4 = new JButton("Exit");
		button4.setAlignmentX(CENTER_ALIGNMENT);
		buttonPanel.add(button4);

		buttonPanel.add(Box.createGlue());

		add(buttonPanel);
	}

	public void makeEvents() {
		button1.addActionListener(this);
		button4.addActionListener(this);
	}

	public void killEvents() {
		button1.removeActionListener(this);
		button4.removeActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button4)
			System.exit(0);
		if (e.getSource() == button1) {
			Game.main(null);
		}
	}

	public static void main(String[] args) throws IOException {

		String[] color = { "Blue", "Green", "Red", "Yellow" };
		String[] number = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "Draw", "Reverse", "Skip" };
		ImageManager iManager = ImageManager.getInstance();
		double cardSize = 0.25d;
		iManager.loadAndCacheImage("Wild");
		iManager.loadAndCacheImage("Wild_Draw");
		iManager.loadAndCacheImage("Deck");
		iManager.loadAndCacheImage("Uno Logo", 0.085d);
		for (String c : color)
			for (String n : number)
				iManager.loadAndCacheImage(c + "_" + n);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				editUI();
				Main m = new Main();
				m.makeEvents();
			}
		});
	}

	private static void editUI() {
		Font f;
		try {
			f = Font.createFont(Font.TRUETYPE_FONT, Main.class.getResourceAsStream("/font/Stroud-anB5.ttf"));
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
			UIManager.getLookAndFeelDefaults().forEach((k, v) -> {
				if (k.toString().matches("\\w+.font")) {
					UIManager.getLookAndFeelDefaults().put(k, f.deriveFont(26f));
				}
			});
		} catch (FontFormatException | IOException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

}
