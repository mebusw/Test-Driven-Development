package exercise.arabicToRoman;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class ArabicToRomanTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		assertEquals("I", convert(1));
		assertEquals("X", convert(10));
		assertEquals("XI", convert(11));
		assertEquals("C", convert(100));
		assertEquals("CI", convert(101));
		assertEquals("CXI", convert(111));
		
		assertEquals("CCI", convert(201));
	}

	private String symbolX1[] = new String[] { "", "I", "II" };
	private String symbolX10[] = new String[] { "", "X", "XX" };
	private String symbolX100[] = new String[] { "", "C", "CC" };
	private String symbolX1000[] = new String[] { "", "D", "DD" };

	private String convert(int arabic) {
		String x1 = symbolX1[arabic % 10];
		String x10 = symbolX10[arabic % 100 / 10];
		String x100 = symbolX100[arabic % 1000 / 100];
		String x1000 = symbolX1000[arabic % 10000 / 1000];

		return x1000 + x100 + x10 + x1;
	}

}
