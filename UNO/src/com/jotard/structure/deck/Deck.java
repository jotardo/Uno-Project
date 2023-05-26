package com.jotard.structure.deck;

import java.util.Collections;
import java.util.Stack;

import com.jotard.structure.card.Card;
import com.jotard.structure.card.CardFactory;
import com.jotard.structure.game.Game;
import com.jotard.structure.game.GameNotifier;
import com.jotard.structure.player.PlayerManager;

public class Deck {
	
	private Stack<Card> cardList;
	private GameNotifier gameNotifier;
	
	public Deck(GameNotifier game) {
		this.cardList = new Stack<>();
		this.gameNotifier = game;
	}
	
	public void generateDeck() {
		String[] color = {Card.RED, Card.GREEN, Card.BLUE, Card.YELLOW};
		CardFactory cf = CardFactory.getInstance();
		for (String c: color) {
//			this.cardList.add(cf.createNormalCard(c, 0));
//			for (int i = 1; i <= 9; i++) {
//				this.cardList.add(cf.createNormalCard(c, i));
//				this.cardList.add(cf.createNormalCard(c, i));
//			}
//			this.cardList.add(cf.createBanCard(c));
//			this.cardList.add(cf.createBanCard(c));
//			this.cardList.add(cf.createDrawTwoCard(c));
//			this.cardList.add(cf.createDrawTwoCard(c));
//			this.cardList.add(cf.createReverseCard(c));
//			this.cardList.add(cf.createReverseCard(c));
			this.cardList.add(cf.createWildCard());
			this.cardList.add(cf.createWildDraw4Card());
		}
		Collections.shuffle(this.cardList);
		this.gameNotifier.notifyDeck();
	}
	
	public void pickFirstCard(Game g) {
//		while (this.cardList.peek().toString().startsWith("Wild")) {
//			this.cardList.add(0, this.cardList.pop());
//		}
		g.setLastPlayedCard(this.cardList.pop());
		this.gameNotifier.notifyDeck();
	}
	
	public void drawCard(PlayerManager player, int number) {
		for (int i = 0; i < number; i++) {
			if (this.cardList.isEmpty())
				this.generateDeck();
			player.addCardToHand(this.cardList.pop());
		}
		this.gameNotifier.notifyDeck();
	}
	
	public void drawCard(PlayerManager player) {
		drawCard(player, 1);
	}
}
