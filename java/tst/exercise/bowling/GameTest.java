package exercise.bowling;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GameTest {

	private Game g;

	@Before
	public void setUp() throws Exception {
		g = new Game();
	}

	@Test
	public void testTwoThrowsNoMark() {
		g.add(5);
		g.add(4);
		assertEquals(9, g.score());
		assertEquals(2, g.getCurrentFrame());
	}

	@Test
	public void testFourThrowsNoMark() {
		g.add(5);
		g.add(4);
		g.add(7);
		g.add(2);
		assertEquals(18, g.score());
		assertEquals(9, g.scoreForFrame(1));
		assertEquals(18, g.scoreForFrame(2));
		assertEquals(3, g.getCurrentFrame());
	}

	@Test
	public void testSimpleSpare() {
		g.add(3);
		g.add(7);
		g.add(3);
		assertEquals(13, g.scoreForFrame(1));
		assertEquals(2, g.getCurrentFrame());
	}

	@Test
	public void testSimpleFrameAfterSpare() {
		g.add(3);
		g.add(7);
		g.add(3);
		g.add(2);
		assertEquals(13, g.scoreForFrame(1));
		assertEquals(18, g.scoreForFrame(2));
		assertEquals(18, g.score());
		assertEquals(3, g.getCurrentFrame());
	}

	@Test
	public void testStrike() {
		g.add(10);
		g.add(3);
		g.add(6);
		assertEquals(19, g.scoreForFrame(1));
		assertEquals(28, g.score());
		assertEquals(3, g.getCurrentFrame());
	}

	@Test
	public void testPerfectGame() {
		for (int i = 0; i < 12; i++) {

			g.add(10);
		}
		assertEquals(300, g.score());
		assertEquals(11, g.getCurrentFrame());
	}

	@Test
	public void testHeartBreak() {
		for (int i = 0; i < 11; i++) {
			g.add(10);
		}
		g.add(9);
		assertEquals(299, g.score());
	}

	@Test
	public void testTenthFrameSpare() {
		for (int i = 0; i < 9; i++) {
			g.add(10);
		}
		g.add(9);
		g.add(1);
		g.add(1);
		assertEquals(270, g.score());
	}

}
