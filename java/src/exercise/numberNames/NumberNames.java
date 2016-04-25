package exercise.numberNames;

import java.util.function.Function;

public class NumberNames {

    public static final int MILLION = 1000000;
    public static final int THOUSAND = 1000;
    public static final int HUNDRED = 100;
    public static final int TEN = 10;
    private static String[] LITERALS = new String[]{
            "zero", "one", "two", "three", "four",
            "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
    private static String[] TYS = new String[]{"zero", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};


    public static String translate(int num) {
        if (num >= MILLION) return translate(num / MILLION) + " million" + remnant(MILLION, ", ").apply(num);
        if (num >= THOUSAND) return translate(num / THOUSAND) + " thousand" + remnant(THOUSAND, ", ").apply(num);
        if (num >= HUNDRED) return LITERALS[(num / HUNDRED)] + " hundred" + remnant(HUNDRED, " and ").apply(num);
        if (num >= 20) return TYS[(num / TEN)] + remnant(TEN, " ").apply(num);
        return LITERALS[num];
    }

    private static Function<Integer, String> remnant(int unit, String separator) {
        return n -> n % unit != 0 ? separator + translate(n % unit) : "";
    }


}
