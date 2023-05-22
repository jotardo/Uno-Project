package com.jotard.gui.card;

import com.jotard.structure.game.GameModel;

public class WildCardButton extends CardButton {

	private boolean drawFour;

	public WildCardButton(boolean drawFour) {
		super(drawFour ? "/image/Wild_Draw.png" : "/image/Wild.png");
		this.drawFour = drawFour;
		setToolTipText(this.toString());
	}
	
	@Override
	public String toString() {
		return "Wild" + (this.drawFour ? " +4" : "") + (this.color == null ? "" : "-" + this.color);
	}

	@Override
	public int getNumber() {
		return -1;
	}

	@Override
	public void play() {
	}
}