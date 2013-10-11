package training.stringvm;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

public class StringVMTest {

	/**
	 * As a user I want to add string to string vm 
	 * As a user I want to concatenate strings in string vm 
	 * As a user I want to print strings in string vm
	 */
	@Test
	public void testPush() {
		Stack stack = new Stack();
		Push push = new Push("value");
		push.invoke(stack);
		assertEquals("CMD push \"value\"", push.toString());
		assertEquals("value", stack.pop());
	}
}
