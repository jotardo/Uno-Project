package com.jotard.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

public class PlayerUI extends JPanel{

	private static final long serialVersionUID = 1L;

	public PlayerUI() {
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel = new JLabel("New label");
		add(lblNewLabel);
		
		JPanel panel = new JPanel();
		add(panel);
		
		JButton btnNewButton_4 = new JButton("New button");
		panel.add(btnNewButton_4);
		
		JButton btnNewButton_3 = new JButton("New button");
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_5 = new JButton("New button");
		panel.add(btnNewButton_5);
		
		JButton btnNewButton_2 = new JButton("New button");
		panel.add(btnNewButton_2);
		
		JButton btnNewButton = new JButton("New button");
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		panel.add(btnNewButton_1);
	}
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.add(new PlayerUI());
		f.pack();
		f.setVisible(true);
	}
}
