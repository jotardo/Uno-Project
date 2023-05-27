package com.jotard.gui;

import java.awt.BorderLayout;
import java.awt.Component;
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
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
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
	private static boolean started = false;
	private JButton button1, button2, button3, button4;

	public Main() {
		super("UNO!");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		pack();
		setLocationRelativeTo(null);
		if (!started) {
			editUI();
			try {
				loadAndCacheImages();
			} catch (IOException e) {
				e.printStackTrace();
			}
			started = true;
		}
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
		buttonPanel.setPreferredSize(new Dimension(300, 250));
		buttonPanel.setMaximumSize(new Dimension(300, 250));
		buttonPanel.add(Box.createGlue());
		button1 = new JButton("Play 4-Player Game");
		button1.setAlignmentX(CENTER_ALIGNMENT);
		buttonPanel.add(button1);
		buttonPanel.add(Box.createGlue());
		button2 = new JButton("Play Custom Game");
		button2.setAlignmentX(CENTER_ALIGNMENT);
		buttonPanel.add(button2);
		buttonPanel.add(Box.createGlue());
		button3 = new JButton("Read Help");
		button3.setAlignmentX(CENTER_ALIGNMENT);
		buttonPanel.add(button3);
		buttonPanel.add(Box.createGlue());
		button4 = new JButton("Quit Game");
		button4.setAlignmentX(CENTER_ALIGNMENT);
		buttonPanel.add(button4);
		buttonPanel.add(Box.createGlue());
		add(buttonPanel);
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		button4.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button4)
			System.exit(0);
		if (e.getSource() == button1) {
			this.dispose();
			SwingUtilities.invokeLater(new GameController(4, 7));
		}
		if (e.getSource() == button2) {
			JDialog d = new JDialog(this, "Choose how many CPU you want to play against");
			JSpinner s = new JSpinner(new SpinnerNumberModel(3, 1, 9, 1));
			JSpinner s2 = new JSpinner(new SpinnerNumberModel(7, 1, 30, 1));
			JButton button = new JButton("Confirm!");
			JPanel holder;
			JLabel l;
			d.setModal(true);
			d.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			d.add(holder = new JPanel(new BorderLayout()));
			d.add(Box.createHorizontalStrut(30), BorderLayout.EAST);
			d.add(Box.createHorizontalStrut(30), BorderLayout.WEST);
			d.add(Box.createVerticalStrut(30), BorderLayout.NORTH);
			d.add(Box.createVerticalStrut(30), BorderLayout.SOUTH);
			holder.setLayout(new BoxLayout(holder, BoxLayout.Y_AXIS));
			Box box = Box.createHorizontalBox();
			Box box2 = Box.createHorizontalBox();
			
			box.add(Box.createGlue());
			box.add(new JLabel("You VS : "));
			box.add(s);
			box.add(new JLabel(" CPU(s)"));
			box.add(Box.createGlue());
			box2.add(Box.createGlue());
			box2.add(new JLabel("Start with "));
			box2.add(s2);
			box2.add(new JLabel(" card(s) in hand"));
			box2.add(Box.createGlue());
			holder.add(l = new JLabel("Custom Rules Settings"));
			l.setFont(l.getFont().deriveFont(Font.BOLD));
			holder.add(Box.createVerticalStrut(15));
			holder.add(box);
			holder.add(box2);
			holder.add(button);
			for (Component c : holder.getComponents())
				((JComponent) c).setAlignmentX(CENTER_ALIGNMENT);
			button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					d.dispose();
					dispose();
					SwingUtilities.invokeLater(new GameController((Integer) s.getValue()  + 1, (Integer) s2.getValue()));
				}
			});
			d.pack();
			d.setLocationRelativeTo(null);
			d.setVisible(true);
		}
		if (e.getSource() == button3) {
			new HelpWindow();
		}
	}

	public static void editUI() {
		Font f;
		try {
			f = Font.createFont(Font.TRUETYPE_FONT, Main.class.getResourceAsStream("/font/Cabin-Medium.ttf"));
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
			UIManager.getLookAndFeelDefaults().forEach((k, v) -> {
				if (k.toString().matches("\\w+.font")) {
					float size = UIManager.getLookAndFeelDefaults().getFont(k).getSize2D() * 1.618f;
					UIManager.getLookAndFeelDefaults().put(k, f.deriveFont(size));
				}
			});
		} catch (FontFormatException | IOException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

	public static void loadAndCacheImages() throws IOException {
		String[] color = { "Blue", "Green", "Red", "Yellow" };
		String[] number = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "Draw", "Reverse", "Skip", "Wild", "Wild_Draw" };
		ImageManager iManager = ImageManager.getInstance();
		iManager.loadAndCacheImage("/image/Wild.png");
		iManager.loadAndCacheImage("/image/Wild_Draw.png");
		iManager.loadAndCacheImage("/image/Deck.png");
		iManager.loadAndCacheImage("/image/Uno Logo.png");
		iManager.loadAndCacheImage("/image/Arrow_Up.png");
		iManager.loadAndCacheImage("/image/Arrow_Down.png");
		iManager.loadAndCacheImage("/image/Arrow_Left.png");
		iManager.loadAndCacheImage("/image/Arrow_Right.png");
		iManager.loadAndCacheImage("/image/Home_Button.png");
		for (int i = 1; i <= 4; i++)
			iManager.loadAndCacheImage("/image/Tutorial_" + i + ".png");
		for (String c : color)
			for (String n : number)
				iManager.loadAndCacheImage("/image/" + c + "_" + n + ".png");
	}

	@Override
	public void run() {
	}

}
