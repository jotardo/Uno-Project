package com.jotard.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jotard.gui.card.CardButton;
import com.jotard.gui.card.WildCardButton;
import com.jotard.structure.card.Card;
import com.jotard.structure.player.PlayerManager;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

public class HumanPlayerUI extends JPanel{

	private static final long serialVersionUID = 1L;
	private JLabel nameLabel;
	private JPanel handPanel;
	private JButton drawButton;
	private JButton endButton;

	public HumanPlayerUI(String name) {
		super();
		JPanel p1, p2;
		add(p1 = new JPanel());
		p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
		p1.add(nameLabel = new JLabel(name));
		p1.add(handPanel = new JPanel());
		add(p2 = new JPanel());
		p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));
		p2.add(drawButton = new JButton("Draw"));
		p2.add(endButton = new JButton("End turn"));
	}
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new HumanPlayerUI("Hello"));
		f.pack();
		f.setVisible(true);
	}
}
