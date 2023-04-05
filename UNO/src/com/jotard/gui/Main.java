package com.jotard.gui;

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
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalButtonUI;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.plaf.synth.SynthButtonUI;

import com.jotard.image.ImageManager;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int SCREEN_WIDTH = 1000;
	private static final int SCREEN_HEIGHT = 640;

	public Main() {
		super("UNO!");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) throws IOException {
		
		String[] color = { "Blue", "Green", "Red", "Yellow" };
		String[] number = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "Draw", "Reverse", "Skip" };
		ImageManager iManager = ImageManager.getInstance();
		double cardSize = 0.25d;
		iManager.loadAndCacheImage("Wild", cardSize);
		iManager.loadAndCacheImage("Wild_Draw", cardSize);
		iManager.loadAndCacheImage("Deck", cardSize);
		iManager.loadAndCacheImage("Uno Logo", 0.075d);
		for (String c : color)
			for (String n : number)
				iManager.loadAndCacheImage(c + "_" + n, cardSize);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				editUI();
				Main m = new Main();
				Menu a = new MainMenu();
				a.makeEvents();
				m.add(a);
			}
		});
	}

	private static void editUI() {
		Font f;
		try {
			f = Font.createFont(Font.TRUETYPE_FONT, Main.class.getResourceAsStream("/font/Stroud-anB5.ttf"));
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
			Enumeration<Object> cringe =  UIManager.getLookAndFeelDefaults().keys();
			Font font = null;
			while (cringe.hasMoreElements()) {
				String c = String.valueOf(cringe.nextElement());
				if (c.matches("^\\S+font$")) {
					font = UIManager.getLookAndFeelDefaults().getFont(c);
					UIManager.getLookAndFeelDefaults().put(c, f.deriveFont(25f));
				}
			}
		} catch (FontFormatException | IOException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

}
