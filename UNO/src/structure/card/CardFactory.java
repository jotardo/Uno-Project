package structure.card;

public class CardFactory {
	
	private final Card emptyCard = new BaseCard();
	
	public Card createNormalCard(String color, int number) {
		return new NumberDecorator(new ColorDecorator(emptyCard, color), number);
	}
	
	public Card createBanCard(String color) {
		return new BanDecorator(new ColorDecorator(emptyCard, color));
	}
	
	public Card createDrawTwoCard(String color) {
		return new DrawDecorator(new ColorDecorator(emptyCard, color), 2);
	}
	
	public Card createReverseCard(String color) {
		return new ReverseDecorator(new ColorDecorator(emptyCard, color));
	}
	
	public Card createWildCard() {
		return new WildDecorator(emptyCard);
	}
	
	public Card createWildDraw4Card() {
		return new DrawDecorator(new WildDecorator(emptyCard), 4);
	}
}
