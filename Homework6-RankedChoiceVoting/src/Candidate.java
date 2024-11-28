/**
 * A class that contains candidates from an election 
 * 
 * @author Gemma Joy Bertain and Ben Heck
 * @version 11.9.2023
 */
public class Candidate implements Comparable<Candidate>{
	private String candidateName = "";
	private int rank = 0;
	
	/** 
	 * Setter method for the candidate name
	 * @param name the name to be set
	 */
	public void setCandidateName(String name) {
		this.candidateName = name;
	}
	
	/**
	 * Setter method for the rank
	 * @param rank the rank to be set
	 */
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	/**
	 * Getter method for candidate name
	 * @return the candidate's name
	 */
	public String getCandidateName() {
		return candidateName;
	}
	
	/**
	 * Getter method for candidate's rank 
	 * @return rank of the candidate
	 */
	public int getRank() {
		return rank;
	}
	
	/**
	 * A method that compares two candidates based on their rank
	 * 
	 * @param other the candidate we're comparing to
	 * @return -1 if we're smaller, 1 if we're larger, and 0 if we're the same size
	 */
	@Override
	public int compareTo(Candidate other) {
		// this is for catching no ranked choices
		if (this.rank == -1) {
			return 1;
		} else if (other.rank == -1) {
			return -1;
		}
		
		// this is for comparing the ranks of two candidates
		if (this.rank < other.rank) {
			return -1;
		} else if (this.rank > other.rank) {
			return 1;
		}
		
		// just in case
		return 0;
	}
	
	/**
	 * A method that sees if two candidates are equal 
	 * 
	 * @param o the candidate we're comparing to
	 * @return if the two candidates are equal or not
	 */
	 @Override
	 public boolean equals(Object o) {
		 Candidate other = (Candidate) o;

		 if (this.candidateName.equals(other.candidateName)) {
			 return true;
		 }
		 
		 return false;
	 }
	
}
