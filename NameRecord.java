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

import java.util.ArrayList;

public class NameRecord implements Comparable<NameRecord> {
	private int baseDecade;
	private int numsOfDecade;
	private String name;
	private ArrayList<Integer> ranks;

	public NameRecord(int baseDecade, int numsOfDecade, String name, ArrayList<Integer> ranks) {
		this.baseDecade = baseDecade;
		this.numsOfDecade = numsOfDecade;
		this.name = name;
		this.ranks = ranks;
	}
	
	public String getName() {
		return name;
	}

	public int getBaseDecade() {
		return baseDecade;
	}

	public int getNumsOfDecade() {
		return numsOfDecade;
	}

	public int getRank(int decade) {
		return ranks.get(decade);
	}

	public int getBestDecade() {
		ArrayList<Integer> convertedRanks = new ArrayList<Integer>();
		convertedRanks = convertUnknownRank(ranks);
		int max = convertedRanks.get(0);
		int index = 0;
		int currBestDecade = baseDecade;
		int DECADE = 10;
		for (int i = 0; i < convertedRanks.size(); i++) {
			if (convertedRanks.get(i) <= max) {
				max = convertedRanks.get(i);
				index = i;
			}
		}
		for (int j = 0; j < index; j++) {
			currBestDecade += DECADE;
		}
		convertOriginalRank(ranks);
		return currBestDecade;
	}

	public boolean isMorePopular() {
		ArrayList<Integer> convertedRanks = new ArrayList<Integer>();
		convertedRanks = convertUnknownRank(ranks);
		boolean isMorePopular = true;
		int initialDecade = convertedRanks.get(0);
		for (int i = 1; i < convertedRanks.size(); i++) {
			if (convertedRanks.get(i) >= initialDecade) {
				isMorePopular = false;
			} else if (convertedRanks.get(i) < initialDecade) {
				initialDecade = convertedRanks.get(i);
			}
		}
		convertOriginalRank(ranks);
		return isMorePopular;
	}

	public boolean isLessPopular() {
		ArrayList<Integer> convertedRanks = new ArrayList<Integer>();
		convertedRanks = convertUnknownRank(ranks);
		boolean isLessPopular = true;
		int initialDecade = convertedRanks.get(0);
		for (int i = 1; i < convertedRanks.size(); i++) {
			if (convertedRanks.get(i) <= initialDecade) {
				isLessPopular = false;
			} else if (convertedRanks.get(i) > initialDecade) {
				initialDecade = convertedRanks.get(i);
			}
		}
		convertOriginalRank(ranks);
		return isLessPopular;
	}

	public int numsInTopRank() {
		int numsInTop = 0;
		for (int i = 0; i < ranks.size(); i++) {
			if (ranks.get(i) > 0) {
				numsInTop++;
			}
		}
		return numsInTop;
	}

	public boolean isAlwaysInTop() {
		boolean isAlwaysInTop = true;
		for (int i = 0; i < ranks.size(); i++) {
			if (ranks.get(i) == 0) {
				isAlwaysInTop = false;
			}
		}
		return isAlwaysInTop;
	}

	public boolean isInTopOnce() {
		boolean isInTopOnce = true;
		int inTop = 0;
		for (int i = 0; i < ranks.size(); i++) {
			if (ranks.get(i) > 0) {
				inTop += 1;
			}
		}
		if (inTop == 0 || inTop > 1) {
			isInTopOnce = false;
		}
		return isInTopOnce;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		int currDecade = baseDecade;
		int DECADE = 10;
		sb.append(name + "\n");
		for (int i = 0; i < ranks.size(); i++) {
			sb.append(currDecade + ": " + ranks.get(i) + "\n");
			currDecade += DECADE;
		}
		return sb.toString();
	}

	@Override
	public int compareTo(NameRecord other) {

		return this.name.compareTo(other.name);
	}

	// This helper method is to convert the 0 ranks to 1000 for easier looping and
	// operating
	private ArrayList<Integer> convertUnknownRank(ArrayList<Integer> ranks) {
		int ROOF_RANK = 1000;
		ArrayList<Integer> convertedRank = new ArrayList<Integer>();
		for (int i = 0; i < ranks.size(); i++) {
			if (ranks.get(i) == 0) {
				ranks.set(i, ROOF_RANK);
			}
			convertedRank.add(ranks.get(i));
		}
		return convertedRank;
	}

	// This helper method is to revert the converted ranks to original
	private ArrayList<Integer> convertOriginalRank(ArrayList<Integer> ranks) {
		int FLOOR_RANK = 0;
		ArrayList<Integer> convertedRank = new ArrayList<Integer>();
		for (int i = 0; i < ranks.size(); i++) {
			if (ranks.get(i) == 1000) {
				ranks.set(i, FLOOR_RANK);
			}
			convertedRank.add(ranks.get(i));
		}
		return convertedRank;
	}
}
