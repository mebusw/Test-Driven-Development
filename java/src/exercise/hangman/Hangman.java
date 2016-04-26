package exercise.hangman;

import static java.util.stream.Collectors.joining;

public class Hangman {
    public static final int MOST_TRIES = 12;
    public String word;
    private String VOWELS = "AEIOU";
    private String used = VOWELS;
    private int tries;

    public Hangman(String word) {
        this.word = word;
        this.tries = MOST_TRIES;
    }


    public int length() {
        return word.length();
    }

    public String used() {
        return used;
    }

    private boolean isExpectedChar(char c) {
        return word.contains(String.valueOf(c));
    }

    private boolean isUnusedChar(char c) {
        return !used.contains(String.valueOf(c));
    }

    public int tries() {
        return tries;
    }

    public void tap(char c, Runnable gameWin, Runnable gameOver) {
        if (!isUnusedChar(c) || !isExpectedChar(c)) {
            this.tries -= 1;
        }
        if (isUnusedChar(c)) {
            used += c;
        }

        if (word.equals(discovered())) {
            gameWin.run();
        }
        if (tries <= 0) {
            gameOver.run();
        }
    }

    public String discovered() {
        // return word.chars().mapToObj(i -> (char) i).map(this::detect).collect(joining());
        return word.chars().mapToObj(i -> (char) i).map(c -> {
                    if (used.indexOf(c) > -1)
                        return String.valueOf(c);
                    return "_";
                }
        ).collect(joining());
    }


    private String detect(char c) {
        if (used.indexOf(c) > -1)
            return String.valueOf(c);
        return "_";
    }

}
