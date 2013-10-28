package training.generatPrimes;

import static org.junit.Assert.*;

import org.junit.Test;

public class GeneratePrimesTest {

	@Test
	public void testPrimes() {
		int[] nullArray = PrimeGenerator.generatePrimes(0);
		assertEquals(0, nullArray.length);

		int[] minArray = PrimeGenerator.generatePrimes(2);
		assertEquals(1, minArray.length);
		assertEquals(2, minArray[0]);

		int[] threeArray = PrimeGenerator.generatePrimes(3);
		assertEquals(2, threeArray.length);
		assertEquals(2, threeArray[0]);
		assertEquals(3, threeArray[1]);

		int[] centArray = PrimeGenerator.generatePrimes(100);
		assertEquals(25, centArray.length);
		assertEquals(97, centArray[24]);

	}

}
