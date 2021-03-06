package exercise.arabicToRoman;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ArabicToRoman2Test {

	private int arabic;
	private String expectedRoman;
	private ArabicToRoman converter = new ArabicToRoman();;

	public ArabicToRoman2Test(int theArabic, String expectedRoman) {
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
