package com.jotard.structure.player;

import java.util.ArrayList;
import java.util.List;

import com.jotard.structure.card.Card;
import com.jotard.structure.card.CardFactory;
import com.jotard.structure.card.WildCard;
import com.jotard.structure.deck.Deck;
import com.jotard.structure.game.GameModel;

public class CPUPlayer implements PlayerManager {

	private String name;
	private List<Card> cardHand;
	private Deck deck;
	private Card lastCardPlayed;
	private GameModel gameManager;

	private boolean isBanned;
	private int startDraw;
	private boolean isTakingTurn;

	public CPUPlayer(String name, GameModel gm) {
		this.name = name;
		this.cardHand = new ArrayList<>();
		this.isBanned = false;
		this.gameManager = gm;
		this.startDraw = 0;
		this.isTakingTurn = false;
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
		System.out.println(name + ":: draw card!");
		deck.drawCard(this);
	}

	@Override
	public void drawCard(int number) {
		System.out.println(name + ":: draw "+ number + " card!");
		deck.drawCard(this, number);
	}

	@Override
	public void takeTurn() {
		this.isTakingTurn = true;
		if (this.startDraw > 0) {
			drawCard(startDraw);
			this.startDraw = 0;
		}
		if (this.isBanned) {
			System.out.println(name + ":: currently banned!");
			this.isBanned = false;
			this.gameManager.endCurrentPlayerTurn();
		}
	}

	public void promptingAction(boolean hasDrawnFirstCard) {
		List<Integer> playableCard = new ArrayList<>();
		Card card = null;
		for (int i = 0; i < this.cardHand.size(); i++) {
			card = this.cardHand.get(i);
			if (card.getColor() == null || card.getColor().equals(this.lastCardPlayed.getColor())
					|| card.getNumber() == this.lastCardPlayed.getNumber()) {
				playableCard.add(i);
			}
		}
		if (playableCard.isEmpty()) {
			if (!hasDrawnFirstCard) {
				drawCard();
				promptingAction(true);
			}
			else {
				this.gameManager.endCurrentPlayerTurn();
			}
		}
		else {
			int i = 0;
			while (i < playableCard.size()) {
				card = this.cardHand.get(playableCard.get(i));
				if (card.getColor() == null || card.getColor().equals(this.lastCardPlayed.getColor())
						|| card.getNumber() == this.lastCardPlayed.getNumber()) {
					this.playCard(playableCard.get(i));
					break;
				}
				i++;
			}
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
	
	public void playCard(int cardIndex) {
		String[] colors = {Card.RED, Card.GREEN, Card.BLUE, Card.YELLOW};
		String chosenColor = colors[(int) (Math.random() * 4)];
		Card card = null;
		if (this.cardHand.get(cardIndex).toString().startsWith("Wild")) {
			if (this.cardHand.get(cardIndex).toString().startsWith("Wild: +4")) {
				card = CardFactory.getInstance().createColoredWildDraw4Card(chosenColor);
			}
			else {
				card = CardFactory.getInstance().createColoredWildCard(chosenColor);
			}
			System.out.println(name + ":: played " + this.cardHand.get(cardIndex) + " = " + chosenColor);
			removeCardFromHand(this.cardHand.get(cardIndex));
			card.play(gameManager);
			this.gameManager.endCurrentPlayerTurn();
		}
		else {
			System.out.println(name + ":: played " + this.cardHand.get(cardIndex));
			card = this.cardHand.get(cardIndex);
			removeCardFromHand(card);
			card.play(this.gameManager);
		}
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
	
	public String getPlayerName() {
		return this.name;
	}
	
	public int getPlayerHandSize() {
		return this.cardHand.size();
	}

	@Override
	public List<Card> getPlayerHand() {
		return this.cardHand;
	}

	@Override
	public void endTurn() {
		this.isTakingTurn = false;
	}

	@Override
	public boolean isTakingTurn() {
		return isTakingTurn;
	}

}
