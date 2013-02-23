package training.args;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;
import training.args.Args;

public class ArgsTest {

    @Test
    public void testUsage() throws ParseException, ArgsException {
        Args args = new Args("l,p#,d*", new String[] { "-l", "-p", "123", "-d", "ABC" });
        assertEquals("-[l,p#,d*]", args.usage());
    }

    @Test
    public void testParsingBoolean() throws ParseException, ArgsException {
        Args args = new Args("l,p#,d*", new String[] { "-l" });
        assertEquals(true, args.getBoolean('l'));
    }

    @Test
    public void testGetNonExistingBoolean() throws ParseException, ArgsException {
        Args args = new Args("l,p#,d*", new String[] { "-l" });
        assertEquals(false, args.getBoolean('y'));
    }
    
    @Test
    public void testParsingMultiBoolean() throws ParseException, ArgsException {
        Args args = new Args("l,m", new String[] { "-lm" });
        assertEquals(true, args.getBoolean('l'));
        assertEquals(true, args.getBoolean('m'));
    }

    @Test
    public void testErrorMessageForUnexpectedSchema() throws Exception {
        Args args = new Args("l,p#,d*", new String[] { "-l", "-p", "123", "-d", "ABC", "-y" });
        assertEquals("Arguments(s)  -y  unexpected", args.errorMessage());
    }
}
