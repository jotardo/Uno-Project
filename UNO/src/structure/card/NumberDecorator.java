package structure.card;

public class NumberDecorator extends CardDecorator{
	
	private int number;

	public NumberDecorator(Card card, int number) {
		super(card);
		this.number = number;
	}
	
	@Override
	public int getNumber() {
		return number;
	}
	
	@Override
	public String toString() {
		return number + super.toString();
	}
	
	@Override
	public void playCard() {
		System.out.println("Set last played card's number to " + number);
		super.playCard();
	}

}
