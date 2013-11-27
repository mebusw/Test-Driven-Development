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
		if (firstThrowInFrame == true) {
			if (!adjustFrameForStrike(pins)) {
				firstThrowInFrame = false;
			}
		} else {
			firstThrowInFrame = true;
			advanceFrame();
		}

	}

	private boolean adjustFrameForStrike(int pins) {
		if (pins == 10) {
			advanceFrame();
			return true;
		}
		return false;
	}

	private void advanceFrame() {
		itsCurrentFrame = Math.min(11, itsCurrentFrame + 1);
	}

	public int scoreForFrame(int frame) {
		return itsScorer.scoreForFrame(frame);
	}

	public int score() {
		return itsScorer.scoreForFrame(getCurrentFrame() - 1);
	}

	public int getCurrentFrame() {
		return itsCurrentFrame;
	}
}
