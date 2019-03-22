package hangman;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Scanner;

class LivesGameTest {

	ArrayList<String> word = new ArrayList<String>();
	
	@Test
	void testGetWord() {
		word.add("aassd");
		LivesGame game = new LivesGame(3, "medium", new Scanner(System.in), null);
		game.wordBank = word;
		
		assertEquals("aassd", game.getWord());
		
	}

}
