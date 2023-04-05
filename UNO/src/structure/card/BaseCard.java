package structure.card;

public class BaseCard implements Card{

	@Override
	public String getColor() {
		return null;
	}

	@Override
	public int getNumber() {
		return -1;
	}
	
	@Override
	public String toString() {
		return "";
	}

	@Override
	public void playCard() {
		System.out.println("End Turn");
	}
}
