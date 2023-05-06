package com.jotard.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jotard.image.ImageManager;

public class Game extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public Game() {
		setLayout(new BorderLayout());
		setUpPlayer();
		setUpCenter();
	}

	private void setUpPlayer() {
		JPanel down = new JPanel();
		down.add(new PlayerUI());
		down.add(new PlayerUI());
		add(down, BorderLayout.SOUTH);
		JPanel up = new JPanel();
		up.add(new PlayerUI());
		up.add(new PlayerUI());
		add(up, BorderLayout.NORTH);
	}

	private void setUpCenter() {
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
		p.add(Box.createGlue());
		p.add(new JButton());
		p.add(Box.createGlue());
		p.add(new JLabel(new ImageIcon(ImageManager.getInstance().getScaledImage("Wild", 0.4d))));
		p.add(Box.createGlue());
		
		add(p);
	}
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		f.add(new Game());
		f.setVisible(true);
	}

}
