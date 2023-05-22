package com.jotard.gui.card;

public class ActionCardButton extends CardButton {
	public static final int DRAW_TWO = 0;
	public static final int REVERSE = 1;
	public static final int BAN = 2;

	private String color;
	private int functionType;

	@Override
	public String toString() {
		String result = this.color + " ";
		switch (functionType) {
		case DRAW_TWO:
			result += "Draw";
			break;
		case REVERSE:
			result += "Reverse";
			break;
		case BAN:
			result += "Skip";
			break;
		}
		return result;
	}

	public ActionCardButton(String color, int functionType) {
		super("/image/" + color + "_" + convertFunctionType(functionType) + ".png");
		this.color = color;
		this.functionType = functionType;
		setToolTipText(this.toString());
	}

	@Override
	public int getNumber() {
		return -1;
	}

	@Override
	public void play() {
		switch (this.functionType) {
		case DRAW_TWO:
			break;
		case REVERSE:
			break;
		case BAN:
		default:
		}
	}
	
	private static String convertFunctionType(int functionType) {
		switch (functionType) {
		case DRAW_TWO:
			return "Draw";
		case REVERSE:
			return "Reverse";
		case BAN:
			return "Skip";
		default:
			return null;
		}
	}
}
