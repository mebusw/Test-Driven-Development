package training.generatPrimes;

public class PrimeGenerator {
	private static boolean[] unCrossed;
	private static int[] result;

	/**
	 * 
	 * @param maxValue
	 *            is the generation limit
	 * @return
	 */
	public static int[] generatePrimes(int maxValue) {
		if (maxValue >= 2) { // the only valid case
			initializeArrayOfIntegers(maxValue);
			crossOutMultiples();
			putUncrossedIntegerIntoResult();
			return result;
		} else { // maxValue < 2
			return new int[0]; // return null array if bad input.
		}
	}

	private static void putUncrossedIntegerIntoResult() {
		result = new int[numberOfUncrossedIntegers()];

		// move the primes into the result
		for (int i = 0, j = 0; i < unCrossed.length; i++) {
			if (unCrossed[i])
				result[j++] = i;
		}
	}

	private static int numberOfUncrossedIntegers() {
		// how many primes are there?
		int count = 0;
		for (int i = 0; i < unCrossed.length; i++) {
			if (unCrossed[i])
				count++; // bump count
		}
		return count;
	}

	private static void crossOutMultiples() {
		// sieve
		for (int i = 2; i < calcMaxPrimeFactor(); i++) {
			if (unCrossed[i]) { // if i is uncrossed, cross out its multiples
				crossOutMultiplesOf(i);
			}
		}
	}

	private static void crossOutMultiplesOf(int i) {
		for (int multiple = 2 * i; multiple < unCrossed.length; multiple += i) {
			unCrossed[multiple] = false; // multiple is not prime
		}
	}

	private static int calcMaxPrimeFactor() {
		return (int) Math.sqrt(unCrossed.length) + 1;
	}

	private static void initializeArrayOfIntegers(int maxValue) {
		// declarations
		unCrossed = new boolean[maxValue + 1];

		// initialize array to true
		for (int i = 2; i < unCrossed.length; i++) {
			unCrossed[i] = true;
		}
	}
}
