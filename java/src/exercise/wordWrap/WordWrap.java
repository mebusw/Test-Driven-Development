package exercise.wordWrap;

public class WordWrap {

	public static String wrap(String s, int length) {
		if (s == null)
			return "";
		if (s.length() <= length) {
			return s;
		} else {
			int space = s.indexOf(" ");
			if (space >= 0)
				return s.substring(0, space) + "\n"
						+ wrap(s.substring(space + 1), length);
			else
				return s.substring(0, length) + "\n"
						+ wrap(s.substring(length), length);
		}
	}
}
