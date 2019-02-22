package hangman;

public class User {

	private String userName;
	private int hardGamesPlayed = 0;
	private int normalGamesPlayed = 0;
	private int totalGamesPlayed = 0;
	private int hardMostWordsSolvedOneGame = 0;
	private int normalMostWordsSolvedOneGame = 0;
	private int wordsSolvedAllTime = 0;
	private int numTimesHanged = 0;
	
	public User (String userName) {
		this.userName = userName;
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
	
	protected String getUserName() {
		return new String(userName);
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

	
	
}
