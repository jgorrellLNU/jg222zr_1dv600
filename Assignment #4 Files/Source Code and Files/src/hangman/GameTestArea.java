package hangman;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GameTestArea {

	/*
	 *	Test Valid Word/Phrase
	 *
	 *
	 *	A word/phrase is INVALID if:
	 *		
	 *		- has 3 consecutive same letters
	 *		- has more than one consecutive space
	 *		- has less than 5 letters
	 *		- has non-letter characters (excluding spaces)
	 *		- has more than 40 characters (including spaces)
	 *
	 *	There will be a test for each of these scenarios
	 *	as well as one that verifies a valid word/phrase is 
	 *	indeed valid.
	 *
	 *	(The test testing if the word/phrase has 40 more characters
	 *		will be the test that intentionally fails).
	 *
	 */
	
	@Test
	void testForThreeConsecutiveSameLetters() {
		
		assertFalse(Game.verifyWord("This is an invaaalid entry"));
		//										   ^^^
	}
	
	@Test
	void testForTwoConsecutiveSpaces() {
		
		assertFalse(Game.verifyWord("This is an  invalid entry"));
											// ^^
	}
	
	@Test
	void testForFiveLetters() {
		
		assertFalse(Game.verifyWord("f a l s "));
		//						  Too few letters
	}
	
	@Test
	void testForMoreThanFortyCharacters() {
		
		assertFalse(Game.verifyWord("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz"));
		//								Too many letters
	}
	
	@Test
	void testForNonLetterChars() {
		
		assertFalse(Game.verifyWord("This is a fals3 entry"));
											 //    ^
	}
	
	@Test
	void testValidWordEntry() {
		
		assertTrue(Game.verifyWord("This is a valid entry"));
		
	}

}







