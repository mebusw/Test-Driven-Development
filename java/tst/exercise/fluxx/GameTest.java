package exercise.fluxx;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

/**
 * Created by jacky on 15/10/18.
 */
public class GameTest {

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

}