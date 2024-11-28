import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * A class that runs a ranked choice vote election - testing homework stuff
 * 
 * @author Gemma Joy Bertain and Ben Heck
 * @version 11.9.2023
 */
public class Election {
	public static void main(String[] args) {
		boolean fileFound = false;
		ParseInput inputParser = new ParseInput();
		ArrayList<Ballot> totalBallots = new ArrayList<>();
		String[] candidateList = null;
		
		System.out.println("Please select your ballot list");
		Scanner keyboard = new Scanner(System.in);
		String inputName = keyboard.nextLine();
		do {
			// Trying to read it, if not possible then we try again
			try {
				Scanner file = new Scanner(new File(inputName));
				String candidates = file.nextLine();
				candidateList = candidates.split(",");
				
				totalBallots = inputParser.readFile(inputName);
				fileFound = true;
			} catch (FileNotFoundException e) {
				System.out.println("Sorry, that doesn't match a list of ballots we have, try inputting in a different file name");
				inputName = keyboard.nextLine();
	
			}
		} while (!fileFound);
		
		// run election 
		InstantRunoffAlgorithm RunElection = new InstantRunoffAlgorithm();
		Candidate winner = RunElection.runElection(totalBallots, candidateList);
		// print the winner
		//System.out.println("Winner by Majority: " + winner.getCandidateName());
	}
}
