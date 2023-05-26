package com.jotard.gui.game_gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JButton;

import com.jotard.image.ImageManager;

public class SettingButton extends JButton {

	private static final long serialVersionUID = 1L;
	
	public SettingButton() {
		super();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image i = ImageManager.getInstance().getScaledImage("/image/Home_Button.png", 2d);
		g.drawImage(i, 5, 5, getWidth() - 10, getHeight() - 10, null);
	}

}
