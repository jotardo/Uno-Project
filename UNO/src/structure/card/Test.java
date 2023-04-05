package structure.card;

public class Test {
	public static void main(String[] args) {
		CardFactory factory = new CardFactory();
		Card c1 = factory.createNormalCard("Blue", 5);
		c1.playCard();
		System.out.println();
		Card c2 = factory.createNormalCard("Yellow", 0);
		c2.playCard();
		System.out.println();
		Card c3 = factory.createDrawTwoCard("Red");
		c3.playCard();
		System.out.println();
		Card c4 = factory.createReverseCard("Yellow");
		c4.playCard();
		System.out.println();
		Card cWeird = new BanDecorator(factory.createWildDraw4Card());
		cWeird.playCard();
	}
}
