package exercise.fluxx;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by jacky on 15/10/18.
 */
public class GameTest {

    private Player alice;
    private Player bob;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testRuleCards() {
        Card card = new Rule();
        assertEquals(card.text, "");

        card = new Draw(2);
        assertEquals(card.text, "Draw 2");
    }

    @Test
    public void testGoalCards() {
        Card card = new Goal();
        assertEquals(card.text, "");

        card = new TenHands();
        assertEquals(card.text, "10 Cards in Hand");
    }

    @Test
    public void testGameWithBasicRule() {
        Game game = startNewGame();

        game.tick();
        assertEquals(3, alice.hands.size());

        game.tick();
        assertEquals(3, bob.hands.size());

        game.tick();
        assertEquals(3, alice.hands.size());

        game.tick();
        assertEquals(3, bob.hands.size());

        assertEquals(4, game.pool.size());
        assertEquals(4, game.table.size());

//        assertFalse(game.isRunning);

    }

    private Game startNewGame() {
        Game game = new Game();
        alice = new Player("Alice");
        bob = new Player("Bob");
        game.addPlayers(alice, bob);
        game.addToPool(givenCards(8));
        assertEquals(8, game.pool.size());

        game.start();
        assertTrue(game.isRunning);
        assertEquals(3, alice.hands.size());
        assertEquals(3, bob.hands.size());
        return game;
    }

    @Test
    public void testGameWithRuleOfDraw2Play1() {
        Game game = startNewGame();
        //TODO
        game.putOnTable(new Draw(2));

        game.tick();
        assertEquals(3, alice.hands.size());

        game.tick();
        assertEquals(3, bob.hands.size());

        assertEquals(6, game.pool.size());
        assertEquals(2, game.table.size());


    }

    private Card[] givenCards(int n) {
        Card[] cards = new Card[n];
        for (int i = 0; i < n; i++) {
            cards[i] = new Card();
        }
        return cards;
    }
}