package structure;

import java.util.Comparator;
import java.util.Random;
import java.util.Stack;

import structure.card.Card;
import structure.card.CardFactory;
import structure.player.Player;

public class Deck {
	
	private Stack<Card> cardList;
	
	public Deck() {
		this.cardList = new Stack<>();
	}
	
	public void generateDeck() {
		String[] color = {"Red", "Green", "Blue", "Yellow"};
		CardFactory cf = new CardFactory();
		for (String c: color) {
			this.cardList.add(cf.createNormalCard(c, 0));
			for (int i = 1; i <= 9; i++) {
				this.cardList.add(cf.createNormalCard(c, i));
				this.cardList.add(cf.createNormalCard(c, i));
			}
			this.cardList.add(cf.createBanCard(c));
			this.cardList.add(cf.createBanCard(c));
			this.cardList.add(cf.createDrawTwoCard(c));
			this.cardList.add(cf.createDrawTwoCard(c));
			this.cardList.add(cf.createReverseCard(c));
			this.cardList.add(cf.createReverseCard(c));
			this.cardList.add(cf.createWildCard());
			this.cardList.add(cf.createWildDraw4Card());
		}
		Comparator<Card> randomSort = new Comparator<Card>() {
			
			Random r = new Random();
			
			@Override
			public int compare(Card o1, Card o2) {
				if (r.nextBoolean())
					return -1;
				return 1;
			}
		};
		this.cardList.sort(randomSort);
	}
	
	public void drawCard(Player player, int number) {
		for (int i = 0; i < number; i++)
			player.addCardToHand(this.cardList.pop());
	}
	
	public void drawCard(Player player) {
		drawCard(player, 1);
	}
}
