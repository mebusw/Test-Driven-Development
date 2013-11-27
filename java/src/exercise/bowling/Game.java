package exercise.bowling;

public class Game {

	private boolean firstThrowInFrame = true;
	private int itsCurrentFrame = 1;
	private Scorer itsScorer = new Scorer();

	public void add(int pins) {
		itsScorer.addThrow(pins);
		adjustCurrentFrame(pins);
	}

	private void adjustCurrentFrame(int pins) {
		if (isLastBallInFrame(pins)) {
			advanceFrame();
		} else {
			firstThrowInFrame = false;
		}

	}

	private boolean isLastBallInFrame(int pins) {
		return !firstThrowInFrame || strike(pins);
	}

	private boolean strike(int pins) {
		return firstThrowInFrame && pins == 10;
	}

	private void advanceFrame() {
		itsCurrentFrame = Math.min(10, itsCurrentFrame + 1);
	}

	public int scoreForFrame(int frame) {
		return itsScorer.scoreForFrame(frame);
	}

	public int score() {
		return itsScorer.scoreForFrame(itsCurrentFrame);
	}

}
