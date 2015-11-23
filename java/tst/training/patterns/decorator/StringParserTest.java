package training.patterns.decorator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringParserTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void parseWithoutEscape() {
		StringParser parser = new StringParser();

		Node node = parser.parse("<span>Hello%20World</span>", false);

		assertEquals("Hello%20World", node.toString());
	}

	@Test
	public void parseWithEscape() {
		StringParser parser = new StringParser();

		Node node = parser.parse("<span>Hello%20World</span>", true);

		assertEquals("Hello World", node.toString());
	}

}
