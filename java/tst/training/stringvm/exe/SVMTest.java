package training.stringvm.exe;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

import static org.junit.Assert.assertEquals;

public class SVMTest {

	@Test
	public void testPush() {
		Stack<String> stack = new Stack<String>();
		Command push = new Push("Hello");

		push.invoke(stack);

		assertEquals("Hello", stack.pop());
	}

	@Test
	public void testConcat() {
		Stack<String> stack = new Stack<String>();
		new Push("Hello").invoke(stack);
		new Push(" World").invoke(stack);
		Concat concat = new Concat();
		concat.invoke(stack);

		assertEquals("Hello World", stack.pop());
	}

	@Test
	public void testSwap() {
		Stack<String> stack = new Stack<String>();
		new Push("Hello").invoke(stack);
		new Push(" World").invoke(stack);
		Command swap = new Swap();
		swap.invoke(stack);

		assertEquals("Hello", stack.pop());
		assertEquals(" World", stack.pop());

	}

	@Test
	public void testSimpleRuntime() {
		SimpleRuntime runtime = new SimpleRuntime();

		Iterable<? extends Command> commands = Arrays
				.asList(new Push(" World"), new Push("Hello"), new Swap(),
						new Concat());

		runtime.run(commands);
		assertEquals("Hello World", runtime.result());

	}

	@Test
	public void testProgramBuilder() {
		SimpleRuntime runtime = new SimpleRuntime();

		Iterable<? extends Command> commands = new ProgramBuilder()
				.push(" World").push("Hello").swap().concat().getProgram();

		runtime.run(commands);
		assertEquals("Hello World", runtime.result());

	}

	@Test
	public void testTraceAndBreakpoint() {
		SimpleRuntime runtime = new SimpleRuntime();

		Iterable<? extends Command> commands = new ProgramBuilder()
				.push(" World").push("Hello").swap().concat().getProgram();

		runtime.run(commands, true, -1);
		assertEquals("Hello World", runtime.result());
	}

	@Test
	public void testProcessors() {
		SimpleRuntime runtime = new SimpleRuntime();

		Iterable<? extends Command> commands = new ProgramBuilder()
				.push(" World").push("Hello").swap().concat().getProgram();

		runtime.run(commands, runtime.breatAt(8, runtime.trace(runtime.cpu())));
		assertEquals("Hello World", runtime.result());
	}
}

class ProgramBuilder {
	private ArrayList<Command> commands = new ArrayList<Command>();

	public Iterable<? extends Command> getProgram() {
		return commands;
	}

	public ProgramBuilder push(String string) {
		commands.add(new Push(string));
		return this;
	}

	public ProgramBuilder swap() {
		commands.add(new Swap());
		return this;
	}

	public ProgramBuilder concat() {
		commands.add(new Concat());
		return this;
	}
}

class SimpleRuntime {
	private Stack<String> stack = new Stack<String>();
	private int line = 0;

	public void run(Iterable<? extends Command> commands) {
		for (Command command : commands) {
			command.invoke(stack);
		}
	}

	public void run(Iterable<? extends Command> commands, Processor processor) {
		for (Command command : commands) {
			processor.process(command);
		}

	}

	public void run(Iterable<? extends Command> commands, boolean trace,
			int breakAt) {
		for (Command command : commands) {
			command.invoke(stack);
			if (trace) {
				System.out.println(command.toString());
			}
			if (line == breakAt) {
				return;
			}
			line++;
		}
	}

	public String result() {
		return stack.pop();
	}

	public Processor cpu() {
		return new Processor() {
			public void process(Command cmd) {
				cmd.invoke(stack);
			}
		};
	}

	public Processor trace(final Processor inner) {
		return new Processor() {
			public void process(Command cmd) {
				System.out.println(cmd.toString());
				inner.process(cmd);
			}
		};
	}

	public Processor breatAt(final int breakpoint, final Processor inner) {
		return new Processor() {
			public void process(Command cmd) {
				if (line < breakpoint)
					inner.process(cmd);
			}
		};
	}
}

interface Processor {
	public void process(Command cmd);
}

interface Command {

	public void invoke(Stack<String> stack);

}

class Push implements Command {
	private String s;

	public Push(String s) {
		this.s = s;
	}

	public void invoke(Stack<String> stack) {
		stack.push(this.s);
	}
}

class Concat implements Command {
	public Concat() {
	}

	public void invoke(Stack<String> stack) {
		String s1 = stack.pop();
		String s2 = stack.pop();
		stack.push(s2 + s1);
	}
}

class Swap implements Command {

	public Swap() {
	}

	@Override
	public void invoke(Stack<String> stack) {
		String s1 = stack.pop();
		String s2 = stack.pop();
		stack.push(s1);
		stack.push(s2);
	}

}