package structure.card;

public class WildDecorator extends FunctionDecorator{

	public WildDecorator(Card card) {
		super(card);
	}
	
	@Override
	public String toString() {
		return "Wild" + super.toString();
	}
	
	@Override
	public void playCard() {
		System.out.println("Prompt user to choose color to set.");
		super.playCard();
	}

}
