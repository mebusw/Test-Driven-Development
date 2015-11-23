package training.args;

import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.*;

public class ArgsTest {

    @Test
    public void testUsage() throws ParseException, ArgsException {
        Args args = new Args("l,p#,d*", new String[] { "-l", "-p", "123", "-d", "ABC" });
        assertEquals("-[l,p#,d*]", args.usage());
    }

    @Test
    public void testGetNonExistingBoolean() throws ParseException, ArgsException {
        Args args = new Args("l,p#,d*", new String[] { "-l" });
        assertEquals(false, args.getBoolean('y'));
    }

    @Test
    public void testGetNonExistingString() throws ParseException, ArgsException {
        Args args = new Args("l,p#,d*", new String[] { "-l" });
        assertEquals("", args.getString('y'));
    }

    @Test
    public void testGetNonExistingInt() throws ParseException, ArgsException {
        Args args = new Args("l,p#,d*", new String[] { "-l" });
        assertEquals(0, args.getInt('y'));
    }

    @Test
    public void testGetNonExistingDouble() throws ParseException, ArgsException {
        Args args = new Args("l,p#,d*", new String[] { "-l" });
        assertEquals(0.0, args.getDouble('y'), .001);
    }

    @Test
    public void testCreateWithNoSchemaOrArguments() throws Exception {
        Args args = new Args("", new String[0]);
        assertEquals(0, args.cardinality());
    }

    @Test
    public void testWithNoSchemaButWithOneArgument() throws Exception {
        try {
            new Args("", new String[] { "-x" });
            fail();
        } catch (ArgsException e) {
            assertEquals(ArgsException.ErrorCode.UNEXPECTED_ARGUMENT, e.getErrorCode());
            assertEquals('x', e.getErrorArgumentId());
        }
    }

    @Test
    public void testWithNoSchemaButWithMultipleArguments() throws Exception {
        try {
            new Args("", new String[] { "-x", "-y" });
            fail();
        } catch (ArgsException e) {
            assertEquals(ArgsException.ErrorCode.UNEXPECTED_ARGUMENT, e.getErrorCode());
            assertEquals('x', e.getErrorArgumentId());
        }
    }

    @Test
    public void testNonLetterSchema() throws Exception {
        try {
            new Args("*", new String[] {});
            fail("Args constructor should have thrown exception");
        } catch (ArgsException e) {
            assertEquals(ArgsException.ErrorCode.INVALID_ARGUMENT_NAME, e.getErrorCode());
            assertEquals('*', e.getErrorArgumentId());
        }
    }

    @Test
    public void testInvalidArgumentFormat() throws Exception {
        try {
            new Args("f~", new String[] {});
            fail("Args constructor should have thrown exception");
        } catch (ArgsException e) {
            assertEquals(ArgsException.ErrorCode.INVALID_FORMAT, e.getErrorCode());
            assertEquals('f', e.getErrorArgumentId());
        }
    }

    @Test
    public void testSimpleBooleanPresent() throws Exception {
        Args args = new Args("x", new String[] { "-x" });
        assertEquals(1, args.cardinality());
        assertEquals(true, args.getBoolean('x'));
    }

    @Test
    public void testSimpleStringPresent() throws Exception {
        Args args = new Args("x*", new String[] { "-x", "param" });
        assertEquals(1, args.cardinality());
        assertTrue(args.has('x'));
        assertEquals("param", args.getString('x'));
    }

    @Test
    public void testMissingStringPresent() throws Exception {
        try {
            new Args("x*", new String[] { "-x" });
            fail();
        } catch (ArgsException e) {
            assertEquals(ArgsException.ErrorCode.MISSING_STRING, e.getErrorCode());
            assertEquals('x', e.getErrorArgumentId());
        }
    }

    @Test
    public void testSpaceInFormat() throws Exception {
        Args args = new Args("x,  y", new String[] { "-xy" });
        assertEquals(2, args.cardinality());
        assertTrue(args.has('x'));
        assertTrue(args.has('y'));
    }

    @Test
    public void testSimpleIntPresent() throws Exception {
        Args args = new Args("x#", new String[] { "-x", "42" });
        assertEquals(1, args.cardinality());
        assertTrue(args.has('x'));
        assertEquals(42, args.getInt('x'));
    }

    @Test
    public void testInvalidInteger() throws Exception {
        try {
            new Args("x#", new String[] { "-x", "Forty Two" });
            fail();
        } catch (ArgsException e) {
            assertEquals(ArgsException.ErrorCode.INVALID_INTEGER, e.getErrorCode());
            assertEquals('x', e.getErrorArgumentId());
            assertEquals("Forty Two", e.getErrorParameter());
        }
    }

    @Test
    public void testMissingInteger() throws Exception {
        try {
            new Args("x#", new String[] { "-x" });
            fail();
        } catch (ArgsException e) {
            assertEquals(ArgsException.ErrorCode.MISSING_INTEGER, e.getErrorCode());
            assertEquals('x', e.getErrorArgumentId());
        }
    }

    @Test
    public void testSimpleDoublePresent() throws Exception {
        Args args = new Args("x##", new String[] { "-x", "42.3" });
        assertEquals(1, args.cardinality());
        assertTrue(args.has('x'));
        assertEquals(42.3, args.getDouble('x'), .001);
    }

    @Test
    public void testInvalidDouble() throws Exception {
        try {
            new Args("x##", new String[] { "-x", "Forty Two" });
            fail();
        } catch (ArgsException e) {
            assertEquals(ArgsException.ErrorCode.INVALID_DOUBLE, e.getErrorCode());
            assertEquals('x', e.getErrorArgumentId());
            assertEquals("Forty Two", e.getErrorParameter());
        }
    }

    @Test
    public void testMissingDouble() throws Exception {
        try {
            new Args("x##", new String[] { "-x" });
            fail();
        } catch (ArgsException e) {
            assertEquals(ArgsException.ErrorCode.MISSING_DOUBLE, e.getErrorCode());
            assertEquals('x', e.getErrorArgumentId());
        }
    }
}
