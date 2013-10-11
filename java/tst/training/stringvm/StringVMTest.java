package training.stringvm;

import static org.junit.Assert.*;

import java.util.Stack;

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
	
	@Test
	public void testConcat() {
		Stack stack = new Stack();
		Push push1 = new Push("head");
		push1.invoke(stack);
		Push push2 = new Push("tail");
		push2.invoke(stack);
		Concat concat = new Concat();
		concat.invoke(stack);
		
		assertEquals("CMD concat", concat.toString());
		assertEquals("headtail", stack.pop());
	}

}
