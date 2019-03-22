package hangman;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class UsernameDataHandler {

	File data = new File("data.txt");
	private PrintWriter output;
	protected ArrayList<String> information = new ArrayList<String>();
	private ArrayList<String> usernames = new ArrayList<String>();
	
	public UsernameDataHandler() {
		
		acquireOldData();
		populateUsernameList();	// Sorted alphabetically
		
	}
	
	// Methods
	
	public void registerUser(User user) {
		
		information.add(user.toString());
		
	}
	
	public void save(User user) {
		
		try {
			FileWriter setup = new FileWriter(data);
			this.output = new PrintWriter(setup);
		}
		catch (FileNotFoundException e) {
			System.out.println("Unable to connect to user data.");
		}
		catch (IOException e) {
			System.out.println("FileWriter mishap.");
		}
		
		// Get Index of Relevant String
		int index = getUserIndex(user);
		
		// Get original numbers
		int[] stats = extractUserNumbers(information.get(index));
		
		// Remove original string from information list
		information.remove(index);
		
		// Calculate new numbers
		updateUserStats(stats, user);
		
		// Generate Updated String
		String updatedUserStats = generateUpdatedString(stats, user);
		
		// Add New String to information
		information.add(updatedUserStats);
		
		// Print to file
		for (String userString : information) {
			output.println(userString);
		}
		
		output.flush();
	}
	
	private void populateUsernameList() {

		for (int i = 0; i < information.size(); i++) {

			String username = information.get(i);
			int endOfNameIndex = username.indexOf(' ');
			username = username.substring(0, endOfNameIndex);

			usernames.add(username);

		}
		
		
		Collections.sort(usernames);
		
	}
	
	public boolean displayUsernameList() {
		
		System.out.println("= Username List = \n");
		
		if (usernames.size() == 0) {
			System.out.println("No registered users.");
			return true;
		} else {
			
			int i = 0;
			
			for (String user : usernames) {
				System.out.println(++i + ". " + user);
			}
		
		}
		
		return false;
		
	}
	
	public User getUser(int index) throws IOException {
		try {
			return new User(usernames.get(index), new ArrayList<String>());	// New arrayList to bypass verification of username
		} catch (IOException e) {
			return null;
		}
	}
	
	public void displayUserStats(User user) {
		
		int index = getUserIndex(user);
		int[] stats = extractUserNumbers(information.get(index));
		
		System.out.println("\n" + user.getUserName() + "'s Stats:");
		System.out.println("\tWords Solved All Time -> " + stats[0]);
		System.out.println("\tTotal Games Played    -> " + stats[1]);
		System.out.println("\tNormal Games Played   -> " + stats[2]);
		System.out.println("\tHard Games Played     -> " + stats[3]);
		System.out.println("\tMost Words Solved One Game (Normal) -> " + stats[4]);
		System.out.println("\tMost Words Solved One Game (Hard)   -> " + stats[5]);
		System.out.println("\tNumber of Times Hanged -> " + stats[6]);
		
	}
	
	private void acquireOldData() {
		
		try {
			Scanner fileScan = new Scanner(data);
			
			while (fileScan.hasNextLine()) {
				information.add(fileScan.nextLine());
			}
			
			fileScan.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	// Helper methods for updating statistics
	
	private void updateUserStats(int[] originalNumbers, User user) {
		
		originalNumbers[0] += user.getWordsSolvedAllTime();
		originalNumbers[2] += user.getNormalGamesPlayed();
		originalNumbers[3] += user.getHardGamesPlayed();
		
		// Uses sum of [2] and [3]
		originalNumbers[1] = originalNumbers[2] + originalNumbers[3];
		
		// Special Slots
		if (user.getNormalMostWordsSolvedOneGame() > originalNumbers[4])
			originalNumbers[4] = user.getNormalMostWordsSolvedOneGame();
		
		if (user.getHardMostWordsSolvedOneGame() > originalNumbers[5])
			originalNumbers[5] += user.getHardMostWordsSolvedOneGame();
		
		
		originalNumbers[6] += user.getNumTimesHanged();
		
	}
	
	protected int[] extractUserNumbers(String user) {
		
		int[] numbers = new int[7];
		
		String remaining = user;
		
		for (int i = 0; i < 7; i++) {	// 7 is the number of statistics
			
			int numIndex = remaining.indexOf(": ") + 2;									// Start of number
			int endNumIndex = remaining.substring(numIndex).indexOf(' ') + numIndex;	// End of number
			int value = Integer.parseInt(remaining.substring(numIndex, endNumIndex));	// Stat value
			
			numbers[i] = value;
			
			remaining = remaining.substring(endNumIndex);
			
		}
		
		return numbers;
		
	}
	
	protected int getUserIndex(User user) {
		
		String username = user.getUserName();
		int index = -1;
		
		for (int i = 0; i < information.size(); i++) {
			
			String string = information.get(i);
			int endOfNameIndex = string.indexOf(' ');
			string = string.substring(0, endOfNameIndex);
			
			if (username.equals(string)) {
				index = i;
				break;
			}
				
		}
		
		return index;
		
	}
	
	private String generateUpdatedString(int[] numbers, User user) {
		
		String[] categories = {" | Words Solved All Time: ", " | Total Games Played: ", " | Normal Games Played: ",
							   " | Hard Games Played: ", " | Normal Most Words Solved One Game: ",
							   " | Hard Most Words Solved One Game: ", " | Number of Times Hanged: "};
		
		StringBuilder string = new StringBuilder(user.getUserName());
		
		for (int i = 0; i < categories.length; i++) {
			string.append(categories[i]);
			string.append(numbers[i]);
		}
		
		string.append(" ");
		
		return string.toString();
		
	}
	
	// Method for getting list of usernames
	
	public ArrayList<String> getUsernames() {
		
		ArrayList<String> copiedList = new ArrayList<String>();
		
		if (usernames.size() > 0) {
			for (String username : usernames) {
				String string = new String(username);
				copiedList.add(string);
			}
		}
		
		return copiedList;
		
	}
	
}










