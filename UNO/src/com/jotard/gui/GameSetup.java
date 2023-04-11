package com.jotard.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameSetup extends JDialog{

	private static final long serialVersionUID = 1L;
	
	public GameSetup(Frame frame) {
		super(frame);
		getContentPane().setPreferredSize(new Dimension(450, 300));
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setResizable(false);
		setupGUI();
		pack();
		setVisible(true);
	}

	private void setupGUI() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel.add(new JLabel("ADDTIONAL RULES CONFIG?"));
		panel.add(Box.createHorizontalStrut(getContentPane().getPreferredSize().width));
		panel.add(new JLabel("Draws can be stacked? "));
		panel.add(Box.createHorizontalStrut(getContentPane().getPreferredSize().width));
		panel.add(new JLabel("Infinite cards? "));
		panel.add(Box.createHorizontalStrut(getContentPane().getPreferredSize().width));
		panel.add(new JLabel("? "));
		add(panel);
		
		JPanel panel2 = new JPanel();
		panel2.add(new JButton("HAHAHA"));
		add(panel2);
	}

}
