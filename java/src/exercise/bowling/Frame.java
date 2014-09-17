package exercise.bowling;

public class Frame {
	private  Rolls rolls;

	public Frame(int firstRoll, int secondRoll) {
		// TODO Auto-generated constructor stub
	}

	public Frame(Rolls rolls) {
		// TODO Auto-generated constructor stub
		this.rolls = rolls;
	}

	public int score() {
		int frameScore = this.rolls.takeOne();
		if (frameScore == 10) {
			return frameScore + rolls.strikeBonus();
		}
		frameScore += this.rolls.takeOne();
		if (frameScore == 10) {
			return frameScore + rolls.spearBonuse(); 
		}
		return frameScore;
	}


}
