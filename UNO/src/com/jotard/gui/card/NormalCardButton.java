package com.jotard.gui.card;

public class NormalCardButton extends CardButton {
	private static final long serialVersionUID = 1L;
	private String color;
	private int number;

	public NormalCardButton(String color, int number) {
		super("/image/" + color + "_" + number + ".png");
		this.color = color;
		this.number = number;
		setToolTipText(this.toString());
	}

	@Override
	public String toString() {
		return this.color + " " + this.number;
	}

	@Override
	public int getNumber() {
		return number;
	}
}
