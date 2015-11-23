package exercise.fizzBuzz;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FizzBuzzTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testOne() {
		Handler firstHandler = new HandlerForMultiplesOfThirtyFive()
				.addSuccessor(new HandlerForMultiplesOfFifteen().addSuccessor(new HandlerForMultiplesOfThree().addSuccessor(new HandlerForMultiplesOfFive()
						.addSuccessor(new HandlerForMultiplesOfSeven()
								.addSuccessor(new HandlerForNumberThree()
										.addSuccessor(new HandlerForOthers()))))));
		FizzBuzz fizzBuzz = new FizzBuzz(firstHandler);
		String results[] = fizzBuzz.play(100);
		assertBySN("1", results, 1);
		assertBySN("2", results, 2);
		assertBySN("Fizz", results, 3);
		assertBySN("4", results, 4);
		assertBySN("Buzz", results, 5);
		assertBySN("Whizz", results, 7);
		assertBySN("FizzBuzz", results, 15);
		assertBySN("Fizz", results, 23);
		assertBySN("Fizz", results, 53);
		assertBySN("FizzBuzzWhizz", results, 35);

	}

	private void assertBySN(String expect, String[] results, int SN) {
		assertEquals("Wrong for " + SN, expect, results[SN - 1]);
	}

}
