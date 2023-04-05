package structure;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import structure.player.HumanPlayer;
import structure.player.Player;

public class Game {
	private Deck deck;
	private boolean isPlaying;
	private List<Player> players;
	private boolean normalOrder;
	private int currentPlayerIndex;

	public Game(int numPlayers) {
		super();
		this.deck = new Deck();
		this.isPlaying = false;
		this.normalOrder = true;
		Scanner input = new Scanner(System.in);
		this.players = new ArrayList<>();
		for (int i = 0; i < numPlayers; i++)
			this.players.add(new HumanPlayer("Player " + i, input));
	}
	
	public void startGame() {
		currentPlayerIndex = -1;
		deck.generateDeck();
		for (int i = 0; i < 7; i++)
			for (int j = 0; j < players.size(); j++)
				deck.drawCard(this.players.get(j));
		isPlaying = true;
		updateGame();
	}
	
	public Player nextPlayer() {
		this.currentPlayerIndex += this.normalOrder ? 1 : -1;
		if (this.currentPlayerIndex < 0)
			this.currentPlayerIndex = this.players.size() - 1;
		if (this.currentPlayerIndex > this.players.size() - 1)
			this.currentPlayerIndex = 0;
		return this.players.get(this.currentPlayerIndex);
	}
	
	private void updateGame() {
		Player p = nextPlayer();
		while (isPlaying) {
			p.takeTurn();
			p = nextPlayer();
		}
		System.out.println("Out of while");
	}
	
	public static void main(String[] args) {
		new Game(4).startGame();
	}
	
	
}
