package structure.card;

public class BanDecorator extends FunctionDecorator{

	public BanDecorator(Card card) {
		super(card);
	}
	
	@Override
	public String toString() {
		return "Ban" + super.toString();
	}
	
	@Override
	public void playCard() {
		System.out.println("Set the game's next player to the one after the true next player");
		super.playCard();
	}

}
