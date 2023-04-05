package structure.card;

public class ColorDecorator extends CardDecorator{
	
	private String color;

	public ColorDecorator(Card card, String color) {
		super(card);
		this.color = color;
	}
	
	@Override
	public String getColor() {
		return color;
	}
	
	@Override
	public String toString() {
		return color + super.toString();
	}
	
	@Override
	public void playCard() {
		System.out.println("Set last played card's color to " + color);
		super.playCard();
	}

}
