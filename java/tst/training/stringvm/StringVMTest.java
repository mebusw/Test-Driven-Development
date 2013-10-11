package training.stringvm;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import junit.framework.Assert;

import org.junit.Test;

public class StringVMTest {

	/**
	 * As a user I want to add string to string vm As a user I want to
	 * concatenate strings in string vm As a user I want to print strings in
	 * string vm
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

	@Test
	public void testPrint() {
		Stack stack = new Stack();
		Push push = new Push("value");
		Print print = new Print();
		print.invoke(stack);
		assertEquals("CMD print", print.toString());

	}

	/* below turn out to a builder */
	@Test
	public void testSimpleRuntime() {
		List<Command> commands = new ArrayList<Command>();
		commands.add(new Push("head"));
		commands.add(new Push("tail"));
		commands.add(new Concat());
		commands.add(new Print());

		new SimpleRuntime().run(commands);

	}

	@Test
	public void testProgramBuilder() {
		Iterable<Command> program = new ProgramBuilder().push("Hello")
				.push(" World!").concat().print().getProgram();
		new SimpleRuntime().run(program);
	}
}
