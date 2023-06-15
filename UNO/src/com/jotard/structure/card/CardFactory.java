package com.jotard.structure.card;

public class CardFactory {
	
	private static CardFactory instance;
	
	private CardFactory() {}
	
	public static CardFactory getInstance() {
		if (instance == null)
			synchronized (CardFactory.class) {
				if (instance == null)
					instance = new CardFactory();
			}
		return instance;
	}

	public Card createNormalCard(String c, int i) {
		return new NormalCard(c, i);
	}

	public Card createBanCard(String c) {
		return new ActionCard(c, ActionCard.BAN);
	}

	public Card createDrawTwoCard(String c) {
		return new ActionCard(c, ActionCard.DRAW_TWO);
	}

	public Card createReverseCard(String c) {
		return new ActionCard(c, ActionCard.REVERSE);
	}

	public Card createWildCard() {
		return new WildCard(false);
	}

	public Card createWildDraw4Card() {
		return new WildCard(true);
	}
	
	public Card createColoredWildCard(String c) {
		return new WildCard(false, c);
	}

	public Card createColoredWildDraw4Card(String c) {
		return new WildCard(true, c);
	}

}
