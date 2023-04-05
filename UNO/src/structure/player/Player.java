package structure.player;

import structure.card.Card;

public interface Player {
	
	public boolean hasWon();
	public void setBanned(boolean state);
	public void setForceDraw(int number);
	public void drawCard();
	public void takeTurn();
	public void addCardToHand(Card pop);
	public void removeCardFromHand(Card pop);
	
}
