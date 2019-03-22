package hangman;

import java.io.File;
import java.util.Scanner;


public class NormalLivesGame extends LivesGame {
	
	public NormalLivesGame(Scanner scan, User user) {
		super(3, "Normal Mode", scan, user);
		
		fillWordBank(new File("normalWords.txt"));
		
	}
	
}
