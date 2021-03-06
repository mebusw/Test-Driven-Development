package exercise.circularBuffer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Copyright (c) mebusw@163.com, the test cases are learned from James Grenning's
 * TDDinC exercise, translated into Java
 * 
 */

public class CircularBufferTest {

    private CircularBuffer buffer;

    @Before
    public void setUp() throws Exception {
        // buffer = new CircularBuffer();
        buffer = new CircularBuffer(500);
    }

    @After
    public void tearDown() throws Exception {
        buffer.destroy();
    }

    @Test
    public void test() {
        // fail("Not yet implemented");
    }

    /**
     * TDD Exercise
     * 
     * The purpose of this exercise is to give you a feel for pair programming
     * with an experienced TDD developer
     * 
     * When starting, brain storm a list of needed tests. Here is the initial
     * list we came up with: Empty Not empty Simple put and get setable capactiy
     * totally full, but no more overfilled with wrap around underflow, taking
     * items out of an empty buffer
     * 
     * The process is write a failing unit test make it compile make it link run
     * the test and see it fail add simplest code to make the test pass
     * 
     * The CircularBuffer files were created using the NewCModule.sh script like
     * this: NewCModule CircularBuffer
     * 
     * The initial state of this code is failing Delete the FAIL macro call,
     * compile and see that all tests pass
     * 
     * To get the feel of TDD enable only one test at a time Like this:
     * 
     * Move the #if down the page. Study the test, make sure you understand what
     * the test is trying to accomplish. You are pretending to pair program with
     * me. I just wrote the first test, you need to make it pass.
     * 
     * Get the test to compile. This usually means just getting the header file
     * right. Once the header file is right you will have a linker error.
     * 
     * Next add the simplest implementation that will fail to get rid of the
     * linker error. Watch the test fail.
     * 
     * Next do the minimum code necessary to get the test to pass. Sometimes
     * this means hardcoding a return value. Don't let the code get ahead of the
     * tests.
     * 
     * Then go to the next test.
     * 
     * You will find some more instruction down the page
     */

    // TODO Add @Test to test function to enable it
    @Test
    public void testEmptyAfterCreation() {
        assertTrue(buffer.isEmpty());
    }

    @Test
    public void testNotFullAfterCreation() {
        assertFalse(buffer.isFull());
    }

    /**
     * To get the prior two tests passing all you need are the function
     * prototypes in the header with hard coded return results in the C file.
     * Please delete any other code you have written.
     */

    @Test
    public void testNotEmpty() {
        buffer.put(10046);
        assertFalse(buffer.isEmpty());
    }

    @Test
    public void testNotEmptyThenEmpty() {
        buffer.put(4567);
        assertFalse(buffer.isEmpty());
        buffer.get();
        assertTrue(buffer.isEmpty());
    }

    @Test
    public void testGetPutOneValue() {
        buffer.put(4567);
        assertEquals(4567, buffer.get());
    }

    /**
     * With the previous tests passing you should have at most a counter that
     * knows how many ints have been added to the buffer, with hard coded return
     * value for Get()
     * 
     * If you have more, delete it now! it is not tested code, you are supposed
     * to be doing TDD!
     */

    @Test
    public void testGetPutAFew() {
        buffer.put(1);
        buffer.put(2);
        buffer.put(3);
        assertEquals(1, buffer.get());
        assertEquals(2, buffer.get());
        assertEquals(3, buffer.get());
    }

    /**
     * The previous test has driven you to have a simple internal array of fixed
     * size, an index and an outdex. There should be no circular buffer logic
     * yet. Why? your tests do not require it. Delete untested code now!
     */

    @Test
    public void testCapacity() {
        buffer = new CircularBuffer(2);
        assertEquals(2, buffer.capacity());
    }

    /**
     * With the Capacity test, the simple array should be replaced with an array
     * created using new. Did you get a memory leak? If you did not get a leak,
     * remove the free from CircularBuffer_Destroy to see what happens. Did you
     * change CircularBuffer_Create to accept a parameter?
     */

    @Test
    public void testIsFull() {
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put(i + 100);
        }
        assertTrue(buffer.isFull());
    }

    /**
     * With the EmptyToFullToEmpty test there is still no need for the wrap
     * around logic. If you have wrap around logic already, please delete the
     * untested code. How many times do I have to tell you?!
     */

    @Test
    public void testEmptyToFullToEmpty() {
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put(i + 100);
        }
        assertTrue(buffer.isFull());

        for (int i = 0; i < buffer.capacity(); i++) {
            assertEquals(i + 100, buffer.get());
        }
        assertTrue(buffer.isEmpty());
        assertFalse(buffer.isFull());
    }

    /**
     * Did that test pass without changes to the production code?
     * 
     * Finally, the wrap around test. Sometimes this next test will crash the
     * tests when your buffer boundary is violated.
     */

    @Test
    public void testWrapAround() {
        int capacity = buffer.capacity();
        for (int i = 0; i < capacity; i++) {
            buffer.put(i + 100);
        }
        assertTrue(buffer.isFull());
        assertEquals(100, buffer.get());
        assertFalse(buffer.isFull());
        buffer.put(1000);
        assertTrue(buffer.isFull());

        for (int i = 1; i < buffer.capacity(); i++) {
            assertEquals(i + 100, buffer.get());
        }
        assertEquals(1000, buffer.get());
        assertTrue(buffer.isEmpty());
    }

    /**
     * Did Wrap around test pass without change? Beware, you are trashing
     * something.
     * 
     * By now you should be sick of writing the fill loop over and over again.
     * Refactor the loop into the fillTheQueue function, add it to the
     * TEST_GROUP, and get rid of all the duplication.
     * 
     * You should not have any production code that is worried about doing a Get
     * from an empty buffer, or Putting to a full buffer. TDD, remember?
     * 
     */

    @Test
    public void testPutToFullFails() {
        fillTheQueue(900, buffer.capacity());
        assertFalse(buffer.put(9999));
    }

    private void fillTheQueue(int start, int capacity) {
        for (int i = 0; i < capacity; i++) {
            buffer.put(i + start);
        }

    }

    @Test
    public void testPutToFullDoesNotDamageContents() {
        fillTheQueue(900, buffer.capacity());
        buffer.put(9999);

        for (int i = 0; i < buffer.capacity(); i++) {
            assertEquals(i + 900, buffer.get());
        }
        assertTrue(buffer.isEmpty());
    }

    /**
     * Don't you think you should refactor that duplicate index wrapping in the
     * production code into a helper function?
     * 
     */

    @Test
    public void testGetFromEmptyReturns0_WeHaveToDoSomething() {
        assertEquals(0, buffer.get());
        assertTrue(buffer.isEmpty());
        assertFalse(buffer.isFull());
    }

    /**
     * Congratulations!
     * 
     * Look for test refactoring opportunities.
     * 
     * Did you get rid of all those unneeded comments?
     * 
     * Do it again next week except without my tests to guide you
     */

    @Test
    public void testPrintEmpty() {
        String expectedOutput = "Circular buffer content:\n<>\n";
        assertEquals(expectedOutput, buffer.print());
    }

    /**
     * You should have only printed the heading. this next test drives you to
     * get the format of a single element. Did you implement any of the wrap
     * around logic in your print function? Delete it if you did.
     */

    @Test
    public void testPrintAfterOneIsPut() {
        String expectedOutput = "Circular buffer content:\n<1>\n";
        buffer.put(1);
        assertEquals(expectedOutput, buffer.print());

    }

    @Test
    public void testPrintNotYetWrappedOrFull() {
        String expectedOutput = "Circular buffer content:\n<1, 2, 3>\n";
        buffer.put(1);
        buffer.put(2);
        buffer.put(3);
        assertEquals(expectedOutput, buffer.print());

    }

    @Test
    public void testPrintNotYetWrappedAndIsFull() {
        String expectedOutput = "Circular buffer content:\n<200, 201, 202, 203, 204>\n";
        buffer = new CircularBuffer(5);
        buffer.put(200);
        buffer.put(201);
        buffer.put(202);
        buffer.put(203);
        buffer.put(204);
        assertEquals(expectedOutput, buffer.print());
    }

    /**
     * Print does not need to handle wrapping until this next test. I am glad to
     * hear you resisted the temptation to code ahead of your tests
     */

    @Test
    public void testPrintWrappedAndIsFullOldestToNewest() {
        String expectedOutput = "Circular buffer content:\n<201, 202, 203, 204, 999>\n";
        buffer = new CircularBuffer(5);
        buffer.put(200);
        buffer.put(201);
        buffer.put(202);
        buffer.put(203);
        buffer.put(204);
        buffer.get();
        buffer.put(999);
        assertEquals(expectedOutput, buffer.print());
    }

    @Test
    public void testFillThenEmptyThenPrint() {
        String expectedOutput = "Circular buffer content:\n<>\n";
        buffer = new CircularBuffer(5);
        fillTheQueue(200, 5);

        for (int i = 0; i < 5; i++) {
            buffer.get();
        }
        assertEquals(expectedOutput, buffer.print());
    }
}
