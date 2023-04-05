package structure.card;

public class DrawDecorator extends FunctionDecorator{
	
	private int drawCard;

	public DrawDecorator(Card card, int drawCard) {
		super(card);
		this.drawCard = drawCard;
	}
	
	@Override
	public String toString() {
		return "Draw" + super.toString();
	}
	
	@Override
	public void playCard() {
		System.out.println("Set next player action to draw " + drawCard + " cards");
		super.playCard();
	}

}
