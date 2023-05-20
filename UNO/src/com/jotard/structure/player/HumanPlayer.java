package structure.player;

import java.util.ArrayList;
import java.util.List;

import structure.card.Card;
import structure.deck.Deck;
import structure.game.GameManager;

public class HumanPlayer implements PlayerManager {

	private String name;
	private List<Card> cardHand;
	private Deck deck;
	private Card lastCardPlayed;
	private GameManager gameManager;

	private boolean isBanned;
	private int startDraw;

	public HumanPlayer(String name, GameManager gm) {
		this.name = name;
		this.cardHand = new ArrayList<>();
		this.isBanned = false;
		this.gameManager = gm;
		this.startDraw = 0;
	}

	@Override
	public void setBanned(boolean state) {
		this.isBanned = state;

	}

	@Override
	public void setDrawWhenTurnStart(int number) {
		this.startDraw = number;
	}

	@Override
	public void drawCard() {
		deck.drawCard(this);
	}

	@Override
	public void drawCard(int number) {
		deck.drawCard(this, number);
	}

	@Override
	public void takeTurn() {
		if (this.startDraw > 0) {
			drawCard(startDraw);
			this.startDraw = 0;
		}
		if (this.isBanned) {
			System.out.println(name + "'s banned!");
			this.isBanned = false;
			return;
		}
		System.out.println("Last played card : " + lastCardPlayed);
		System.out.println(name + "'s hand = " + cardHand);
		System.out.println(
				"what's " + name + " gonna do? play <0-index card hand>: play card, draw: draw a card, end: endturn");
		promptingAction(false);
		System.out.println(name + "'s turn ended.");
	}

	private void promptingAction(boolean hasDrawnFirstCard) {
		String cmd = null;
		do {
			System.out.print("Your action ?");
			cmd = gameManager.getInput().nextLine();
		} while (!(cmd.equals("draw") || cmd.matches("play \\d+") || cmd.equals("end")));
		
		if (cmd.equals("draw")) {
			if (hasDrawnFirstCard) {
				System.out.println("Error: you have drawn a card, play a card or end your turn");
				promptingAction(true);
				return;
			}
			else {
				drawCard();
				hasDrawnFirstCard = true;
				System.out.println(name + "'s hand = " + cardHand);
				promptingAction(true);
				return;
			}
		}
		else if (cmd.equals("end")) {
			if (!hasDrawnFirstCard) {
				System.out.println("Error: you haven't draw a card before ending the turn, use draw");
				promptingAction(hasDrawnFirstCard);
				return;
			}
		}
		else if (cmd.matches("play \\d+")) {
			if (Integer.valueOf(cmd.substring(5)) < 0 || Integer.valueOf(cmd.substring(5)) >= this.cardHand.size()) {
				System.out.println("Error: the card with that index from your hand does not exists");
				promptingAction(hasDrawnFirstCard);
				return;
			}
			int index = Integer.valueOf(cmd.substring(5));
			if (this.cardHand.get(index).getColor() == null) {
				playCard(this.cardHand.get(index));
			}
			else if (this.cardHand.get(index).getColor().equals(this.lastCardPlayed.getColor())
					|| this.cardHand.get(index).getNumber() == this.lastCardPlayed.getNumber()) 
				playCard(this.cardHand.get(index));
		}
	}

	@Override
	public void addCardToHand(Card pop) {
		this.cardHand.add(pop);
	}
	
	@Override
	public void removeCardFromHand(Card pop) {
		this.cardHand.remove(pop);
	}
	
	private void playCard(Card c) {
		c.play(this.gameManager);
		removeCardFromHand(c);
	}

	@Override
	public boolean hasWon() {
		return this.cardHand.isEmpty();
	}

	@Override
	public void notifyDeck(Deck deck) {
		this.deck = deck;
	}

	@Override
	public void notifyLastPlayedCard(Card previousCard) {
		this.lastCardPlayed = previousCard;
	}
	
	@Override
	public String toString() {
		return name;
	}

}
