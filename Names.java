/*  Student information for assignment:
*
*  On my honor, Khanh Van, this programming assignment is my own work
*  and I have not provided this code to any other student.
*
*  UTEID: kqv69
*  email address: kvan27082002@gmail.com
*  Number of slip days I am using:
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * A collection of NameRecords. Stores NameRecord objects and provides methods
 * to select NameRecords based on various criteria.
 */
public class Names {

	private ArrayList<NameRecord> names;
	private NameRecord name;
	private int BASE_DECADE;
	private int NUMS_OF_DECADE;

	/**
	 * Construct a new Names object based on the data source the Scanner sc is
	 * connected to. Assume the first two lines in the data source are the base year
	 * and number of decades to use. Any lines without the correct number of decades
	 * are discarded and are not part of the resulting Names object. Any names with
	 * ranks of all 0 are discarded and not part of the resulting Names object.
	 * 
	 * @param sc Is connected to a data file with baby names and positioned at the
	 *           start of the data source.
	 */
	public Names(Scanner sc) {
		names = new ArrayList<NameRecord>();
		BASE_DECADE = sc.nextInt();
		NUMS_OF_DECADE = sc.nextInt();
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			String[] parsedData = line.split("\\s+");
			if (parsedData.length == NUMS_OF_DECADE + 1) {
				String currName = parsedData[0];
				ArrayList<Integer> ranks = new ArrayList<Integer>();
				for (int i = 1; i <= NUMS_OF_DECADE; i++) {
					ranks.add(Integer.parseInt(parsedData[i]));
				}
				name = new NameRecord(BASE_DECADE, NUMS_OF_DECADE, currName, ranks);
				names.add(name);
			}
		}
	}

	/**
	 * Returns an ArrayList of NameRecord objects that contain a given substring,
	 * ignoring case. The names must be in sorted order based on the names of the
	 * NameRecords.
	 * 
	 * @param partialName != null, partialName.length() > 0
	 * @return an ArrayList of NameRecords whose names contains partialName. If
	 *         there are no NameRecords that meet this criteria returns an empty
	 *         list.
	 */
	public ArrayList<NameRecord> getMatches(String partialName) {
		ArrayList<NameRecord> matchedNames = new ArrayList<NameRecord>();
		for (int i = 0; i < names.size(); i++) {
			if (names.get(i).getName().toLowerCase().contains(partialName.toLowerCase())) {
				matchedNames.add(names.get(i));
			}
		}
		Collections.sort(matchedNames);
		return matchedNames;
	}

	/**
	 * Returns an ArrayList of Strings of names that have been ranked in the top
	 * 1000 or better for every decade. The Strings must be in sorted order based on
	 * the name of the NameRecords.
	 * 
	 * @return A list of the names that have been ranked in the top 1000 or better
	 *         in every decade. The list is in sorted ascending order. If there are
	 *         no NameRecords that meet this criteria returns an empty list.
	 */
	public ArrayList<String> rankedEveryDecade() {
		ArrayList<String> namesRanked = new ArrayList<String>();
		for (int i = 0; i < names.size(); i++) {
			if (names.get(i).isAlwaysInTop() == true) {
				namesRanked.add(names.get(i).getName());
			}
		}
		Collections.sort(namesRanked);
		return namesRanked;
	}

	/**
	 * Returns an ArrayList of Strings of names that have been ranked in the top
	 * 1000 or better in exactly one decade. The Strings must be in sorted order
	 * based on the name of the NameRecords.
	 * 
	 * @return A list of the names that have been ranked in the top 1000 or better
	 *         in exactly one decade. The list is in sorted ascending order. If
	 *         there are no NameRecords that meet this criteria returns an empty
	 *         list.
	 */
	public ArrayList<String> rankedOnlyOneDecade() {
		ArrayList<String> namesRankedOnce = new ArrayList<String>();
		for (int i = 0; i < names.size(); i++) {
			if (names.get(i).isInTopOnce() == true) {
				namesRankedOnce.add(names.get(i).getName());
			}
		}
		Collections.sort(namesRankedOnce);
		return namesRankedOnce;
	}

	/**
	 * Returns an ArrayList of Strings of names that have been getting more popular
	 * every decade. The Strings must be in sorted order based on the name of the
	 * NameRecords.
	 * 
	 * @return A list of the names that have been getting more popular in every
	 *         decade. The list is in sorted ascending order. If there are no
	 *         NameRecords that meet this criteria returns an empty list.
	 */
	public ArrayList<String> alwaysMorePopular() {
		ArrayList<String> namesMorePopular = new ArrayList<String>();
		for (int i = 0; i < names.size(); i++) {
			if (names.get(i).isMorePopular() == true) {
				namesMorePopular.add(names.get(i).getName());
			}
		}
		Collections.sort(namesMorePopular);
		return namesMorePopular;
	}

	/**
	 * Returns an ArrayList of Strings of names that have been getting less popular
	 * every decade. The Strings must be in sorted order based on the name of the
	 * NameRecords.
	 * 
	 * @return A list of the names that have been getting less popular in every
	 *         decade. The list is in sorted ascending order. If there are no
	 *         NameRecords that meet this criteria returns an empty list.
	 */
	public ArrayList<String> alwaysLessPopular() {
		ArrayList<String> namesLessPopular = new ArrayList<String>();
		for (int i = 0; i < names.size(); i++) {
			if (names.get(i).isLessPopular() == true) {
				namesLessPopular.add(names.get(i).getName());
			}
		}
		Collections.sort(namesLessPopular);
		return namesLessPopular;
	}

	/**
	 * Return the NameRecord in this Names object that matches the given String
	 * ignoring case. <br>
	 * <tt>pre: name != null</tt>
	 * 
	 * @param name The name to search for.
	 * @return The name record with the given name or null if no NameRecord in this
	 *         Names object contains the given name.
	 */
	public NameRecord getName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("The parameter name cannot be null");
		}
		NameRecord currNameRecord = null;
		for (int i = 0; i < names.size(); i++) {
			if (names.get(i).getName().equals(name)) {
				currNameRecord = names.get(i);
				break;
			}
		}
		return currNameRecord;
	}
}