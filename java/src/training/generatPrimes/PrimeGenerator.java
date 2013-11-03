package training.generatPrimes;

public class PrimeGenerator {
	private static final int TWO = 2;
	private static boolean[] isCrossed;

	/**
	 * 
	 * @param maxValue
	 *            is the generation limit
	 * @return
	 */
	public static int[] generatePrimes(int maxValue) {
		if (maxValue >= 2) { // the only valid case
			uncrossIntegersUpTo(maxValue);
			crossOutMultiples();
			return putUncrossedToResult();
		} else { // maxValue < 2
			return new int[0]; // return null array if bad input.
		}
	}

	private static int[] putUncrossedToResult() {
		int[] result = new int[numberOfUncrossedIntegers()];

		// move the primes into the result
		for (int i = TWO, j = 0; i < isCrossed.length; i++) {
			if (notCrossed(i))
				result[j++] = i;
		}
		return result;
	}

	private static int numberOfUncrossedIntegers() {
		// how many primes are there?
		int count = 0;
		for (int i = TWO; i < isCrossed.length; i++) {
			if (notCrossed(i))
				count++; // bump count
		}
		return count;
	}

	private static void crossOutMultiples() {
		// sieve
		for (int i = TWO; i < determineInterationLimit(); i++) {
			if (notCrossed(i)) {
				crossOutMultipleOf(i);
			}
		}
	}

	private static void crossOutMultipleOf(int i) {
		for (int j = 2 * i; j < isCrossed.length; j += i) {
			isCrossed[j] = true; // multiple is not prime
		}
	}

	private static double determineInterationLimit() {
		return Math.sqrt(isCrossed.length) + 1;
	}

	private static boolean notCrossed(int i) {
		return !isCrossed[i];
	}

	private static void uncrossIntegersUpTo(int maxValue) {
		isCrossed = new boolean[maxValue + 1];

		// initialize array to true
		for (int i = TWO; i < isCrossed.length; i++) {
			isCrossed[i] = false;
		}
	}
}
