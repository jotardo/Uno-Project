package com.jotard.gui.card;

import java.awt.Image;

import com.jotard.image.ImageManager;
import com.jotard.structure.game.GameManager;

public class NormalCardButton extends CardButton {
	private String color;
	private int number;

	public NormalCardButton(String color, int number) {
		super("/image/" + color + "_" + number + ".png");
		this.color = color;
		this.number = number;
	}

	@Override
	public String toString() {
		return this.color + " " + this.number;
	}

	@Override
	public int getNumber() {
		return number;
	}

	@Override
	public void play() {
	}
}
