package exercise.wordWrap;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

/**
 * http://blog.8thlight.com/uncle-bob/2013/05/27/
 * TheTransformationPriorityPremise.html
 * 
 * @author jacky
 * 
 */
public class WordWrapTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	/* ({}–>nil), (nil->constant) */
	public void WrapNullReturnsEmptyString() throws Exception {
		assertThat(WordWrap.wrap(null, 10), is(""));
	}

	@Test
	/* (constant->constant+) */
	public void WrapEmptyReturnsEmptyString() throws Exception {
		assertThat(WordWrap.wrap("", 10), is(""));
	}

	@Test
	/* (unconditional->if),(constant->scalar) */
	public void OneShortWordDoesNotWrap() throws Exception {
		assertThat(WordWrap.wrap("word", 5), is("word"));
	}

	@Test
	/* (expression->function) */
	public void TwoWordsLongerThanLimitShouldWrap() throws Exception {
		assertThat(WordWrap.wrap("word word", 6), is("word\nword"));
	}

	@Test
	public void ThreeWordsJustOverTheLimitShouldWrapAtSecondWord() {
		assertThat(WordWrap.wrap("word word word", 9), is("word word\nword"));
	}
}
