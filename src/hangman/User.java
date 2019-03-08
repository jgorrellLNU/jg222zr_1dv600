package hangman;

import java.io.IOException;

public class User {

	private String username;
	private int hardGamesPlayed = 0;
	private int normalGamesPlayed = 0;
	private int totalGamesPlayed = 0;
	private int hardMostWordsSolvedOneGame = 0;
	private int normalMostWordsSolvedOneGame = 0;
	private int wordsSolvedAllTime = 0;
	private int numTimesHanged = 0;
	
	public User (String username) throws IOException {
		if (isValidUsername(username))
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
		return username + " | Words Solved All Time: " + wordsSolvedAllTime;
	}
	
	// Validator
	
	boolean isValidUsername(String name) {
		
		if (name.length() == 0)
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










