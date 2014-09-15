package training.generatPrimes;

/**
 * Refactorings:
 * 
 * (Extract Fields.) Extract Methods: initArrayOfIntegers, crossOutMultiples,
 * putUncrossedIntegerIntoResult Inline s with f.length Rename f to isCrossed
 * and revert logic Ensure for-loop starts from 2 Extract Methods:
 * crossOutMultipleOf, numberOfUncrossedIntegers, notCrossed Rename method
 * initArrayOfIntegers to uncrossIntegersUpTo Inline fields to make thread safe
 * and lessen dependency as functional programming Ensure abstract level of
 * names
 * 
 * @author jacky
 * 
 */

public class PrimeGenerator {
	private boolean[] isCrossed;

	/**
	 * 
	 * @param maxValue
	 *            is the generation limit
	 * @return
	 */
	public int[] generatePrimes(int maxValue) {
		if (maxValue >= 2) { // the only valid case
			uncrossIntegersUpTo(maxValue);
			crossOutMultiples();
			return putUncrossedIntegerIntoResult();
			
		} else { 
			return new int[0];
		}
	}

	private int[] putUncrossedIntegerIntoResult() {
		int count = numberOfUncrossedIntegers();

		int[] primes = new int[count];

		for (int i = 0, j = 0; i < isCrossed.length; i++) {
			if (notCrossed(i))
				primes[j++] = i;
		}

		return primes;
	}

	private int numberOfUncrossedIntegers() {
		int count = 0;
		for (int i = 2; i < isCrossed.length; i++) {
			if (notCrossed(i))
				count++; 
		}
		return count;
	}

	private boolean notCrossed(int i) {
		return !isCrossed[i];
	}

	private void crossOutMultiples() {
		for (int i = 2; i < Math.sqrt(isCrossed.length) + 1; i++) {
			for (int j = 2 * i; j < isCrossed.length; j += i) {
				crossOutMultipleOf(j);
			}
		}
	}

	private void crossOutMultipleOf(int j) {
		isCrossed[j] = true;
	}

	private void uncrossIntegersUpTo(int maxValue) {
		isCrossed = new boolean[maxValue + 1];

		for (int i = 0; i < isCrossed.length; i++) {
			isCrossed[i] = false;
		}

		// get rid of known non-primes
		isCrossed[0] = isCrossed[1] = true;
	}
}
