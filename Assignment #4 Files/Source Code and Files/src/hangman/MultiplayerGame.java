package hangman;

import java.util.Scanner;

public class MultiplayerGame extends Game {
	
	private Scanner scan;
	protected int roundNumber = 0;
	protected int playerOnePoints = 0;
	protected int playerTwoPoints = 0;
	protected int playerOneTriesRemaining = 5;
	protected int playerTwoTriesRemaining = 5;
	private boolean playerOneTurn = false;
	
	public MultiplayerGame (Scanner scan) {
		this.scan = scan;
	}
	
	public void playGame() {
		
		while (!gameOverCheck()) {	// Playing the game
			
			printStats();
			
			if (playerOneTurn) {
				System.out.println("Player #1's turn!\n");
			} else {
				System.out.println("Player #2's turn!\n");
			}
			
			
			// Start New Round
			Round round = new Round(getUserWord(), scan);
			makeSpace(30);
			int win = round.play();
			
			if (win == 1) // Lost Round
				;
			else if (win == 2)	// Quit Game
				break;
			else if (win == 3) {	// Won Round
				if (playerOneTurn)
					playerOnePoints++;
				else
					playerTwoPoints++;
			}
			
			// Switch turn
			if (playerOneTurn) {
				playerOneTurn = false;
				playerOneTriesRemaining--;
			} else {
				playerOneTurn = true;
				playerTwoTriesRemaining--;
			}
			
			roundNumber++;
			
		}
		
		// Results 
		
		int outcome = determineOutcome();
		
		System.out.println("\nPlayer #1 Score: " + playerOnePoints + " | Player #2 Score: " + playerTwoPoints);
		
		if (outcome == 1) 
			System.out.println("Player #1 wins!");
		else if (outcome == 2) 
			System.out.println("Player #2 wins!");
		else if (outcome == 3) 
			System.out.println("It's a tie!");
		
	
		
	}
	
	private String getUserWord() {
		
		String input = "";
		boolean validWordEntered = false;
		
		while (!validWordEntered) {
		
			if (playerOneTurn) {	// Player #2 enters a word
				System.out.print("Player #2, enter a word (don't let player #1 see) -> ");
				input = scan.nextLine();
			} else {	// Player #1 enters a word
				System.out.print("Player #1, enter a word (don't let player #2 see) -> ");
				input = scan.nextLine();
			}
			
			validWordEntered = Game.verifyWord(input);
			
			if (!validWordEntered) {	// Valid word not entered. Display instructions
				System.out.println("Invalid word/phrase. Words/phrases must be:");
				System.out.println("\t- At least five letters");
				System.out.println("\t- Have no more than one consecutive space");
				System.out.println("\t- Have no more than two consecutive same letters");
				System.out.println("\t- Be no longer than forty characters");
				System.out.println("\nTry again!\n");
				
			}
			
		}
		
		return input;
		
	}
	
	private void printStats() {
		System.out.println("\nRounds remaining: " + (10 - roundNumber) +
						   " | Player #1 Score: " + playerOnePoints + " | Player #2 Score: " + playerTwoPoints
						   + "\n");
	}
	
	protected boolean gameOverCheck() {
		
		if (roundNumber > 10) {	// All rounds played
			return true;
		} else if (playerOnePoints == 5 && playerTwoPoints == 5) {	// Tie
			return true;
		} else if (playerOnePoints == 5) {	// Player One Wins
			return true;
		} else if (playerTwoPoints == 5 && playerOnePoints < 4)	{// Player Two Wins
			return true;
		} else if (playerOnePoints > playerTwoPoints+playerTwoTriesRemaining) {// Player One Wins, out of reach
			return true;
		} else if (playerTwoPoints > playerOnePoints+playerOneTriesRemaining) {	// Player Two Wins, out of reach
			return true;
		}
			
		
		return false;
	}
	
	protected int determineOutcome() {
		
		/*
		 *  1 = Player One Wins
		 *  2 = Player Two Wins
		 *  3 = Tie
		 */
		
		int outcome = 0;
		
		
		if (playerOnePoints > playerTwoPoints)	// Player One Wins
			outcome = 1;
		else if (playerTwoPoints > playerOnePoints) // Player Two Wins
			outcome = 2;
		else if (playerOnePoints == playerTwoPoints)	// Tie
			outcome = 3;
		
		return outcome;
		
	}
	
	private void makeSpace(int spaces) {
		for (int i = 0; i < spaces; i++)
			System.out.println();
	}
	
}





