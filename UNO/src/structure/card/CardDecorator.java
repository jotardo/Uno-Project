package structure.card;

public abstract class CardDecorator implements Card{
	
	protected Card card;
	
	public CardDecorator(Card card) {
		this.card = card;
	}
	
	@Override
	public String getColor() {
		return card.getColor();
	}
	
	@Override
	public int getNumber() {
		return card.getNumber();
	}
	
	@Override
	public String toString() {
		return "(" + card.toString() + ")";
	}
	
	@Override
	public void playCard() {
		card.playCard();
	}
}
