import java.util.PriorityQueue;
/**
 * A class that contains a priorityqueue of candidates to represent a ranked choice vote
 * 
 * @author Gemma Joy Bertain and Ben Heck
 * @version 11.9.2023
 */
public class Ballot {
	// fields 
	private PriorityQueue<Candidate> ballot = new PriorityQueue<>(); 
	
	// methods
	/**
	 * Method that adds a candidate to the ballot
	 * @param candidate
	 */
	public void addCandidate(Candidate candidate) {
		ballot.add(candidate);
	}
	
	/**
	 * Method that removes a given candidate from the ballot
	 * @param candidate
	 */
	public void removeCandidate(Candidate candidate) {
		ballot.remove(candidate);
	}
	
	/*
	 * A getter method for the size of the ballot priority queue
	 */
	public int getSize() {
		return ballot.size();
	}
	
	/**
	 * A method that peeks the ballot
	 * @return the candidate at the beginning of the ballot
	 */
	public Candidate peekBallot() {
		return ballot.peek();
	}
	
	/**
	 * A method that writes out the ballot as a string
	 */
	@Override
	public String toString() {
		String str = "";
		for (Candidate c : ballot) {
			str += c.getCandidateName() + " " + c.getRank();
		}
		return str;
	}
}
