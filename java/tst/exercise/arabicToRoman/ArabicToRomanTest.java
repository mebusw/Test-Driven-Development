package exercise.arabicToRoman;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ArabicToRomanTest {

	private int arabic;
	private String expectedRoman;
	private ArabicToRoman converter = new ArabicToRoman();;

	public ArabicToRomanTest(int theArabic, String expectedRoman) {
		arabic = theArabic;
		this.expectedRoman = expectedRoman;
	}

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { 
				{ 1, "I" }, 
				{ 5, "V" }, 
				{ 2, "II" }, 
				{ 3, "III" }, 
				{ 4, "IV" }, 
				{ 6, "VI" }, 
				{ 0, "" }, 
				{ 9, "IX" }, 
				{ 10, "X" }, 
				{ 40, "XL" }, 
				{ 50, "L" },
				{ 90, "XC" }, 
				{ 100, "C" }, 
				{ 400, "CD" }, 
				{ 500, "D" }, 
				{ 900, "CM" }, 
				{ 1000, "M" }, 
				{ 2457, "MMCDLVII" }, 
			});
	}

	@Test
	public void convert_arabic_to_roman() {
		String actual = converter.convert(arabic);

		assertEquals(expectedRoman, actual);
	}

}
