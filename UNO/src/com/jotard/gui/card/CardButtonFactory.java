package com.jotard.gui.card;

import com.jotard.structure.card.Card;

public class CardButtonFactory {
	
	private static CardButtonFactory instance;
	
	private CardButtonFactory() {}
	
	public static CardButtonFactory getInstance() {
		if (instance == null)
			instance = new CardButtonFactory();
		return instance;
	}

	public CardButton createNormalCard(String c, int i) {
		return new NormalCardButton(c, i);
	}

	public CardButton createBanCard(String c) {
		return new ActionCardButton(c, ActionCardButton.BAN);
	}

	public CardButton createDrawTwoCard(String c) {
		return new ActionCardButton(c, ActionCardButton.DRAW_TWO);
	}

	public CardButton createReverseCard(String c) {
		return new ActionCardButton(c, ActionCardButton.REVERSE);
	}

	public CardButton createNormalWildCard() {
		return new WildCardButton(false, null);
	}

	public CardButton createNormalWildDraw4Card() {
		return new WildCardButton(true, null);
	}

	public CardButton createColoredWildCard(String c) {
		return new WildCardButton(false, c);
	}

	public CardButton createColoredWildDraw4Card(String c) {
		return new WildCardButton(true, c);
	}
	
	public CardButton createCard(Card c) {
		String s = c.toString();
		String[] sArr = null;
		CardButton result = null;
		if (c != null) {
			if (s.startsWith("Normal:")) {
				result = createNormalCard(c.getColor(), c.getNumber());
			}
			if (s.startsWith("Action:")) {
				s = s.substring(7);
				sArr = s.split(" ");
				if (sArr[1].equals("+2"))
					result = createDrawTwoCard(c.getColor());
				if (sArr[1].equals("Reverse"))
					result = createReverseCard(c.getColor());
				if (sArr[1].equals("Ban"))
					result = createBanCard(c.getColor());
			}
			if (s.startsWith("Wild:")) {
				s = s.substring(5);
				sArr = s.split("-");
				if (s.startsWith("+4"))
					result = c.getColor() != null ? createColoredWildDraw4Card(c.getColor()) : createNormalWildCard();
				else
					result = c.getColor() != null ? createColoredWildCard(c.getColor()) : createNormalWildCard();
			}
		}
		return result;
	}

}
