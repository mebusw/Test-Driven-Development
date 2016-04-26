package exercise.hangman;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

/**
 * hangman-easy java8   http://www.twoplayergames.org/play/668-Hangman.html
 * length
 * used
 * discovered answer
 * tries
 * gamewin
 * gameover
 * mock callback => type().checkWin()
 */
public class HangmanTest {


    private Hangman game;
    private Runnable gameWin = () -> {
    };
    private Runnable gameOver = () -> {
    };


    @Before
    public void setUp() {
        game = new Hangman("XYZ");
    }

    @Test
    public void length_of_new_game() {
        assertThat(game.length(), is(3));
    }


    @Test
    public void used_of_new_game_are_all_vowels() {
        assertThat(game.used(), is("AEIOU"));
    }


    @Test
    public void used_of_typing_a_uncontained_consonant() {
        game.tap('N', gameWin, gameOver);

        assertThat(game.used(), is("AEIOUN"));
    }

    @Test
    public void used_of_typing_a_contained_consonant() {
        game.tap('X', gameWin, gameOver);

        assertThat(game.used(), is("AEIOU" + "X"));
    }

    @Test
    public void used_of_typing_a_contained_but_used_consonant() {
        game.tap('X', gameWin, gameOver);

        game.tap('X', gameWin, gameOver);

        assertThat(game.used(), is("AEIOU" + "X"));
    }

    @Test
    public void tries_when_start() {
        assertThat(game.tries(), is(12));
    }

    @Test
    public void tries_decreased_when_tap_an_uncontained_char() {
        game.tap('N', gameWin, gameOver);

        assertThat(game.tries(), is(11));
        assertThat(game.used(), is("AEIOU" + "N"));
    }

    @Test
    public void tries_remained_when_tap_an_contained_char() {
        game.tap('Y', gameWin, gameOver);

        assertThat(game.tries(), is(12));
        assertThat(game.used(), is("AEIOU" + "Y"));
    }

    @Test
    public void tries_decreased_when_tap_an_contained_char_twice() {
        game.tap('Y', gameWin, gameOver);

        game.tap('Y', gameWin, gameOver);

        assertThat(game.tries(), is(11));
        assertThat(game.used(), is("AEIOU" + "Y"));
    }


    @Test
    public void discovered_at_start_with_all_vowels() {
        game = new Hangman("AE");

        assertThat(game.discovered(), is("AE"));
    }

    @Test
    public void discovered_at_start_with_consonant() {
        game = new Hangman("AB");

        assertThat(game.discovered(), is("A_"));
    }

    @Test
    public void game_not_win_or_over_at_start() {
        gameWin = mock(Runnable.class);
        gameOver = mock(Runnable.class);

        game.tap('Y', gameWin, gameOver);

        verify(gameWin, never()).run();
        verify(gameOver, never()).run();
    }

    @Test
    public void game_win_when_all_char_discovered() {
        gameWin = mock(Runnable.class);

        game.tap('X', gameWin, ()->{});
        game.tap('Y', gameWin, ()->{});
        game.tap('Z', gameWin, ()->{});

        verify(gameWin, times(1)).run();
    }

    @Test
    public void game_over_when_no_more_tries() {
        gameOver = mock(Runnable.class);

        IntStream.range(0,game.MOST_TRIES).forEach(i->game.tap('N', ()->{}, gameOver));

        verify(gameOver, times(1)).run();
    }


}