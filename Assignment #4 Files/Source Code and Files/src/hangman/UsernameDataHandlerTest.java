package hangman;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.io.IOException;

import org.junit.jupiter.api.Test;

class UsernameDataHandlerTest {
	
	

	UsernameDataHandler usdh = new UsernameDataHandler();
	
	int[] gilbertKey = {1, 1, 1, 0, 1, 0, 3};
	int[] jordanKey = {10, 3, 2, 1, 8, 1, 9};
	int[] larryKey = {32, 10, 5, 5, 12, 5, 30};
	
	int[][] keys = {gilbertKey, jordanKey, larryKey};
	
	@Test
	void testExtractUserNumbers() throws IOException {
		
		User gilbert = new User("Gilbert", new ArrayList<String>());
		User jordan = new User("Jordan", new ArrayList<String>());
		User larry = new User("Larry", new ArrayList<String>());
		
		User[] users = {gilbert, jordan, larry};
		
		int keyIndex = 0;
		
		for (User user : users) {
			int userInfoIndex = usdh.getUserIndex(user);
			
			int[] numbers = usdh.extractUserNumbers(usdh.information.get(userInfoIndex));
			
			for (int i = 0; i < numbers.length; i++)
				assertEquals(numbers[i], keys[keyIndex][i]);
				
			keyIndex++;
			
		}
		
		
	}

}
