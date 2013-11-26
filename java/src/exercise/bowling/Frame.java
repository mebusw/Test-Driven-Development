package exercise.bowling;

public class Frame {

	private int itsScore=0;

	public int getScores() {
		return itsScore;
	}

	public void add(int pins) {
		itsScore+=pins;
	}

}
