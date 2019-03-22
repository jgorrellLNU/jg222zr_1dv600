package hangman;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

class RoundClassTestArea {

	Random rand = new Random();

	Round round = null;
	File fakeData = new File("fakeData.txt");
	File quitFile = new File("quit.txt");

	/*
	 * Round Class Tests
	 */

	/*
	 * computeLengthMinusWhiteSpace() Tests
	 * 
	 * Two tests that test two extremes
	 * 
	 * First tests very short strings Second tests very long strings
	 * 
	 */

	@Test
	void testComputeLengthMinusWhiteSpaceShortAndLongWords() {

		/*
		 * Tests strings of length 3
		 */

		Random rand = new Random();

		for (int i = 0; i < 10000; i++) { // Run test 10000 times

			StringBuilder word = new StringBuilder("");
			int whiteChars = 0;

			for (int k = 0; k < 3; k++) { // Calculate whitespace in strings of length 3

				int charIntValue = rand.nextInt(40) + 95;
				char insert = ' ';

				// Determine what is inserted
				if (charIntValue >= 97 && charIntValue <= 122) // If the value is in this range, insert corresponding
																// letter
					insert = (char) charIntValue; // Else, insert space
				else
					whiteChars++;

				word.append(insert);

			}

			round = new Round(word.toString(), fakeDataScanSetup());

			// Compare Method to the the length of the 'word' minus the amount of whitespace
			// characters
			assertTrue(word.toString().length() - whiteChars == round.computeLengthMinusWhitespace());

		}
		
		/*
		 * Test strings of length 300
		 */

		for (int i = 0; i < 10000; i++) { // Run test 10000 times

			StringBuilder word = new StringBuilder("");
			int whiteChars = 0;

			for (int k = 0; k < 300; k++) { // Calculate whitespace in strings of length 300

				int charIntValue = rand.nextInt(40) + 95;
				char insert = ' ';

				// Determine what is inserted
				if (charIntValue >= 97 && charIntValue <= 122) // If the value is in this range, insert corresponding
																// letter
					insert = (char) charIntValue; // Else, insert space
				else
					whiteChars++;

				word.append(insert);

			}

			round = new Round(word.toString(), fakeDataScanSetup());

			// Compare Method to the the length of the 'word' minus the amount of whitespace
			// characters
			assertTrue(word.toString().length() - whiteChars == round.computeLengthMinusWhitespace());

		}

	}

	/*
	 * validateGuessedLetter() Test
	 * 
	 *  First tests loops through the invalid 
	 *  lines entries in fakeData.txt until 
	 *  the last line of the file. There it should find 'q',
	 *  a valid input, and the method should return true.
	 *  
	 *  Second test loops through quit.txt until 
	 *  the last line of the file. There it should find 'quit',
	 *  the way to make the method return false (and in the whole
	 *  program quit the round).
	 * 
	 * 
	 */

	@Test
	void testValidateGuessedLetterCorrectEntries() {

		round = new Round("a", fakeDataScanSetup());
		
		assertTrue(round.validateGuessedLetter());

		
	}
	
	@Test
	void testValidateGuessedLetterQuit() {
		
		round = new Round("a", quitScanSetup());
		
		assertFalse(round.validateGuessedLetter());
		
	}

	
	 //||||||||||||\\
	// Help Methods \\
//	\\||||||||||||||//
	
	Scanner fakeDataScanSetup() {
		
		Scanner returnedScanner = null;
		
		try {
			returnedScanner = new Scanner(fakeData);
		}
		catch (FileNotFoundException e) {
			System.out.println("fakeData File not found");
		}
		
		return returnedScanner;
		
	}
	
	Scanner quitScanSetup() {
		Scanner returnedScanner = null;
		
		try {
			returnedScanner = new Scanner(quitFile);
		}
		catch (FileNotFoundException e) {
			System.out.println("quitFile not found");
		}
		
		return returnedScanner;
		
	}
	
}
