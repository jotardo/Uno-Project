package com.jotard.structure.deck;

import java.util.Comparator;
import java.util.Random;
import java.util.Stack;

import com.jotard.structure.card.Card;
import com.jotard.structure.card.CardFactory;
import com.jotard.structure.game.Game;
import com.jotard.structure.game.GameModel;
import com.jotard.structure.game.GameNotifier;
import com.jotard.structure.player.PlayerManager;

public class Deck {
	
	private Stack<Card> cardList;
	private GameNotifier gameManager;
	
	public Deck(GameNotifier game) {
		this.cardList = new Stack<>();
		this.gameManager = game;
	}
	
	public void generateDeck() {
		String[] color = {Card.RED, Card.GREEN, Card.BLUE, Card.YELLOW};
		CardFactory cf = CardFactory.getInstance();
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
		this.cardList.sort(new Comparator<Card>() {
			@Override
			public int compare(Card o1, Card o2) {
				if (Math.random() < 0.5)
					return -1;
				return 1;
			}
		});
		this.gameManager.notifyDeck();
	}
	
	public void pickFirstCard(Game g) {
		while (this.cardList.peek().toString().startsWith("Wild")) {
			this.cardList.add(this.cardList.pop());
		}
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
