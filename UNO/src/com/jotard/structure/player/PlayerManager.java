package com.jotard.structure.player;

import java.util.List;

import com.jotard.structure.card.Card;
import com.jotard.structure.deck.Deck;

public interface PlayerManager {
	
	//status
	public boolean hasWon();
	public String getPlayerName();
	public boolean isCPU();
	public List<Card> getPlayerHand();
	//action
	public void setBanned(boolean state);
	public void setDrawWhenTurnStart(int number);
	public void takeTurn();
	public void drawCard();
	public void drawCard(int number);
	public void addCardToHand(Card pop);
	public void removeCardFromHand(Card pop);
	//subscribers
	public void notifyDeck(Deck deck);
	public void notifyLastPlayedCard(Card previousCard);
	
}
