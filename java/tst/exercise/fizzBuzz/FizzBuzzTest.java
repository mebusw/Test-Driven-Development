package exercise.fizzBuzz;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FizzBuzzTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testOne() {
		FizzBuzz fizzBuzz = new FizzBuzz();
		String results[] = fizzBuzz.play(100);
		assertBySN("1", results, 1);
		assertBySN("2", results, 2);
		assertBySN("Fizz", results, 3);
		assertBySN("4", results, 4);
		assertBySN("Buzz", results, 5);
		assertBySN("FizzBuzz", results, 15);
	}

	private void assertBySN(String expect, String[] results, int SN) {
		assertEquals(expect, results[SN - 1]);
	}
}
