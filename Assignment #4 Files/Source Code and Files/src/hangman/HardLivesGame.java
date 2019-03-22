package hangman;

import java.io.File;
import java.util.Scanner;

public class HardLivesGame extends LivesGame {
	
	public HardLivesGame(Scanner scan, User user) {
		super(3, "Hard Mode", scan, user);
		
		fillWordBank(new File("hardWords.txt"));
		
	}
	
}
