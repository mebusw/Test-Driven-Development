package training.stringvm;

import java.util.Stack;

public class SimpleRuntime {
	private Stack stack = new Stack();
	private int line = 0;

	public void run(Iterable<Command> commands, Processor invoker) {
		for (Command cmd : commands) {
			invoker.process(cmd);
			line++;
		}
	}

	public Processor cpu() {
		return new Processor() {
			public void process(Command cmd) {
				cmd.invoke(stack);
			}
		};
	}

	public Processor tracing(final Processor inner) {
		return new Processor() {
			public void process(Command cmd) {
				System.out.println(line + ": " + cmd);
				inner.process(cmd);
				System.out.println(stack);
			}
		};
	}

	public Processor breakAt(final int breakPoint, final Processor inner) {
		return new Processor() {
			public void process(Command cmd) {
				if (line < breakPoint) {
					inner.process(cmd);
				}
			}
		};
	}
}

