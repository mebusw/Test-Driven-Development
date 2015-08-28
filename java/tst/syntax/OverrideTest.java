package syntax;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OverrideTest {

    private Parent p1;
    private Child c1;
    private Parent p2;

    @Before
    public void setUp() throws Exception {
        p1 = new Child();
        c1 = (Child) p1;
        p2 = (Parent) p1;
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testOverrideIsDecidedWhenRuntime() {
        assertEquals("Child Print Integer.", c1.print(new Integer(10)));
        assertEquals("Child Print Integer.", p1.print(new Integer(10)));
        assertEquals("Child Print Integer.", p2.print(new Integer(10)));

    }

    @Test
    public void testOverloadIsDecidedWhenCompile() {
        assertEquals("Process As Parent.", process(p1));
        assertEquals("Process As Child.", process(c1));
        assertEquals("Process As Parent.", process(p2));

    }

    private static String process(Child C) {
        return "Process As Child.";
    }

    private static String process(Parent P) {
        return "Process As Parent.";
    }

}

class Parent {
    public String print(Integer s) {
        return "Parent Print Integer.";
    }

    public String print(String s) {
        return "Parent Print String.";
    }
}

/**
 * "Override" is for compiler to check if it's an overrided method
 * 
 */
class Child extends Parent {
    @Override
    public String print(Integer s) {
        return "Child Print Integer.";
    }

    public String print(String s) {
        return "Child Print String.";
    }
}
