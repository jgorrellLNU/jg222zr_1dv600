package hangman;

import java.util.ArrayList;
import java.util.Scanner;

public class LivesGame extends Game {

	protected int lives;
	protected ArrayList<String> wordBank;
	private Scanner scan;
	
	public LivesGame(int lives, ArrayList<String> wordBank, Scanner scan) {
		this.lives = lives;
		this.wordBank = wordBank;
		this.scan = scan;
	}
	
}
