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
		// Handler firstHandler = new HandlerForMultiplesOfThree(
		// new HandlerForMultiplesOfFive(new HandlerForMultiplesOfSeven(new
		// HandlerForNumberThree(
		// new HandlerForOthers(Handler.EMPTY_HANDLER)))));

		Handler firstHandler = new HandlerForMultiplesOfThree()
				.addSuccessor(new HandlerForMultiplesOfFive()
						.addSuccessor(new HandlerForMultiplesOfSeven()
								.addSuccessor(new HandlerForNumberThree()
										.addSuccessor(new HandlerForOthers()))));
		FizzBuzz fizzBuzz = new FizzBuzz(firstHandler);
		String results[] = fizzBuzz.play(40);
		assertBySN("1", results, 1);
		assertBySN("2", results, 2);
		assertBySN("Fizz", results, 3);
		assertBySN("4", results, 4);
		assertBySN("Buzz", results, 5);
		assertBySN("Whizz", results, 7);
		assertBySN("FizzBuzz", results, 15);
		assertBySN("Fizz", results, 23);
		// assertBySN("FizzBuzzWhizz", results, 35);

	}

	private void assertBySN(String expect, String[] results, int SN) {
		assertEquals(expect, results[SN - 1]);
	}

}
