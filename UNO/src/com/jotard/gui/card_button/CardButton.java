package com.jotard.gui.card_button;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JButton;

import com.jotard.image.ImageManager;

public abstract class CardButton extends JButton{
	
	private static final long serialVersionUID = 1L;
	public static final String RED = "Red";
	public static final String GREEN = "Green";
	public static final String BLUE = "Blue";
	public static final String YELLOW = "Yellow";
	protected String color;
	protected String imageURL;
	protected Image image;
	private static final Color FADING_COLOR = new Color(0, 0, 0, 128);
	
	public CardButton(String image) {
		this.imageURL = image;
		this.image = ImageManager.getInstance().getScaledImage(this.imageURL, 0.25d);
		if (this.image != null) 
			setPreferredSize(new Dimension(this.image.getWidth(null), this.image.getHeight(null)));
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		if (!isEnabled()) {
			if (!g.getColor().equals(FADING_COLOR))
				g.setColor(FADING_COLOR);
			g.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
		}
	}
	
	public Image getImage() {
		return image;
	}
	
	public String getImageURL() {
		return imageURL;
	}
	
	public String getColor() {
		return color;
	}
	
	public abstract int getNumber();
}
