package hangman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class LivesGame extends Game {

	protected int lives;
	protected ArrayList<String> wordBank = new ArrayList<String>();
	protected Scanner scan;
	protected User user;
	protected Random rand = new Random();
	protected int roundNumber = 1;
	protected int wordsSolved = 0;
	protected String difficulty;
	
	public LivesGame(int lives, String difficulty, Scanner scan, User user) {
		this.lives = lives;
		this.scan = scan;
		this.difficulty = difficulty;
		this.user = user;
	}
	
	// Methods \\
	
	public void playGame() {
		
		printStats();
		
		while (lives > 0) {
			
			// Start New Round
			Round round = new Round(getWord(), scan, user);
			int win = round.play();
			
			if (win == 1)
				lives--;
			else if (win == 2)
				break;
			else if (win == 3)
				wordsSolved++;
			
			roundNumber++;
			
			if (lives == 0) {
				System.out.println("\nGame Over! Words Solved: " + wordsSolved);
				
				if (this instanceof NormalLivesGame) {
					user.incrementNormalGamesPlayed();
					
					if (wordsSolved > user.getNormalMostWordsSolvedOneGame()) {
						user.setNormalMostWordsSolvedOneGame(wordsSolved);
					}
					
				} else if (this instanceof HardLivesGame) {
					user.incrementHardGamesPlayed();
					
					if (wordsSolved > user.getHardMostWordsSolvedOneGame()) {
						user.setHardMostWordsSolvedOneGame(wordsSolved);
					}
					
				}
				
			} else
				printStats();
		}
		
	}
	
	protected void fillWordBank(File file) {
		
		try {
			Scanner fileScan = new Scanner(file);
			
			while (fileScan.hasNext()) {
				String word = fileScan.nextLine();
				
				if (Game.verifyWord(word))
					wordBank.add(word);
			}
			
			fileScan.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	protected String getWord() {
		String word = wordBank.get(rand.nextInt(wordBank.size()));	// Get random word
		word = word.trim();											// Ensure no extra spaces are on the String
		return word;
	}
	
	private void printStats() {
		System.out.println("\n\n\n" + difficulty + ": Round #" + roundNumber + " | Lives Left: " + lives + " | Words Solved: " + wordsSolved);
	}
	
}










