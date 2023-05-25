package com.jotard.gui.card_button;

import com.jotard.image.ImageManager;

public class WildCardButton extends CardButton {

	private static final long serialVersionUID = 1L;
	private boolean drawFour;

	public WildCardButton(boolean drawFour, String color) {
		super(drawFour ? "/image/Wild_Draw.png" : "/image/Wild.png");
		this.drawFour = drawFour;
		this.setWildColor(color);
		setToolTipText(this.toString());
	}
	
	@Override
	public String toString() {
		return "Wild" + (this.drawFour ? " +4" : "") + (this.color == null ? "" : "-" + this.color);
	}
	
	public void setWildColor(String color) {
		this.color = color;
		if (color == null)
			this.imageURL = drawFour ? "/image/Wild_Draw.png" : "/image/Wild.png";
		else
			this.imageURL = drawFour ? "/image/" + color + "_Wild_Draw.png" : "/image/" + color + "_Wild.png";
		this.image = ImageManager.getInstance().getScaledImage(this.imageURL, 0.25d);
	}

	@Override
	public int getNumber() {
		return -1;
	}
}
