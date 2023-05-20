package com.jotard.gui.card;

import com.jotard.structure.card.Card;

public class CardButtonFactory {

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

	public CardButton createWildCard() {
		return new WildCardButton(false);
	}

	public CardButton createWildDraw4Card() {
		return new WildCardButton(true);
	}
	
	public CardButton createCard(Card c) {
		String s = c.toString();
		String[] sArr = null;
		CardButton result;
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
				result = s.startsWith("+4") ? createWildDraw4Card() : createWildCard();
				if (c.getColor() != null)
					result.setColor(c.getColor());
				return result;
			}
		}
		return null;
	}

}
