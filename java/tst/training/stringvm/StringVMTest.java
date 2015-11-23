package training.stringvm;

import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.assertEquals;

public class StringVMTest {

	/**
	 * As a user I want to add string to string vm.
	 */
	/**
	 * As a user I want to concatenate strings in string vm.
	 */
	/**
	 * As a user I want to print strings in string vm.
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

	/**
	 * As a user I want to trace the vm execution (print out the current command
	 * and stack) so I can debug my program
	 */

	/**
	 * As a user I want to trace the vm execution (print out the current command
	 * and stack) so I can debug my program As a user I want to set a breakpoint
	 * in string vm so that I can stop my program at any point
	 */

	@Test
	public void testProgramBuilder() {
		Iterable<Command> program = new ProgramBuilder().push("Hello")
				.push(" World!").concat().print().getProgram();
		Runtime runtime = new SimpleRuntime();
		runtime.run(program, runtime.breakAt(4, runtime.tracing(runtime.cpu())));
	}

	/** As a user I want to execute my program in optimized manner when needed */
	/** Separate construction and usage */
	@Test
	public void testOptimizedRuntime() {
		Iterable<Command> program = new ProgramBuilder().push("Hello")
				.push(" World!").concat().print().getProgram();
		Runtime runtime = new OptimizedRuntime();
		runtime.run(program, runtime.breakAt(4, runtime.tracing(runtime.cpu())));
	}

	/** Facade */
	@Test
	public void testInterpreter() {
		Iterable<Command> program = new ProgramBuilder().push("Hello")
				.push(" World!").concat().print().getProgram();

		Interpreter i = new Interpreter();
		i.run(i.parse(""), true, true, 4);
	}

}
