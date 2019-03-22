package play;

import java.io.IOException;
import java.util.Scanner;

import hangman.*;

public class PlayGame {
	
	public static void main(String[] args) {

		// Main Variables

		Scanner sc = new Scanner(System.in);
		UsernameDataHandler usdh = new UsernameDataHandler();
		User user = null;

		/*
		 * User Set-up and Log-in
		 * 
		 */

		System.out.println("Welcome to Beyond Hangman!\n");

		// Log-in Variables

		boolean empty = usdh.displayUsernameList(); // Returns true if list is empty
		boolean secondTry = false; // Says if the user has failed their first attempt at log-in
		String usernameChoice = "";

		// Big ass log-in Loop
		
		while (true) {

			if (!empty && !usernameChoice.equals("new")) {// Ask for username number assuming list is not empty
				System.out.print("\nType the number of your username or type 'new' to register a new username -> ");
				usernameChoice = sc.nextLine();
			}

			if (usernameChoice.toLowerCase().equals("new") || empty) { // Register new user
				System.out.print("\nRegister a new username (letters only) -> ");
				usernameChoice = sc.nextLine();

				while (true) {

					try {
						if (secondTry) {						// Second try
							System.out.print("Try again -> ");
							usernameChoice = sc.nextLine();
						}

						user = new User(usernameChoice, usdh.getUsernames());
						usdh.registerUser(user);			// Register
						break;
					} catch (IOException e) {
						System.out.println(e.getMessage());
						secondTry = true;
					}

				}

				break;
				
			} else { // Returning user (or invalid input)

				try {
					int userIndex = Integer.parseInt(usernameChoice) - 1;
					user = usdh.getUser(userIndex);
					break;
				} catch (NumberFormatException e) {
					System.out.println("Invalid input. Try again.\n");
					System.out.print("Type the number of your username or type 'new' to register a new username -> ");
					usernameChoice = sc.nextLine();
				} catch (IOException e) {
					break;	// This catch is impossible (in theory)
				}

			}

		}


		/* * * * * * * * * * * * * * * * * * * * * * * * \
		 * Logged in, choose gameMode/leaderboard/quit
		 * 
		 * * * * * * * * * * * * * * * * * * * * * * * * */

		System.out.println("\nWelcome to Hangman, " + user.getUserName() + "!\n");
		displayMenu();
		
		String userChoice = sc.nextLine();

		while (true) {

			// Normal Mode
			
			if (userChoice.equals("1")) {	
				
				NormalLivesGame game = new NormalLivesGame(sc, user);
				game.playGame();

				userChoice = playAgain("1", sc);	// Game Over, play Again?
				
/////////
			// Hard Mode
				
			} else if (userChoice.equals("2")) {	
				
				HardLivesGame game = new HardLivesGame(sc, user);
				game.playGame();
				
				userChoice = playAgain("2", sc);	// Game Over, play Again?
				
				
			// Multi-player Mode
				
			} else if (userChoice.equals("3")) {
			
				MultiplayerGame game = new MultiplayerGame(sc);
				game.playGame();
				
				userChoice = playAgain("3", sc);
				
				// Quit Game Confirmation Section
				
			} else if (userChoice.equals("4")) {
				usdh.save(user);
				
				usdh.displayUserStats(user);
				System.out.println();
				
				displayMenu();
				userChoice = sc.nextLine();
				
			} else if (userChoice.equals("5")) {	
				
				quitConfirmation(user, sc, usdh);
				
				
				// Invalid Input Section

			} else {	
				
				while (!(userChoice.equals("1") || userChoice.equals("2") || userChoice.equals("3") || userChoice.equals("4") || userChoice.equals("5"))) {
					System.out.print("Invalid input. Try again ('1' (normal), '2' (hard), or '3' (multiplayer) for play, '4' for user stats, '5' to quit) -> ");
					userChoice = sc.nextLine();
				}
				
			}
			
		}
		
	}

	  //||||||||||||||||||||\\
	 //						 \\
	// Private Helper Methods \\
//	\\						  //
	
	private static String playAgain(String option, Scanner sc) {
		System.out.print("\n\nWould you like to start another game? 'yes' or 'no' -> ");
		String playAgain = sc.nextLine();
		String userChoice = "fix me";
		
		if (playAgain.toLowerCase().equals("yes"))
			userChoice = option;
		else if (playAgain.toLowerCase().equals("no")) {
			System.out.println();
			displayMenu();
			userChoice = sc.nextLine();
		} else
			userChoice = "fix me"; // Sends to invalid input section.
		
		return userChoice;
	}
	
	
	private static String quitConfirmation(User user, Scanner sc, UsernameDataHandler usdh) {
		System.out.print("\nAre you sure you want to quit? 'yes' or 'no' -> ");
		String confirm = sc.nextLine();
		String userChoice = "fix me";
		
		
		if (confirm.toLowerCase().contentEquals("yes")) {
			System.out.println("\nThanks for playing, " + user.getUserName() + "!");
			sc.close();
			usdh.save(user);
			System.exit(-1);
		} else {
			System.out.println();
			displayMenu();
			
			userChoice = sc.nextLine();
		}
		
		return userChoice;
		
	}
	
	private static void displayMenu() {
		System.out.println("[1] Play Normal Mode");
		System.out.println("[2] Play  Hard  Mode");
		System.out.println("[3] Play Multiplayer");
		System.out.println("[4] Your Stats");
		System.out.println("[5] Quit ");
		System.out.print("> ");
	}

}
