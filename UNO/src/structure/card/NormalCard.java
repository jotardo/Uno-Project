package structure.card;

import structure.game.GameManager;

public class NormalCard implements Card{
	private String color;
	private int number;
	
	public NormalCard(String color, int number) {
		this.color = color;
		this.number = number;
	}
	
	@Override
	public String toString() {
		return this.color + " " + this.number;
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
	public void play(GameManager gm) {
		gm.setLastPlayedCard(this);
	}
}
