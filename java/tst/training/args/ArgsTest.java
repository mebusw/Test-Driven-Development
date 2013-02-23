package training.args;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;
import training.args.Args;

public class ArgsTest {

    @Test
    public void testUsage() throws ParseException {
        Args args = new Args("l,p#,d*", new String[] { "-l", "-p123", "-dABC" });
        assertEquals("-[l,p#,d*]", args.usage());
    }

    @Test
    public void testParsingBoolean() throws ParseException {
        Args args = new Args("l,p#,d*", new String[] { "-l" });
        assertEquals(true, args.getBoolean('l'));
    }

    @Test
    public void testGetNonExistingBoolean() throws ParseException {
        Args args = new Args("l,p#,d*", new String[] { "-l" });
        assertEquals(false, args.getBoolean('y'));
    }
    
    @Test
    public void testParsingMultiBoolean() throws ParseException {
        Args args = new Args("l,m", new String[] { "-lm" });
        assertEquals(true, args.getBoolean('l'));
        assertEquals(true, args.getBoolean('m'));
    }

    @Test
    public void testErrorMessageForUnexpectedSchema() throws Exception {
        Args args = new Args("l,p#,d*", new String[] { "-l", "-p123", "-dABC" });
        assertEquals("Arguments(s)  -123ABCp  unexpected", args.errorMessage());
    }
}
