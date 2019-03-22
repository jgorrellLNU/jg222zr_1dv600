package hangman;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.Test;

class UserClassTestArea {

	Random rand = new Random();
	User user = initializeUser();
	ArrayList<String> emptyList = new ArrayList<String>();
	
	/*
	 * 	Test valid username
	 * 
	 * 	A username is INVALID if:
	 * 		
	 * 		- It has length 0
	 * 		- Has a length > 20
	 * 		- Has characters other than letters
	 * 
	 * 	There will be a test for each of these scenarios
	 * 	as well as one that verifies a valid username is
	 * 	indeed valid.
	 */
	
	@Test
	void testLengthZeroUsername() {	// Test #1
		
		for (int i = 0; i < 10000; i++)
			assertFalse(user.isValidUsername(randomLetterString(0), emptyList));
		
	}
	
	@Test
	void testLengthOver20Username() { // Test #2
		
		for (int i = 0; i < 10000; i++)
			assertFalse(user.isValidUsername(randomLetterString(rand.nextInt(20) + 21), emptyList)); // Random length between 21-40
		
	}
	
	@Test
	void testInvalidCharsUsername() {	// Test #3
		
		for (int i = 0; i < 10000; i++) 
			assertFalse(user.isValidUsername(randomNonLetterString(rand.nextInt(20) + 1), emptyList));	// Random non letter string between 1-20 length
		
	}
	
	@Test
	void testValidUsername() {	// Test #4
		
		for (int i = 0; i < 10000; i++)
			assertTrue(user.isValidUsername(randomLetterString(rand.nextInt(20) + 1), emptyList));
		
	}
	
	// Help Method \\
	
	String randomLetterString(int length) {
		
		StringBuilder string = new StringBuilder("");
		
		for (int i = 0; i < length; i++) {
			string.append(getRandomLetter());
		}
		
		return string.toString();
		
	}
	
	String randomNonLetterString(int length) {
		
		StringBuilder string = new StringBuilder("");
		
		for (int i = 0; i < length; i++) {
			string.append(getRandomNonLetter());
		}
		
		return string.toString();
		
	}
	
	String getRandomLetter() {
		
		int charIntValue = rand.nextInt(26) + 97;
		char letter = (char) charIntValue;
		
		return letter + "";
	}
	
	String getRandomNonLetter() {
		
		int charIntValue = rand.nextInt(65);
		char character = (char) charIntValue;
		
		return character + "";
		
	}
	
	User initializeUser() {
		
		User returnedUser = null;
		ArrayList<String> emptyList = new ArrayList<String>();
		
		try {
			returnedUser = new User("Test", emptyList);
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		return returnedUser;
		
	}

}
