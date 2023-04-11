package structure.player;

import structure.card.Card;
import structure.deck.Deck;

public interface PlayerManager {
	
	public boolean hasWon();
	public void setBanned(boolean state);
	public void setDrawWhenTurnStart(int number);
	public void takeTurn();
	public void drawCard();
	public void drawCard(int number);
	public void addCardToHand(Card pop);
	public void removeCardFromHand(Card pop);
	
	public void notifyDeck(Deck deck);
	public void notifyLastPlayedCard(Card previousCard);
	
}
