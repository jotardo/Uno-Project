package com.jotard.structure.card;

import com.jotard.structure.game.GameModel;

public class NormalCard implements Card{
	private String color;
	private int number;
	
	public NormalCard(String color, int number) {
		this.color = color;
		this.number = number;
	}
	
	@Override
	public String toString() {
		return "Normal:" + this.color + " " + this.number;
	}
	
	@Override
	public String getColor() {
		return color;
	}
	@Override
	public int getNumber() {
		return number;
	}
	@Override
	public void play(GameModel gm) {
		gm.setLastPlayedCard(this);
		gm.endCurrentPlayerTurn();
	}
}
