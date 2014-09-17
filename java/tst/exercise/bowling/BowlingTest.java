package exercise.bowling;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BowlingTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testFrame() {
		Frame frame = new Frame(new Rolls(1, 2));
		assertEquals(3, frame.score());
	}

	@Test
	public void testStrikeFrame() {
		Rolls rolls = new Rolls(10, 4, 5);
		Frame frame = new Frame(rolls);
		assertEquals(19, frame.score());
	}

	@Test
	public void testSpareFrame() {
		Rolls rolls = new Rolls(6, 4, 5);
		Frame frame = new Frame(rolls);
		assertEquals(15, frame.score());
	}

	@Test
	public void testAllRolls() {
		assertEquals(300, score(new int[] { 10, 10, 10, 10, 10, 10, 10, 10, 10,
				10, 10, 10 }));
		assertEquals(270, score(new int[] { 10, 10, 10, 10, 10, 10, 10, 10, 10,
				9, 1, 1 }));
		assertEquals(299, score(new int[] { 10, 10, 10, 10, 10, 10, 10, 10, 10,
				10, 10, 9 }));
	}

	public static int score(int[] rolls) {
		Rolls r = new Rolls(rolls);

		int score = 0;
		for (int i = 0; i < 10; i++) {
			score += new Frame(r).score();
		}
		return score;
	}
}
