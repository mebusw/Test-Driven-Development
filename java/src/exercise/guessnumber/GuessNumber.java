package exercise.guessnumber;

public class GuessNumber {

	public String guess(String actual, String guessing) {
		int a = 0, b = 0;
		for (int i = 0; i < 4; i++) {

			if (actual.substring(i, i + 1).equals(guessing.substring(i, i + 1))) {
				a++;
			} else if (actual.contains(guessing.substring(i, i + 1))) {
				b++;
			}
		}

		return String.format("%dA%dB", a, b);
	}
}
