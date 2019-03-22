package hangman;

import java.io.IOException;
import java.util.ArrayList;

public class User {

	private String username;
	private int hardGamesPlayed = 0;
	private int normalGamesPlayed = 0;
	private int totalGamesPlayed = 0;
	private int hardMostWordsSolvedOneGame = 0;
	private int normalMostWordsSolvedOneGame = 0;
	private int wordsSolvedAllTime = 0;
	private int numTimesHanged = 0;
	
	public User (String username, ArrayList<String> existingNames) throws IOException {
		if (isValidUsername(username, existingNames))
			this.username = username;
		else
			throw new IOException("*Only letters are valid in a username*");
	}
	
	// Increment-ers \\
	
	protected void incrementHardGamesPlayed() { hardGamesPlayed++; }
	protected void incrementNormalGamesPlayed() { normalGamesPlayed++; }
	protected void incrementTotalGamesPlayed() { totalGamesPlayed++; }
	protected void incrementHardMostWordsSolvedOneGame() { hardMostWordsSolvedOneGame++; }
	protected void incrementNormalMostWordsSolvedOneGame() { normalMostWordsSolvedOneGame++; }
	protected void incrementWordsSolvedAllTime() { wordsSolvedAllTime++; }
	protected void incrementNumTimesHanged() { numTimesHanged++; }
	
	// Setters \\
	
	void setHardMostWordsSolvedOneGame(int n) {
		hardMostWordsSolvedOneGame = n;
	}

	void setNormalMostWordsSolvedOneGame(int n) {
		normalMostWordsSolvedOneGame = n;
	}
	
	// Getters \\
	
	public String getUserName() {
		return new String(username);
	}

	protected int getHardGamesPlayed() {
		return hardGamesPlayed;
	}

	protected int getNormalGamesPlayed() {
		return normalGamesPlayed;
	}

	protected int getTotalGamesPlayed() {
		return totalGamesPlayed;
	}

	protected int getHardMostWordsSolvedOneGame() {
		return hardMostWordsSolvedOneGame;
	}

	protected int getNormalMostWordsSolvedOneGame() {
		return normalMostWordsSolvedOneGame;
	}

	protected int getWordsSolvedAllTime() {
		return wordsSolvedAllTime;
	}

	protected int getNumTimesHanged() {
		return numTimesHanged;
	}

	// ToString \\
	
	@Override
	public String toString() {
		return username + " | Words Solved All Time: " + wordsSolvedAllTime + " | Total Games Played: " + totalGamesPlayed +
			   " | Normal Games Played: " + normalGamesPlayed + " | Hard Games Played: " + hardGamesPlayed +
			   " | Normal Most Words Solved One Game: " + normalMostWordsSolvedOneGame + " | Hard Most Words Solved One Game: " +
			   hardMostWordsSolvedOneGame + " | Number of Times Hanged: " + numTimesHanged + " ";
	}
	
	// Validator
	
	boolean isValidUsername(String name, ArrayList<String> existingNames) {
		
		if (existingNames.contains(name))
			return false;
		else if (name.length() == 0)
			return false;
		else if (name.length() > 20)
			return false;
		
		char[] usernameChars = name.toCharArray();
		
		for (char c : usernameChars) {
			
			if (!Character.isLetter(c))
				return false;
			
		}
		
		return true;
		
	}
	
}










