package com.jotard.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.plaf.synth.SynthButtonUI;
import javax.swing.plaf.synth.SynthLookAndFeel;

import com.jotard.image.ImageManager;

public class MainMenu extends Menu implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private byte x, y;
	private JButton button4;

	public MainMenu() {
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(Box.createGlue());
		JLabel label = new JLabel(new ImageIcon(ImageManager.getInstance().getImage("Uno Logo")));
		label.setAlignmentX(CENTER_ALIGNMENT);
		add(label);
		add(Box.createGlue());
		makeButton();
		add(Box.createGlue());
	}

	private void makeButton() {
		Border border = BorderFactory.createCompoundBorder(BorderFactory.createRaisedSoftBevelBorder(), BorderFactory.createEmptyBorder());
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(border);
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.setAlignmentX(CENTER_ALIGNMENT);
		buttonPanel.setPreferredSize(new Dimension(250, 250));
		buttonPanel.setMaximumSize(new Dimension(250, 250));
		
		buttonPanel.add(Box.createGlue());
		
		JButton button1 = new JButton("Play Game");
		button1.setAlignmentX(CENTER_ALIGNMENT);
		buttonPanel.add(button1);
		
		buttonPanel.add(Box.createGlue());
		
		JButton button2 = new JButton("Instructions");
		button2.setAlignmentX(CENTER_ALIGNMENT);
		buttonPanel.add(button2);
		
		buttonPanel.add(Box.createGlue());
		
		JButton button3 = new JButton("Settings");
		button3.setAlignmentX(CENTER_ALIGNMENT);
		buttonPanel.add(button3);
		
		buttonPanel.add(Box.createGlue());
		
		button4 = new JButton("Exit");
		button4.setAlignmentX(CENTER_ALIGNMENT);
		buttonPanel.add(button4);
		
		buttonPanel.add(Box.createGlue());
		
		add(buttonPanel);
	}

	@Override
	public void makeEvents() {
		button4.addActionListener(this);
	}

	@Override
	public void killEvents() {
		button4.removeActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button4)
			System.exit(0);
	}

}
