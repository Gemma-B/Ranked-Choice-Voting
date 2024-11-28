import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A class that runs the algorithm to chose a winner from the election
 * 
 * @author Gemma Joy Bertain and Ben Heck
 * @version 11.9.2023
 */
public class InstantRunoffAlgorithm {
	private ArrayList<Ballot> votes;
	private List<String> candidates;
	private Candidate loser = new Candidate();
	private Candidate leader = new Candidate();

	
	// methods
	
	/**
	 * A method to run our election
	 * 
	 * @param votes the list of ballots we have
	 * @param candidateList the list of candidates in the election
	 * @return which candidate has won!
	 */
	public Candidate runElection(ArrayList<Ballot> votes, String[] candidateList) {
		this.votes = votes;
		int round = 1;
		boolean stillRunning = true;
		ArrayList<String> leaderLoser = new ArrayList<>();
		candidates = new ArrayList<>(Arrays.asList(candidateList));

		while (stillRunning) {
			
			// do we ever actually update our leading candidate method with new data? im not sure :(
			leaderLoser = findLeadingCandidate(votes, candidates);
			leader.setCandidateName(leaderLoser.get(0));
			loser.setCandidateName(leaderLoser.get(1));
			boolean hasMajority = leaderLoser.get(2).equals("true");
			
			System.out.println("\nRound " + round);
			System.out.println(toString());
			
			if (candidates.size() <= 1) {
				stillRunning = false;
				return leader;
				
			} else {

				for (int i = 0; i < votes.size(); i++) {
					votes.get(i).removeCandidate(loser);
					
					if (votes.get(i).peekBallot().getRank() == -1) {
						votes.remove(i);
						i--;
					}
				}
				
				// problem set also decreases by removing losing candidate from candidate list 
				candidates.remove(loser.getCandidateName());
				
				// call runElection with new votes list
			}
			round++;
		}
		return leader;
	} 
	/**
	 * A helper method to find the leading/losing candidate 
	 * 
	 * @param votes the list of votes in the election
	 * @param candidateList the list of candidates for the election
	 * @return arraylist with the leader in pos[0] and the loser in pos[1]
	 */
	private ArrayList<String> findLeadingCandidate(ArrayList<Ballot> votes, List<String> candidates) {
		// variables to keep track of who is in the lead/last and how many votes they have
		int mostVotes = -1;
		int leastVotes = votes.size();
		String currentLeader = "";
		String currentLoser = "";
		
		// return values for leader, loser, and if the leader has the majority
		ArrayList<String> leaderLoser = new ArrayList<>();

		// looping through every candidate
		for (int i = 0; i < candidates.size(); i++) {
			int currentVotes = 0;

			// looping through the votes to see who voted for our candidate
			for (int j = 0; j < votes.size(); j++) {
				
				if (votes.get(j).getSize() > 0) {
					String name = votes.get(j).peekBallot().getCandidateName(); // getting the name of the candidate from the ballot
					
					if (name.equals(candidates.get(i))) {
						currentVotes++; //if they voted for our candidate, then we add one to our current vote counter
					}
				}
			}
			
			
			// if our current candidate has the most votes, they're the new leader
			if (currentVotes > mostVotes) {
				mostVotes = currentVotes;
				currentLeader = candidates.get(i);
			}
			
			// if our current candidate has the least votes, they're the new loser
			if (currentVotes < leastVotes) {
				leastVotes = currentVotes;
				currentLoser = candidates.get(i);
			}
			
		}
		leaderLoser.add(currentLeader);
		leaderLoser.add(currentLoser);
		
		if ((votes.size() / mostVotes) < 2) {
			// our leader has a majority of the votes! 
			leaderLoser.add("true");
		} else {
			leaderLoser.add("false");
		}
		return leaderLoser;
	}
	
	@Override 
	public String toString() {
		return toStringHelper(0);
		
	}
	
	private String toStringHelper(int i) {
		String str = "";
		double percent = 0.0;
		double numVotes = 0.0;
		
		// base case when we only have one candidate left to print out	
		if (i >= candidates.size() && candidates.size() != 1) {
			str += "Total Number of Unempty Ballots: " + votes.size() + "\n";
			str += loser.getCandidateName() + " eliminated";
			return str;
			
		}  else if (candidates.size() == 1) {
			// other kinda weird base case for our winner's round where the winner is the only available option 
			for (int j = 0; j < votes.size(); j++) {
				if (votes.get(j).getSize() > 0) {
					//System.out.println(votes.get(j).peekBallot().getCandidateName());
					if (votes.get(j).peekBallot().getCandidateName().equals(candidates.get(i))) {
						numVotes++;
					}
				}
			}
			percent = numVotes / votes.size();
			str += candidates.get(i) + ": " + numVotes + " votes (" + percent + ")" + "\n";
			str += "Total Number of Unempty Ballots: " + votes.size() + "\n";
			str += "Winner By Majority: " + leader.getCandidateName();
			return str; 
			
		} else { 
			
			// general case
			for (int j = 0; j < votes.size(); j++) {
				if (votes.get(j).getSize() > 0) {
					//System.out.println(votes.get(j).peekBallot().getCandidateName());
					if (votes.get(j).peekBallot().getCandidateName().equals(candidates.get(i))) {
						numVotes++;
					}
				}
			}
			percent = numVotes / votes.size();
			str += candidates.get(i) + ": " + numVotes + " votes (" + percent + ")" + "\n";
			return str + toStringHelper(i + 1);

		}
		
	}
	
}
