package exercise.bowling;

public class Rolls {

	private int[] rolls;
	private int index;
	
	public Rolls(int... rolls) {
		this.rolls = rolls;
	}
	
	public int takeOne() {
		return rolls[index++];
	}

	public int strikeBonus() {
		return rolls[index] + rolls[index+1];
	}

	public int spearBonuse() {
		return rolls[index];
	}

}
