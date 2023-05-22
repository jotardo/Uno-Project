package com.jotard.structure.deck;

import java.util.Comparator;
import java.util.Random;
import java.util.Stack;

import com.jotard.structure.card.Card;
import com.jotard.structure.card.CardFactory;
import com.jotard.structure.game.Game;
import com.jotard.structure.game.GameModel;
import com.jotard.structure.player.PlayerManager;

public class Deck {
	
	private Stack<Card> cardList;
	private GameModel gameManager;
	
	public Deck(GameModel game) {
		this.cardList = new Stack<>();
		this.gameManager = game;
	}
	
	public void generateDeck() {
		String[] color = {Card.RED, Card.GREEN, Card.BLUE, Card.YELLOW};
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
		this.gameManager.notifyDeck();
	}
	
	public void pickFirstCard(Game g) {
		g.setLastPlayedCard(this.cardList.pop());
		this.gameManager.notifyDeck();
	}
	
	public void drawCard(PlayerManager player, int number) {
		for (int i = 0; i < number; i++)
			player.addCardToHand(this.cardList.pop());
		this.gameManager.notifyDeck();
	}
	
	public void drawCard(PlayerManager player) {
		drawCard(player, 1);
	}
}
