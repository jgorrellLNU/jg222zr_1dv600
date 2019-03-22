package hangman;

public abstract class Game {

	public Game() {}
	
	public static boolean verifyWord(String word) {
		
		/*
		 * A word/phrase can consist only of letters and spaces.
		 * 	- No more than two of the same letters consecutively
		 * 	- No more than one consecutive space
		 * 	- Word/Phrase must contain at least 5 letters
		 * 	- Word/Phrase must contain no more than 40 characters.
		 * 
		 */
		
		if (computeLengthMinusWhitespace(word) < 5) {
			return false;
		} else if (word.length() > 40) {
			return false;						  
		}
		
		int sameLetterInARow = 1;
		char currentChar = '\u0000';
		char previousChar = word.charAt(0);
		final char SPACE_CHAR = (char) 32;
		
		// Same three letters consecutive or two spaces consectutive, or non letter
		
		char[] wordArray = word.toCharArray();
		
		for (int i = 1; i < wordArray.length; i++) {
			
			currentChar = wordArray[i];
			
			if (!Character.isLetter(currentChar) && currentChar != SPACE_CHAR)	// Invalid character
				return false;
			
			
			if (currentChar == previousChar && currentChar != SPACE_CHAR)
				sameLetterInARow++;
			else 
				sameLetterInARow = 1;
			
			
			if (sameLetterInARow == 3)	// 3 same letters in a row
				return false;
			
			if (currentChar == SPACE_CHAR && previousChar == SPACE_CHAR)	// 2 spaces in a row
				return false;
				
			previousChar = currentChar;
			
		}
		
		// If the word makes it through all that, return true.
		
		return true;
		
	}
	
	private static int computeLengthMinusWhitespace(String word) {			
		int lengthMinusWhite = word.length();
		
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == ' ') {
				lengthMinusWhite--;
			}
		}
		
		return lengthMinusWhite;
		
	}
	
}
