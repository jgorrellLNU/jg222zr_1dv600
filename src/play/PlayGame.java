package play;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import hangman.Round;
import hangman.User;
import hangman.UsernameDataHandler;

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

		System.out.println("Welcome to Hangman!\n");

		// Log-in Variables

		boolean empty = usdh.displayUsernameList(); // Returns true if list is empty
		boolean secondTry = false; // Says if the user has failed their first attempt at log-in
		String usernameChoice = "";

		// Big ass log-in Loop
		
		while (true) {

			if (!empty) {// Ask for username number assuming list is not empty
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

						user = new User(usernameChoice);
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

		
		
		usdh.save();

		/* * * * * * * * * * * * * * * * * * * * * * * * \
		 * Logged in, choose gameMode/leaderboard/quit
		 * 
		 * * * * * * * * * * * * * * * * * * * * * * * * */

		System.out.println("\nWelcome to Hangman, " + user.getUserName() + "!\n");
		System.out.println("[1] Play ");
		System.out.println("[2] Quit ");
		System.out.print("> ");
		
		String userChoice = sc.nextLine();

		while (true) {

			if (userChoice.equals("1")) {
			
				Random rand = new Random();
				
				/*
				 * Temporary implementation of selecting a word.
				 */
				
				String[] wordBank = {"Burger and Fries", "Linnaeus", "Software Technology"};
				int randomIndex = rand.nextInt(wordBank.length);
				
				Round round = new Round(wordBank[randomIndex], sc, user);
				round.play();

				System.out.print("\n\nWould you like to start another round? 'yes' or 'no' -> ");
				String playAgain = sc.nextLine();
				
				if (playAgain.toLowerCase().equals("yes"))
					userChoice = "1";
				else if (playAgain.toLowerCase().equals("no"))
					userChoice = "2";
				else
					userChoice = "fix me"; // Sends to invalid input section.
				
			} else if (userChoice.equals("2")) {
				System.out.print("\nAre you sure you want to quit? 'yes' or 'no' -> ");
				String confirm = sc.nextLine(); 
				
				if (confirm.toLowerCase().contentEquals("yes")) {
					System.out.println("\nThanks for playing, " + user.getUserName() + "!");
					sc.close();
					System.exit(-1);
				} else {
					System.out.println("\n[1] Play ");
					System.out.println("[2] Quit ");
					System.out.print("> ");
					
					userChoice = sc.nextLine();
				}

			} else {
				
				while (!(userChoice.equals("1") || userChoice.equals("2"))) {
					System.out.print("Invalid input. Try again ('1' for play, '2' to quit) -> ");
					userChoice = sc.nextLine();
				}
			}
			
		}
		
	}

}
