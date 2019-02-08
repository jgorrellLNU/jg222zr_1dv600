package hangman;

public class Round {

	private String word;
	private User user;
	
	public Round (String word, User user) {
		this.word = word;
		this.user = user;
	}
	
	public Round (String word) {
		this.word = word;
		this.user = null;
	}
	
}
