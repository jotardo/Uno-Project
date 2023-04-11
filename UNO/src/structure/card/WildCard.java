package structure.card;

import structure.game.GameManager;

public class WildCard implements Card {

	private String color;
	private boolean drawFour;

	public WildCard(boolean drawFour) {
		this.drawFour = drawFour;
	}
	
	@Override
	public String toString() {
		return "Wild" + (this.drawFour ? " +4" : "") + (this.color == null ? "" : "-" + this.color);
	}

	@Override
	public String getColor() {
		return color;
	}

	@Override
	public int getNumber() {
		return -1;
	}

	@Override
	public void play(GameManager gm) {
		String value = "";
		System.out.print("Choose color: ");
		while (!(value.equals(Card.RED) || value.equals(Card.GREEN) || value.equals(Card.BLUE) || value.equals(Card.YELLOW))) {
			value = gm.getInput().nextLine();
			if (!(value.equals(Card.RED) || value.equals(Card.GREEN) || value.equals(Card.BLUE) || value.equals(Card.YELLOW))) {
				System.out.print("Incorrect input. Choose color: ");
			}
		}
		this.color = value;
		if (this.drawFour) {
			gm.getNextPlayer().setDrawWhenTurnStart(4);
			gm.getNextPlayer().setBanned(true);
		}
		gm.setLastPlayedCard(this);
	}
}
