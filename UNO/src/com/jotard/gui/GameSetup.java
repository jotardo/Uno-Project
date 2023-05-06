package com.jotard.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class GameSetup extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JButton okBtn, cancelBtn;
	
	public GameSetup(Frame frame) {
		super(frame);
		getContentPane().setPreferredSize(new Dimension(550, 400));
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setResizable(false);
		setupGUI();
		pack();
		setLocationRelativeTo(frame);
		setVisible(true);
	}

	private void setupGUI() {
		JPanel panel = new JPanel();
		panel.add(new JLabel("Select number of players?"));
		panel.add(Box.createHorizontalStrut(getContentPane().getPreferredSize().width));
		panel.add(new JSpinner(new SpinnerNumberModel(2, 2, 4, 1)));
		panel.add(Box.createHorizontalStrut(getContentPane().getPreferredSize().width));
		panel.add(okBtn = new JButton("OK"));
		panel.add(cancelBtn = new JButton("Cancel"));
		for (Component c : panel.getComponents()) {
			((JComponent) c).setAlignmentX(CENTER_ALIGNMENT);
		}
		add(Box.createGlue());
		add(panel);
		add(Box.createGlue());
		
		okBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancelBtn)
			dispose();
	}

}
