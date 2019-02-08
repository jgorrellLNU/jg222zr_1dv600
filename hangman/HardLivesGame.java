package hangman;

import java.util.ArrayList;
import java.util.Scanner;

public class HardLivesGame extends LivesGame {

	private Scanner scan;
	
	public HardLivesGame(Scanner scan) {
		super(3, new ArrayList<String>(), scan);	// Second parameter will be updated and hardcoded
	}
	
}
