package hangman;

import java.util.ArrayList;
import java.util.Scanner;

public class NormalLivesGame extends LivesGame {

	private Scanner scan;
	
	public NormalLivesGame(Scanner scan) {
		super(3, new ArrayList<String>(), scan);	// Second parameter will be updated and hardcoded
	}
	
}
