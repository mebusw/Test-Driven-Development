package exercise.stringCaculator;

import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;


public class StringCalculator {

    private String input;
    private String delimiter;

    public StringCalculator(String input, String delimiter) {
        this.input = input;
        this.delimiter = delimiter;
    }

    public static int sum(String input) {
        if (input.equals(""))
            return 0;

        String delimiter = ",|\n";
        if (input.startsWith("//")) {
            String[] parts = input.split("\n");
            delimiter = Pattern.quote(parts[0].substring(2));
            input = parts[1];
        }
        StringCalculator sc = new StringCalculator(input, delimiter);
        return sc.resolve();
    }

    private int resolve() {
        String negtives = getNumbers().filter(x -> x < 0).mapToObj(String::valueOf).collect(joining(", "));
        if (negtives.length() > 0) {
            throw new IllegalArgumentException("Contains Negtives: " + negtives);
        }
        return getNumbers().sum();
    }

    private IntStream getNumbers() {
        return Stream.of(input.split(delimiter)).mapToInt(Integer::parseInt).map(x -> x % 1000);
    }
}
