package exercise.wordWrap;

public class WordWrap {

	public static String wrap(String s, int length) {
		if (s == null)
			return "";
		if (s.length() <= length) {
			return s;
		} else {
			int space = s.substring(0, length + 1).lastIndexOf(" ");
			if (space >= 0)
				return breakBetween(s, space, space + 1, length);
			else
				return breakBetween(s, length, length, length);
		}
	}

	private static String breakBetween(String s, int start, int end, int length) {
		return s.substring(0, start) + "\n" + wrap(s.substring(end), length);
	}
}
