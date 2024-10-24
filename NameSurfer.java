/*
 * Student information for assignment: Replace <NAME> in the following with your
 * name. You are stating, on your honor you did not copy any other code on this
 * assignment and have not provided your code to anyone. 
 * 
 * On my honor, Khanh Van, this programming assignment is my own work 
 * and I have not provided this code
 * to any other student. 
 * 
 * UTEID: kqv69
 * email address: kvan27082002@gmail.com
 * Number of slip days I am using:
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class NameSurfer {

	// CS314 students, explain your menu option 7 here:
	
	/*
	 * My menu of option 7 would take in 2 System.in which is the full name (not
	 * case sensitive) and an int of a year within the given decades and would
	 * return the rank of the year within that decade. For example: When entering
	 * the year 1940-1949, it would return the rank of the 1940 decade of the given
	 * name.
	 */
	
	// CS314 students, Explain your interesting search / trend here:
	
	/* I found an interesting search when I run the "names.txt" file which begins
	   1900 and go through next 11 decades (to the 2000).
	   When going through the database, I found that names that are named after
	   major cities of the state of Texas is mostly in top ranks throughout the decades.	
	   	   
	   Austin                    
	   1900: 237
       1910: 258
       1920: 292
       1930: 357
       1940: 434
       1950: 528
       1960: 562
       1970: 382
       1980: 96
       1990: 23
       2000: 23   
       ------------
       Houston
       1900: 575
       1910: 586
       1920: 513
	   1930: 588
       1940: 727
       1950: 798
       1960: 0
       1970: 0
       1980: 969
       1990: 687
       2000: 787
       -------------
       Dallas
       1900: 372
       1910: 336
       1920: 316
       1930: 282
       1940: 321
       1950: 382
       1960: 463
       1970: 418
       1980: 336
       1990: 294
       2000: 314
	*/
	// CS314 students, add test code for NameRecord class here:
	private static void rankAtGivenDecade(Names namesDatabase, Scanner keyboard) {
		if (namesDatabase == null || keyboard == null) {
			throw new IllegalArgumentException("The parameters cannot be null");
		}
		int DECADE = 10;
		System.out.print("Please enter a name: ");
		String data1 = keyboard.next();
		String rawName = data1.toString().toLowerCase();
		String name = rawName.substring(0, 1).toUpperCase() + rawName.substring(1);
		while (namesDatabase.getName(name) == null) {
			System.out.print("\n" + data1 + " is not in the database\n" 
							+ "Please enter a name: ");
			data1 = keyboard.next();
			rawName = data1.toString().toLowerCase();
			name = rawName.substring(0, 1).toUpperCase() + rawName.substring(1);
		}
		NameRecord currRec = namesDatabase.getName(name);
		System.out.print("Please enter the year: ");
		int data2 = keyboard.nextInt();
		int lastDecade = currRec.getBaseDecade();
		for (int i = 0; i < currRec.getNumsOfDecade(); i++) {
			lastDecade += DECADE;
		}
		while (data2 < currRec.getBaseDecade() || data2 >= lastDecade) {
			System.out.print("\nThe year is not within the range of given decade!\n" 
							+ "Please enter the year: ");
			data2 = keyboard.nextInt();
		}
		int decade = (data2 - currRec.getBaseDecade()) / DECADE;
		System.out.println("\n" + currRec.getName() + " is ranked " 
						+ currRec.getRank(decade) + " in " + data2);
	}

	// Checks if given name is present in Names.
	private static void performGetNameTest(Names names, String name, boolean expectNull) {

		System.out.println("Performing test for this name: " + name);
		if (expectNull) {
			System.out.println("Expected return value is null");
		} else {
			System.out.println("Expected return value is not null");
		}
		NameRecord result = names.getName(name);
		if ((expectNull && result == null) || (!expectNull && result != null)) {
			System.out.println("PASSED TEST.");
		} else {
			System.out.println("Failed test");
		}
	}

	// main method. Driver for the whole program
	public static void main(String[] args) {

		// Alter name of file to try different data sources.
		final String NAME_FILE = "names.txt";
		Scanner fileScanner = getFileScannerForNames(NAME_FILE);
		Names namesDatabase = new Names(fileScanner);
		fileScanner.close();
		runOptions(namesDatabase);
	}

	/*
	 * pre: namesDatabase != null Ask user for options to perform on the given Names
	 * object. Creates a Scanner connected to System.in.
	 */
	private static void runOptions(Names namesDatabase) {
		Scanner keyboard = new Scanner(System.in);
		MenuChoices[] menuChoices = MenuChoices.values();
		MenuChoices menuChoice;
		do {
			showMenu();
			int userChoice = getChoice(keyboard) - 1;
			menuChoice = menuChoices[userChoice];
			if (menuChoice == MenuChoices.SEARCH) {
				search(namesDatabase, keyboard);
			} else if (menuChoice == MenuChoices.ONE_NAME) {
				oneName(namesDatabase, keyboard);
			} else if (menuChoice == MenuChoices.APPEAR_ONCE) {
				appearOnce(namesDatabase);
			} else if (menuChoice == MenuChoices.APPEAR_ALWAYS) {
				appearAlways(namesDatabase);
			} else if (menuChoice == MenuChoices.ALWAYS_MORE) {
				alwaysMore(namesDatabase);
			} else if (menuChoice == MenuChoices.ALWAYS_LESS) {
				alwaysLess(namesDatabase);
			} else if (menuChoice == MenuChoices.STUDENT_SEARCH) {
				rankAtGivenDecade(namesDatabase, keyboard);
			}
		} while (menuChoice != MenuChoices.QUIT);
		keyboard.close();
	}

	/*
	 * Create a Scanner and return connected to a File with the given name. pre:
	 * fileName != null post: Return a Scanner connected to the file or null if the
	 * File does not exist in the current directory.
	 */
	private static Scanner getFileScannerForNames(String fileName) {
		Scanner sc = null;
		try {
			sc = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("\n***** ERROR IN READING FILE ***** ");
			System.out.println("Can't find this file " + fileName + " in the current directory.");
			System.out.println("Error: " + e);
			String currentDir = System.getProperty("user.dir");
			System.out.println("Be sure " + fileName + " is in this directory: ");
			System.out.println(currentDir);
			System.out.println("\nReturning null from method.");
			sc = null;
		}
		return sc;
	}

	/*
	 * Display the names that have appeared in every decade. pre: n != null post:
	 * print out names that have appeared in ever decade
	 */
	private static void appearAlways(Names namesDatabase) {
		if (namesDatabase == null) {
			throw new IllegalArgumentException("The parameter namesDatabase cannot be null");
		}
		System.out.println(namesDatabase.rankedEveryDecade().size() 
				+ " names appear in every decade. The names are: ");
		for(int i = 0; i < namesDatabase.rankedEveryDecade().size(); i++) {
			System.out.println(namesDatabase.rankedEveryDecade().get(i));
		}		
	}

	/*
	 * Display the names that have appeared in only one decade. pre: n != null post:
	 * print out names that have appeared in only one decade
	 */
	private static void appearOnce(Names namesDatabase) {
		if (namesDatabase == null) {
			throw new IllegalArgumentException("The parameter" + " namesDatabase cannot be null");
		}
		System.out.println(namesDatabase.rankedOnlyOneDecade().size() 
				+ " names appear in exactly one decade. The names are: ");
		for (int i = 0; i < namesDatabase.rankedOnlyOneDecade().size(); i++) {
			System.out.println(namesDatabase.rankedOnlyOneDecade().get(i));
		}
	}

	/*
	 * Display the names that have gotten more popular in each successive decade.
	 * pre: n != null post: print out names that have gotten more popular in each
	 * decade
	 */
	private static void alwaysMore(Names namesDatabase) {
		if (namesDatabase == null) {
			throw new IllegalArgumentException("The parameter" + " namesDatabase cannot be null");
		}
		System.out.println(namesDatabase.alwaysMorePopular().size() 
				+ " names are more popular in every decade.");
		for (int i = 0; i < namesDatabase.alwaysMorePopular().size(); i++) {
			System.out.println(namesDatabase.alwaysMorePopular().get(i));
		}
	}

	/*
	 * Display the names that have gotten less popular in each successive decade.
	 * pre: n != null post: print out names that have gotten less popular in each
	 * decade
	 */
	private static void alwaysLess(Names namesDatabase) {
		if (namesDatabase == null) {
			throw new IllegalArgumentException("The parameter" + " namesDatabase cannot be null");
		}
		System.out.println(namesDatabase.alwaysLessPopular().size() 
				+ " names are less popular in every decade.");
		for(int i = 0; i < namesDatabase.alwaysLessPopular().size(); i++) {
			System.out.println(namesDatabase.alwaysLessPopular().get(i));
		}
	}

	/*
	 * Display the data for one name or state that name has never been ranked. pre:
	 * n != null, keyboard != null and is connected to System.in post: print out the
	 * data for n or a message that n has never been in the top 1000 for any decade
	 */
	private static void oneName(Names namesDatabase, Scanner keyboard) {
		// Note, no way to check if keyboard actually connected to System.in
		// so we simply assume it is.
		if (namesDatabase == null || keyboard == null) {
			throw new IllegalArgumentException("The parameters cannot be null");
		}
		System.out.print("Enter a Name: ");
		String data = keyboard.next();
		String rawName = data.toString().toLowerCase();
		String name = rawName.substring(0, 1).toUpperCase() + rawName.substring(1);
		if (namesDatabase.getName(name) == null) {
			System.out.println("\n" + data + " does not appear in any decade.");
		} else {
			System.out.println("\n" + namesDatabase.getName(name));			
		}
	}

	/*
	 * Display all names that contain a substring from the user and the decade they
	 * were most popular. pre: n != null, keyboard != null and is connected to
	 * System.in post: display the data for each name.
	 */
	private static void search(Names namesDatabase, Scanner keyboard) {
		// Note, no way to check if keyboard actually connected to System.in
		// so we simply assume it is.
		if (namesDatabase == null || keyboard == null) {
			throw new IllegalArgumentException("The parameters cannot be null");
		}
		System.out.print("Enter a partial name: ");
		String partialName = keyboard.next();
		ArrayList<NameRecord> currNames = namesDatabase.getMatches(partialName);
		System.out.println("\n" + "There are " + currNames.size() + " matches for " + partialName + ".");
		if (currNames.size() != 0) {
			System.out.println("\nThe matches with their highest ranking decade are:");
			for (int i = 0; i < currNames.size(); i++) {
				System.out.println(currNames.get(i).getName() + " " 
							 	 + currNames.get(i).getBestDecade());
			}
		}
	}

	/*
	 * Get choice from the user keyboard != null and is connected to System.in
	 * return an int that is >= MenuChoices.SEARCH.ordinal() and <=
	 * MenuChoices.QUIT.ordinal().
	 */
	private static int getChoice(Scanner keyboard) {
		// Note, no way to check if keyboard actually connected to System.in
		// so we simply assume it is.
		if (keyboard == null) {
			throw new IllegalArgumentException("The parameter keyboard cannot be null");
		}
		int choice = getInt(keyboard, "Enter choice: ");
		keyboard.nextLine();
		// Add one due to zero based indexing of enums, but 1 based indexing of menu.
		final int MAX_CHOICE = MenuChoices.QUIT.ordinal() + 1;
		while (choice < 1 || choice > MAX_CHOICE) {
			System.out.println();
			System.out.println(choice + " is not a valid choice");
			choice = getInt(keyboard, "Enter choice: ");
			keyboard.nextLine();
		}
		return choice;
	}

	/*
	 * Ensure an int is entered from the keyboard. pre: s != null and is connected
	 * to System.in post: return the int typed in by the user.
	 */
	private static int getInt(Scanner s, String prompt) {
		// Note, no way to check if keyboard actually connected to System.in
		// so we simply assume it is.
		if (s == null) {
			throw new IllegalArgumentException("The parameter s cannot be null");
		}
		System.out.print(prompt);
		while (!s.hasNextInt()) {
			s.next();
			System.out.println("That was not an int.");
			System.out.print(prompt);
		}
		return s.nextInt();
	}

	// Show the user the menu.
	private static void showMenu() {
		System.out.println();
		System.out.println("Options:");
		System.out.println("Enter 1 to search for names.");
		System.out.println("Enter 2 to display data for one name.");
		System.out.println("Enter 3 to display all names that appear in only " + "one decade.");
		System.out.println("Enter 4 to display all names that appear in all " + "decades.");
		System.out.println("Enter 5 to display all names that are more popular " + "in every decade.");
		System.out.println("Enter 6 to display all names that are less popular " + "in every decade.");
		System.out.println("Enter 7 to return the rank in a specific decade " + "for one name.");
		System.out.println("Enter 8 to quit.");
		System.out.println();
	}

	/**
	 * An enumerated type to hold the menu choices for the NameSurfer program.
	 */
	private static enum MenuChoices {
		SEARCH, ONE_NAME, APPEAR_ONCE, APPEAR_ALWAYS, ALWAYS_MORE, ALWAYS_LESS, STUDENT_SEARCH, QUIT;
	}
}