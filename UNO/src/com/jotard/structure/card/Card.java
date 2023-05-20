package structure.card;

import structure.game.GameManager;

public interface Card {
	
	public static final String RED = "Red";
	public static final String GREEN = "Green";
	public static final String BLUE = "Blue";
	public static final String YELLOW = "Yellow";
	
	public String getColor();
	public int getNumber();
	public void play(GameManager gameManager);
}
