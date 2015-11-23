package training.args;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArgsExceptionTest {

    @Test
    public void testUnexpectedMessage() throws Exception {
        ArgsException e = new ArgsException(ArgsException.ErrorCode.UNEXPECTED_ARGUMENT, 'x', null);
        assertEquals("Argument -x unexpected.", e.errorMessage());
    }

    @Test
    public void testMissingStringMessage() throws Exception {
        ArgsException e = new ArgsException(ArgsException.ErrorCode.MISSING_STRING, 'x', null);
        assertEquals("Could not find string parameter for -x.", e.errorMessage());
    }

    @Test
    public void testinvalidIntegerMessage() throws Exception {
        ArgsException e = new ArgsException(ArgsException.ErrorCode.INVALID_INTEGER, 'x', "Forty Two");
        assertEquals("Argument -x expects an integer but was 'Forty Two'.", e.errorMessage());
    }

    @Test
    public void testMissingIntegerMessage() throws Exception {
        ArgsException e = new ArgsException(ArgsException.ErrorCode.MISSING_INTEGER, 'x', null);
        assertEquals("Could not find integer parameter for -x.", e.errorMessage());
    }
    
    @Test
    public void testinvalidDoubleMessage() throws Exception {
        ArgsException e = new ArgsException(ArgsException.ErrorCode.INVALID_DOUBLE, 'x', "Forty Two");
        assertEquals("Argument -x expects a double but was 'Forty Two'.", e.errorMessage());
    }

    @Test
    public void testMissingDoubleMessage() throws Exception {
        ArgsException e = new ArgsException(ArgsException.ErrorCode.MISSING_DOUBLE, 'x', null);
        assertEquals("Could not find double parameter for -x.", e.errorMessage());
    }
}
