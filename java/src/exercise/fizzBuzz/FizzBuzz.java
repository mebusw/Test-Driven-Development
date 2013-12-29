package exercise.fizzBuzz;

public class FizzBuzz {

	public String[] play(int max) {
		String results[] = new String[100];
		String tag;
		for (int i = 0; i < max; i++) {
			tag = "";
			if ((i + 1) % 3 == 0) {
				tag += "Fizz";
			}
			if ((i + 1) % 5 == 0) {
				tag += "Buzz";
			}
			if (tag.equals("")) {
				tag = "" + (i + 1);
			}
			results[i] = tag;
		}
		return results;
	}

}
