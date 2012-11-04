package syntax;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BoxingTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * boxing: primitive->object jdk 1.4.2:
     * 
     * <pre>
     * Integer iWrapper = new Integer(10);
     * </pre>
     * 
     * jdk 1.5+:
     * 
     * <pre>
     * Integer iWrapper = 10;
     * </pre>
     * 
     * unboxing: object->primitive jdk 1.4.2:
     * 
     * <pre>
     * int iPrimitive = iWrapper.intValue();
     * </pre>
     * 
     * jdk 1.5+:
     * 
     * <pre>
     * int iPrimitive = iWrapper;
     * </pre>
     * 
     * VM will use same object if:<br>
     * boolean values true and false. All byte values short values between -128
     * and 127. int values between -128 and 127. char in the range \u0000 to
     * \u007F.
     */
    @Test
    public void testBoxing() {
        Integer i1 = 100;
        Integer i2 = 100;
        Integer i3 = 1000;
        Integer i4 = 1000;
        assertTrue(i1 == i2);
        assertFalse(i3 == i4);
        assertTrue(0L == 0);
        assertFalse(((Long) 0L).equals(0));

    }

}
