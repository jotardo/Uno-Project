package structure.card;

public class CardFactory {

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

}
