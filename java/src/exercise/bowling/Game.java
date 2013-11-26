package exercise.bowling;

public class Game {

	private int[] itsThrows = new int[21];
	private int itsCurrentThrow = 0;
	private boolean firstThrowInFrame = true;
	private int itsCurrentFrame = 1;
	private int ball;
	private int firstThrow;
	private int secondThrow;

	public void add(int pins) {
		itsThrows[itsCurrentThrow++] = pins;
		adjustCurrentFrame(pins);
	}

	private void adjustCurrentFrame(int pins) {
		if (firstThrowInFrame == true) {
			if (pins == 10) {
				itsCurrentFrame++;
			}
			firstThrowInFrame = false;
		} else {
			firstThrowInFrame = true;
			itsCurrentFrame++;
		}
		itsCurrentFrame = Math.min(11, itsCurrentFrame);
	}

	public int score() {
		return scoreForFrame(getCurrentFrame() - 1);
	}

	public int scoreForFrame(int frame) {
		int score = 0;
		ball = 0;
		for (int currrentFrame = 0; currrentFrame < frame; currrentFrame++) {
			firstThrow = itsThrows[ball++];
			if (firstThrow == 10) {
				score += 10 + itsThrows[ball] + itsThrows[ball + 1];
			} else {
				score += handleSecondThrow();
			}
		}
		return score;
	}

	private int handleSecondThrow() {
		int score = 0;
		secondThrow = itsThrows[ball++];
		int frameScore = firstThrow + secondThrow;
		// spare need next frame's first throw
		if (frameScore == 10) {
			score += frameScore + itsThrows[ball];
		} else {
			score += frameScore;
		}
		return score;
	}

	public int getCurrentFrame() {
		return itsCurrentFrame;
	}
}
