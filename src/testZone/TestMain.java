package testZone;

import java.util.Random;
import java.util.Scanner;

import hangman.Round;

public class TestMain {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.println("Welcome to Hangman!\n");
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
				
				Round round = new Round(wordBank[randomIndex], sc);
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
				System.out.println("\nThanks for playing!");
				System.exit(-1);

			} else {
				
				while (!(userChoice.equals("1") || userChoice.equals("2"))) {
					System.out.print("Invalid input. Try again ('1' for play, '2' to quit) -> ");
					userChoice = sc.nextLine();
				}
			}

		}

	}

}
