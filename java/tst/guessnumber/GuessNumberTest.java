package guessnumber;

import exercise.guessnumber.GuessNumber;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GuessNumberTest {
	/**
	 * 猜数字 游戏生成一个随机并且不重复的4位数字，并打印“游戏开始，请猜”
	 * 玩家给出4位数字进行猜测，结果返回[m]A[n]B的格式，比如“0A2B”，游戏继续 [m]A代表有[m]位不仅数字猜对了，而且位置也猜对了
	 * [n]B代表有[n]位只是数字猜对了，但是位置不对 玩家猜对后，打印出”恭喜，您猜对了”，游戏结束
	 * 玩家猜不对，游戏继续，如果10次还猜不对，打印“对不起，您失败了”，游戏结束
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAllNumbersAreWrong() {
		GuessNumber guessNumber = new GuessNumber();

		String result = guessNumber.guess("1234", "5678");

		
		assertEquals("0A0B", result);
	}

	@Test
	public void testOneNumberIsCorrectAndRightPlace() {
		GuessNumber guessNumber = new GuessNumber();
		
		String result = guessNumber.guess("1234", "1678");
		
		assertEquals("1A0B", result);	
	}
	@Test
	public void testTwoNumbersAreCorrectAndRightPlace() {
		GuessNumber guessNumber = new GuessNumber();
		
		String result = guessNumber.guess("1234", "1278");
		
		assertEquals("2A0B", result);	
	}
	
	@Test
	public void testOneNumberIsCorrectButNotRightPlace() {
		GuessNumber guessNumber = new GuessNumber();
		
		String result = guessNumber.guess("1234", "6178");
		
		assertEquals("0A1B", result);	
	}
	@Test
	public void testOneNumberIsCorrectAndRightPlaceWhileAnotherNumberIsJustCorrect() {
		GuessNumber guessNumber = new GuessNumber();
		
		String result = guessNumber.guess("1234", "2134");
		
		assertEquals("2A2B", result);	
	}

}
