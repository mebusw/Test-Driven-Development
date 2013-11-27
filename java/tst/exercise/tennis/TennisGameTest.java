package exercise.tennis;

/**
 * Created with IntelliJ IDEA.
 * User: jacky
 * Date: 13-11-27
 * Time: 下午9:18
 * To change this template use File | Settings | File Templates.
 */

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

public class TennisGameTest {

    private TennisGame game;

    @Before
    public void setUp() {
        game = new TennisGame();
    }

    @Test
    public void test_love_love() {
        assertEquals("love All", game.status());

    }

    @Test
    public void test_fifteen_love() {
        game.playerOneScore(1);
        assertEquals("fifteen love", game.status());
    }

    @Test
    public void test_fifteen_thirty() {
        game.playerOneScore(1);
        game.playerTwoScore(2);

        assertEquals("fifteen thirty", game.status());
    }

    @Test
    public void test_forty_fifteen() {
        game.playerOneScore(3);
        game.playerTwoScore(1);

        assertEquals("forty fifteen", game.status());
    }

    @Test
    public void test_win_thirty() {
        game.playerOneScore(3);
        game.playerTwoScore(2);
        game.playerOneScore(1);

        assertEquals("Player One Win", game.status());
    }

    @Test
    public void test_forty_all() {
        game.playerOneScore(3);
        game.playerTwoScore(3);

        assertEquals("forty All", game.status());
    }

    @Test
    public void test_deuce() {
        game.playerOneScore(4);
        game.playerTwoScore(4);

        assertEquals("Deuce", game.status());
    }

    @Test
    public void test_advantage() {
        game.playerOneScore(4);
        game.playerTwoScore(3);

        assertEquals("Player One Advantage", game.status());
    }

    @Test
    public void test_win_after_advantage() {
        game.playerOneScore(4);
        game.playerTwoScore(3);

        game.playerOneScore(1);
        assertEquals("Player One Win", game.status());
    }
}