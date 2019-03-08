package hangman;

import java.util.Scanner;

public class Round {

	// Fields
	
	private String word;										// Word to Solve
	private User user;											// To Save User Stats
	private Scanner scan;										// For User Input
	private StringBuilder guessed = new StringBuilder("");		// Keep track of guessed letters
	private String letterGuess = " ";								// Initial variable for guessing letters
	private int mistakes = 0;						// Tracks number of mistakes
	private int[] reveal;										// Tracks whether a letter should be hidden or revealed when printing
	private int wordLengthMinusWhitespace;						// For determining when a player has won the round.
	private boolean roundOver = false;							// Clear
	private final int TRY_LIMIT = 5;							// Number of tries before losing
	
	
	// Constructors
	
	public Round (String word, Scanner scan, User user) {
		this.word = word;
		this.scan = scan;
		this.user = user;
		this.reveal = new int[word.length()];
		wordLengthMinusWhitespace = computeLengthMinusWhitespace();
	}
	
	public Round (String word, Scanner scan) {
		this(word, scan, null);
	}
	
	// 
	
	public void play() {
		
		makeSpace(3);
		
		// Initial Printing
		printBoard();
		printInitialWord();
		
		while (!roundOver) {	// Main Stuff happens here
			
			roundOver = modifyAndPrintLetters();
			
		}
		
		if (mistakes == TRY_LIMIT) {
			System.out.println("\n\nSorry, but ya ded.");
			System.out.println("The word was \"" + word.toUpperCase() + "\".");
			
		} else if (letterGuess.toLowerCase().equals("quit")) {
			System.out.println("***QUIT Successful***");
		} else {
			System.out.println("\n\nCongratulations, you won the round!");
		}
		
	}
	
	private void printInitialWord() {
		
		for (int i = 0; i < word.length(); i++) {
			
			if (word.charAt(i) != ' ') {
				System.out.print("_ ");
			} else {
				System.out.print("   ");
			}
			
		}
		
	}
	
	int computeLengthMinusWhitespace() {			// Changed access modifier for JUnit test
		int lengthMinusWhite = word.length();
		
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == ' ') {
				lengthMinusWhite--;
			}
		}
		
		return lengthMinusWhite;
		
	}
	
	private boolean modifyAndPrintLetters() {
		
		boolean containsLetter = false;
		
		printGuessedLetters();
		
		makeSpace(5);
		
		System.out.println("\n\n('quit' to end)");
		System.out.print("Guess a letter (a-z) -> ");
		
		letterGuess = scan.nextLine();
		
		// Validate Letter and check if user wants to quit
		boolean quit = validateGuessedLetter();
		
		// Quit Confirmation
		
		if (!quit) {
			System.out.print("Are you sure you want to quit? ('yes' or 'no') -> ");
			String confirmQuit = scan.nextLine();
			
			if (confirmQuit.toLowerCase().equals("yes")) {
				return true;	// roundOver == true
			} else {
				return false; 	// keep playing
			}
	
		}
		
		// Letter added to Guessed Letters
		guessed.append(letterGuess.toLowerCase());
		
		// Adjust reveal[] if letter guess is correct
		
		for (int i = 0; i < word.length(); i++) {
			
			if (Character.toLowerCase(letterGuess.charAt(0)) == Character.toLowerCase(word.charAt(i))) {
				reveal[i]++;
				containsLetter = true;
			}
			
		}
		
		if (!containsLetter) {	// Letter is not in the word
			mistakes++;
		}
		
		//
		
		makeSpace(10);
		
		// Print Updated Board
		System.out.println("\n");
		printBoard();
		
		// Updated Word
		for (int i = 0; i < word.length(); i++) {
			
			if (word.charAt(i) != ' ' && reveal[i] == 0) {
				System.out.print("_ ");
			} else if (word.charAt(i) == ' ') {
				System.out.print("   ");
			} else {
				System.out.print(Character.toUpperCase(word.charAt(i)) + " ");
			}
			
		}
		
		int sumOfReveal = 0;	// Sum of all reveal[] elements
		
		for (int i = 0; i < reveal.length; i++) {
			sumOfReveal += reveal[i];
		}
		
		if (sumOfReveal == wordLengthMinusWhitespace) {	// Word Completed
			incrementTotalWordsSolved();
			return true;
			
		} else if (mistakes == TRY_LIMIT) {	// Round Lost
			incrementNumOfTimesHanged();
			return true;
			
		}
		
		return false;	// Keep playing
		
	}
	
	private void printGuessedLetters() {
		
		System.out.print("\n\nGuessed Letters: ");
		for (int i = 0; i < guessed.length(); i++) {
			
			if (i == guessed.length() - 1) {
				System.out.print(guessed.charAt(i));
			} else {
				System.out.print(guessed.charAt(i) + ", ");
			}
			
		}
		
	}
	
	boolean validateGuessedLetter() {		// Changed access modifier for JUnit test

		while (letterGuess.length() != 1 || !(Character.isLetter(letterGuess.charAt(0))) || guessed.toString().contains(letterGuess)) {

			if (letterGuess.toLowerCase().equals("quit")) {
				return false;

			} else if (letterGuess.length() != 1 || !(Character.isLetter(letterGuess.charAt(0)))) {
				System.out.print("You didn't enter a valid option. Try again -> ");
				letterGuess = scan.nextLine();

			} else if (guessed.toString().contains(letterGuess)) {
				System.out.print("You've already guessed this letter. Try again -> ");
				letterGuess = scan.nextLine();

			}

		}
		
		return true;

	}
	
	private void makeSpace(int lines) {
		
		for (int i = 0; i < lines; i++)
			System.out.println();
		
	}
	
	private void printBoard() {
		
		if (mistakes == 0) {
			
			// No Mistakes \\
			
			System.out.println("      =========");
			System.out.println("       |      |");
			System.out.println("              |");
			System.out.println("              |");
			System.out.println("              |");
			System.out.println("              |");
			System.out.println("===============\n");
		
		} else if (mistakes == 1) {
		
			// One Mistake \\
			
			System.out.println("      =========");
			System.out.println("       |      |");
			System.out.println("       O      |");
			System.out.println("              |");
			System.out.println("              |");
			System.out.println("              |");
			System.out.println("===============");
		
		} else if (mistakes == 2) {
		
			// Two Mistakes \\
			
			System.out.println("      =========");
			System.out.println("       |      |");
			System.out.println("       O      |");
			System.out.println("      | |     |");
			System.out.println("              |");
			System.out.println("              |");
			System.out.println("===============");
		
		} else if (mistakes == 3) {
		
			// Three Mistakes \\
			
			System.out.println("      =========");
			System.out.println("       |      |");
			System.out.println("       O      |");
			System.out.println("     -| |-    |");
			System.out.println("              |");
			System.out.println("              |");
			System.out.println("===============");
		
		} else if (mistakes == 4) {
		
			// Four Mistakes \\
			
			System.out.println("      =========");
			System.out.println("       |      |");
			System.out.println("       O      |");
			System.out.println("     -| |-    |");
			System.out.println("      /       |");
			System.out.println("              |");
			System.out.println("===============");
		
		} else if (mistakes == 5) {
		
			// Five Mistakes \\
			
			System.out.println("      =========");
			System.out.println("       |      |");
			System.out.println("       O      |");
			System.out.println("     -| |-    |");
			System.out.println("      / \\     |");
			System.out.println("      DEAD    |");
			System.out.println("===============");
		
		}
		
		System.out.println();
		
	}
	
	// User Handling Methods
	
	private void incrementTotalWordsSolved() {
		if (user != null)
			user.incrementWordsSolvedAllTime();
	}
	
	private void incrementNumOfTimesHanged() {
		if (user != null)
			user.incrementNumTimesHanged();
	}
	
}









