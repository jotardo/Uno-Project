package structure.card;

public class ReverseDecorator extends FunctionDecorator{

	public ReverseDecorator(Card card) {
		super(card);
	}
	
	@Override
	public String toString() {
		return "Reverse" + super.toString();
	}
	
	@Override
	public void playCard() {
		System.out.println("Reverse the direction of turns");
		super.playCard();
	}
	
}
