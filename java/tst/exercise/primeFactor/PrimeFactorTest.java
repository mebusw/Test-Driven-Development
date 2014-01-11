package exercise.primeFactor;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PrimeFactorTest {

	@Before
	public void setUp() throws Exception {
	}

	private List<Integer> list(int... ints) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i : ints) {
			list.add(i);

		}
		return list;
	}

	@Test
	public void testOne() {
		assertEquals(list(), PrimeFactor.generate(1));
	}

	@Test
	public void testTwo() {
		assertEquals(list(2), PrimeFactor.generate(2));
	}

	@Test
	public void testThree() {
		assertEquals(list(3), PrimeFactor.generate(3));
	}

	@Test
	public void testFour() {
		assertEquals(list(2, 2), PrimeFactor.generate(4));
	}

	@Test
	public void testSix() {
		assertEquals(list(2, 3), PrimeFactor.generate(6));
	}

	@Test
	public void testEight() {
		assertEquals(list(2, 2, 2), PrimeFactor.generate(8));
	}

	@Test
	public void testNine() {
		assertEquals(list(3, 3), PrimeFactor.generate(9));
	}

}
