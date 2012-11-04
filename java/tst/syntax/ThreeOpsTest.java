package syntax;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ThreeOpsTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {
        People p1 = new People(21, "Lucky", true);
        String answer1 = People.getDescription(p1);
        People p2 = new People(16, "Sunny", false);
        String answer2 = People.getDescription(p2);

        assertEquals("Monday", answer1);
        assertEquals("Wednesday", answer2);

    }

}

class People {
    private int age;
    private String name;
    private boolean isMarried;

    public People(int age, String name, boolean isMarried) {
        this.age = age;
        this.name = name;
        this.isMarried = isMarried;
    }

    public static String getDescription(People p) {
        return p != null ? (p.age > 20 ? (p.isMarried ? "Monday" : "Sunday")
                : (p.name != null ? "Wednesday" : "Friday")) : "Thursday";
    }

}