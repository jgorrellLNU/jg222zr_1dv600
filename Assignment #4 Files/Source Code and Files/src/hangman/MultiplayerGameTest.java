package hangman;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

class MultiplayerGameTest {

	@Test
	void testGameOverCheck() {

		MultiplayerGame game = new MultiplayerGame(new Scanner(System.in));

		// First Outcome
		game.roundNumber = 11;
		assertTrue(game.gameOverCheck());
		game.roundNumber = 0;

		// Second Outcome
		game.playerOnePoints = 5;
		game.playerTwoPoints = 5;
		assertTrue(game.gameOverCheck());

		// Third
		game.playerTwoPoints = 3;
		// playerOnePoints still == 5
		assertTrue(game.gameOverCheck());

		// Fourth
		game.playerTwoPoints = 5;
		game.playerOnePoints = 3;
		assertTrue(game.gameOverCheck());

		// Fifth
		game.playerOnePoints = 4;
		game.playerTwoPoints = 2;
		game.playerTwoTriesRemaining = 1;
		assertTrue(game.gameOverCheck());

		// Sixth
		game.playerTwoPoints = 4;
		game.playerOnePoints = 2;
		game.playerOneTriesRemaining = 1;
		assertTrue(game.gameOverCheck());

		// Game not over
		game.roundNumber = 6;
		game.playerOnePoints = 2;
		game.playerTwoPoints = 3;
		game.playerOneTriesRemaining = 3;
		game.playerTwoTriesRemaining = 2;
		assertFalse(game.gameOverCheck());

	}

	@Test
	void testDetermineOutcome() {

		/*
		 * Scenarios:
		 * 
		 * 1 - Player One Wins 2 - PLayer Two Wins 3 - Tie
		 * 
		 */

		MultiplayerGame game = new MultiplayerGame(new Scanner(System.in));

		// First Scenario (all scenarios in which player one beats player two)
		game.playerTwoPoints = 0;

		while (game.playerTwoPoints <= 4) {

			game.playerOnePoints = game.playerTwoPoints + 1;

			while (game.playerTwoPoints < game.playerOnePoints) {
				assertEquals(1, game.determineOutcome());
				game.playerTwoPoints++;
			}

			game.playerTwoPoints = game.playerOnePoints;

		}

		// Second Scenario (all scenarios in which player two beats player one)
		game.playerOnePoints = 0;

		while (game.playerOnePoints <= 4) {

			game.playerTwoPoints = game.playerOnePoints + 1;

			while (game.playerOnePoints < game.playerTwoPoints) {
				assertEquals(2, game.determineOutcome());
				game.playerOnePoints++;
			}

			game.playerOnePoints = game.playerTwoPoints;

		}
		
		// Third Scenario (all scenarios in which the players tie)
		game.playerOnePoints = 0;
		game.playerTwoPoints = 0;
		
		while (game.playerOnePoints <= 5 && game.playerTwoPoints <= 5) {
			assertEquals(3, game.determineOutcome());
			game.playerOnePoints++;
			game.playerTwoPoints++;
		}

	}

}
