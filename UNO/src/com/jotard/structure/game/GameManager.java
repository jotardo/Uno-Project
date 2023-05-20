package structure.game;

import java.util.Scanner;

import structure.card.Card;
import structure.player.PlayerManager;

public interface GameManager {
	
	public Scanner getInput();
	
	public void notifyDeck();
	public void notifyLastCard();
	public void setLastPlayedCard(Card card);
	public PlayerManager getNextPlayer();
	public void reverseTurn();
	
}
