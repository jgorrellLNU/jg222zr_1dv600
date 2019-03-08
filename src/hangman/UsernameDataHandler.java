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

	private File data = new File("data.txt");
	private PrintWriter output;
	private Scanner scan;
	private ArrayList<String> usernames = new ArrayList<String>();
	
	public UsernameDataHandler() {
		try {
			FileWriter setup = new FileWriter(data, true);
			this.output = new PrintWriter(setup);
			this.scan = new Scanner(data);
		}
		catch (FileNotFoundException e) {
			System.out.println("Unable to connect to user data.");
		}
		catch (IOException e) {
			System.out.println("FileWriter mishap.");
		}
		
		populateUsernameList();	// Sorted alphabetically
		
	}
	
	// Methods
	
	public void registerUser(User user) {
		
		output.println(user);
		
	}
	
	public void save() {
		output.flush();
	}
	
	private void populateUsernameList() {
		
		while (scan.hasNext()) { 
			
			String username = scan.nextLine();
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
			return new User(usernames.get(index));
		} catch (IOException e) {
			return null;
		}
	}
	
	// public void updateUserStats() {}
	
}



