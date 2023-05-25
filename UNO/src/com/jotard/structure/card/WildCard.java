package com.jotard.structure.card;

import com.jotard.structure.game.GameModel;

public class WildCard implements Card {

	private String color;
	private boolean drawFour;
	
	public WildCard(boolean drawFour) {
		this.drawFour = drawFour;
	}

	public WildCard(boolean drawFour, String color) {
		this.drawFour = drawFour;
		this.color = color;
	}
	
	@Override
	public String toString() {
		return "Wild:" + (this.drawFour ? " +4" : "") + (this.color == null ? "" : "-" + this.color);
	}

	@Override
	public String getColor() {
		return color;
	}

	@Override
	public int getNumber() {
		return -1;
	}

	@Override
	public void play(GameModel gm) {
		if (this.drawFour) {
			gm.getNextPlayer().setDrawWhenTurnStart(4);
			gm.getNextPlayer().setBanned(true);
		}
		gm.setLastPlayedCard(this);
	}
}
