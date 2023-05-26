package com.jotard.gui.game_gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import com.jotard.image.ImageManager;

public class OrderPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	public static final byte DOWN = 3;
	public static final byte UP = 2;
	public static final byte RIGHT = 1;
	public static final byte LEFT = 0;
	private static final double IMAGE_SCALE = 0.1;
	private static final int IMAGE_SPACING = 60;

	private boolean isNormalOrientation;
	private byte currentOrientation;
	
	public OrderPanel(byte orientation) {
		this.currentOrientation = orientation;
		this.isNormalOrientation = false;
		setPreferredSize(new Dimension(45,45));
	}
	
	public void updateNormalOrientation(boolean isNormalOritentation) {
		if (isNormalOritentation != this.isNormalOrientation) {
			this.isNormalOrientation = isNormalOritentation;
			repaint();
		}
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image i = null;
		ImageManager im = ImageManager.getInstance();
		switch (this.currentOrientation) {
		case DOWN:
			i = isNormalOrientation ? im.getScaledImage("/image/Arrow_Right.png", IMAGE_SCALE) : im.getScaledImage("/image/Arrow_Left.png", IMAGE_SCALE);
			break;
		case UP:
			i = isNormalOrientation ? im.getScaledImage("/image/Arrow_Left.png", IMAGE_SCALE) : im.getScaledImage("/image/Arrow_Right.png", IMAGE_SCALE);
			break;
		case LEFT:
			i = isNormalOrientation ? im.getScaledImage("/image/Arrow_Down.png", IMAGE_SCALE) : im.getScaledImage("/image/Arrow_Up.png", IMAGE_SCALE);
			break;
		case RIGHT:
			i = isNormalOrientation ? im.getScaledImage("/image/Arrow_Up.png", IMAGE_SCALE) : im.getScaledImage("/image/Arrow_Down.png", IMAGE_SCALE);
			break;
		}
		if (this.currentOrientation == LEFT || this.currentOrientation == RIGHT) {
			for (int d = 20; d < getHeight(); d+=i.getHeight(null) + IMAGE_SPACING)
				g.drawImage(i, 0, d, i.getWidth(null), i.getHeight(null), null);
		}
		else {
			for (int d = 0; d < getWidth(); d+=i.getWidth(null) + IMAGE_SPACING)
				g.drawImage(i, d, 0, i.getWidth(null), i.getHeight(null), null);
		}
		g.dispose();
	}
	
}