package com.jotard.structure.card;

import com.jotard.structure.game.GameModel;

public class ActionCard implements Card {
	public static final int DRAW_TWO = 0;
	public static final int REVERSE = 1;
	public static final int BAN = 2;

	private String color;
	private int functionType;

	@Override
	public String toString() {
		String result = "Action:" + this.color + " ";
		switch (functionType) {
		case DRAW_TWO:
			result += "+2";
			break;
		case REVERSE:
			result += "Reverse";
			break;
		case BAN:
			result += "Ban";
			break;
		}
		return result;
	}

	public ActionCard(String color, int functionType) {
		this.color = color;
		this.functionType = functionType;
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
		switch (this.functionType) {
		case DRAW_TWO:
			gm.getNextPlayer().setDrawWhenTurnStart(2);
			gm.getNextPlayer().setBanned(true);
			break;
		case REVERSE:
			gm.reverseTurn();
			break;
		case BAN:
			gm.getNextPlayer().setBanned(true);
		default:
		}
		gm.setLastPlayedCard(this);
	}
}
