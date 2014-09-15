package exercise.arabicToRoman;

import java.util.Collection;
import java.util.Collections;
import java.util.SortedMap;
import java.util.TreeMap;

public class ArabicToRoman {

	private static final SortedMap<Integer, String> SYMBOLS = new TreeMap<Integer, String>(
			Collections.reverseOrder());
	static {
		SYMBOLS.put(1, "I");
		SYMBOLS.put(4, "IV");
		SYMBOLS.put(5, "V");
		SYMBOLS.put(9, "IX");
		SYMBOLS.put(10, "X");
		SYMBOLS.put(40, "XL");
		SYMBOLS.put(50, "L");
		SYMBOLS.put(90, "XC");
		SYMBOLS.put(100, "C");
		SYMBOLS.put(400, "CD");
		SYMBOLS.put(500, "D");
		SYMBOLS.put(900, "CM");
		SYMBOLS.put(1000, "M");
	}

	public String convert(int arabic) {
		if (arabic == 0)
			return "";

		int largestSymbolOfArabicLessThanInput = SYMBOLS.tailMap(arabic)
				.firstKey();
		return SYMBOLS.get(largestSymbolOfArabicLessThanInput)
				+ convert(arabic - largestSymbolOfArabicLessThanInput);
	}

}
