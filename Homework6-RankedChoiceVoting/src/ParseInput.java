import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;
/**
 * A class that parses the input file for our election
 * 
 * @author Gemma Joy Bertain and Ben Heck
 * @version 11.9.2023
 */
public class ParseInput {
	/**
	 * Reading a file of votes and organizing them
	 * @param fileName the name of our file
	 * @return an arraylist of ballots that contains all of the votes
	 * @throws FileNotFoundException if the file name is bad
	 */
	public ArrayList<Ballot> readFile(String fileName) throws FileNotFoundException {
		Scanner file = new Scanner(new File(fileName));
		String line = file.nextLine(); // first line: only shows candidates
		
		ArrayList<Ballot> totalBallots = new ArrayList<>();
		
		String[] candidates = line.split(",");
		
		while (file.hasNext()) {
			line = file.nextLine();
			String[] splitLine = line.split(",");
			Ballot ballot = new Ballot();
			
			// make a for-loop for every item in candidates
			for (int i = 0; i < candidates.length; i++) {
				Candidate candidate = new Candidate();
				candidate.setCandidateName(candidates[i]);
				candidate.setRank(findRank(splitLine[i]));
				ballot.addCandidate(candidate);
			}
			if (ballot.peekBallot().getRank() == -1) {
				continue;
			} else {
				totalBallots.add(ballot);
			}
			
			
		} // while loop
		
		file.close(); // closing my file :)
		return totalBallots;
	}
	
	/**
	 * A helper method that parses out the rank from a vote
	 * @param input
	 * @return the rank of the candidate
	 */
	private int findRank(String input) {
		if (input.length() == 9) {
			char c = input.charAt(8);
			if (Character.isDigit(c)) {
				String ch = Character.toString(c);
				int rank = Integer.parseInt(ch);
				return rank;
			}
		}
		return -1;
	}
}
