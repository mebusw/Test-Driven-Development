package exercise.wordWrap;

public class WordWrap {

	public static String wrap(String s, int length) {
		if (s == null)
			return "";
		String result = "";
		while (s.length() > length) {
			result += s.substring(0, length) + "\n";
			s = s.substring(length);
		}
		result += s;
		return result;
	}
}
