package training.args;

import static org.junit.Assert.*;

import org.junit.Test;
import training.args.Args;

public class ArgsTest {

    @Test
    public void testUsage() {
        Args args = new Args("l,p#,d*", new String[] { "-l", "-p123", "-dABC" });
        assertEquals("-[l,p#,d*]", args.usage());
    }

    @Test
    public void testParsingBoolean() {
        Args args = new Args("l,p#,d*", new String[] { "-l" });
        assertEquals(true, args.getBoolean('l'));
    }

    @Test
    public void testParsingMultiBoolean() {
        Args args = new Args("l,m", new String[] { "-lm" });
        assertEquals(true, args.getBoolean('l'));
        assertEquals(true, args.getBoolean('m'));
    }

    @Test
    public void testErrorMessageForUnexpectedSchema() {
        Args args = new Args("l,p#,d*", new String[] { "-l", "-p123", "-dABC" });
        assertEquals("Arguments(s)  -123ABCdp  unexpected", args.errorMessage());
    }
}
