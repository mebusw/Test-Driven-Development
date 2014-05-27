package exercise.wordWrap;

import static org.junit.Assert.*;

import org.junit.*;
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
	/* (unconditional->if),(expression->function) */
	public void WordLongerThenLengthBreaksAtLength() throws Exception {
		assertThat(WordWrap.wrap("longword", 4), is("long\nword"));
		assertThat(WordWrap.wrap("longword", 6), is("longwo\nrd"));
	}

	@Test
	/* (if->while) */
	public void WordLongerThenTwiceLengthShouldBreaksTwice() throws Exception {
		assertThat(WordWrap.wrap("verylongword", 4), is("very\nlong\nword"));
	}

	@Test
	/* (expression->function) */
	public void TwoWordsLongerThanLimitShouldWrap() throws Exception {
		assertThat(WordWrap.wrap("word word", 6), is("word\nword"));
		assertThat(WordWrap.wrap("wrap here", 6), is("wrap\nhere"));
	}

	@Test
	/* (expression->function) */
	public void ThreeWordsEachLongerThanLimitShouldWrap() {
		assertThat(WordWrap.wrap("word word word", 6), is("word\nword\nword"));
	}

	@Test
	public void ThreeWordsJustOverTheLimitShouldBreakAtSecond() {
		assertThat(WordWrap.wrap("word word word", 11), is("word word\nword"));
	}
	
	@Test
	public void TwoWordsTheFirstEndingAtTheLimit() {
		assertThat(WordWrap.wrap("word word", 4), is("word\nword"));
	}
	
}
