package exercise.bowling;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FrameTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testScoreNoThrows() {
		Frame f = new Frame();
		f.add(5);
		assertEquals(5, f.getScores());
	}

}
