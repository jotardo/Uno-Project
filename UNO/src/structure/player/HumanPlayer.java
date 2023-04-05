package structure.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import structure.Deck;
import structure.Publisher;
import structure.Subscriber;
import structure.card.Card;

public class HumanPlayer implements Player {
	
	private boolean hasWon;
	private boolean isBanned;
	private List<Card> cardHand;
	private String name;
	private Scanner input;
	private boolean hasDrawnFirst;
	
	public HumanPlayer(String name, Scanner input) {
		this.name = name;
		this.hasDrawnFirst = false;
		this.cardHand = new ArrayList<>();
		this.isBanned = false;
		this.input = input;
	}

	@Override
	public void setBanned(boolean state) {
		this.isBanned = state;
		
	}

	@Override
	public void setForceDraw(int number) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawCard() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void takeTurn() {
		if (this.isBanned) {
			System.out.println(name + "'s banned!");
			return;
		}
		System.out.print("what's " + name + " gonna do: ");
		System.out.println(name + "'s hand = " + cardHand);
		System.out.println(name + "'s turn ended.");
	}

	@Override
	public void addCardToHand(Card pop) {
		this.cardHand.add(pop);
	}

	@Override
	public void removeCardFromHand(Card pop) {
		this.cardHand.remove(pop);
	}

	@Override
	public boolean hasWon() {
		return hasWon;
	}

}
