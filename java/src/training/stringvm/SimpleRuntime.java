package training.stringvm;

import java.util.Stack;

public class SimpleRuntime implements Runtime {
	private Stack stack = new Stack();
	private int line = 0;

	/* (non-Javadoc)
	 * @see training.stringvm.Runtime#run(java.lang.Iterable, training.stringvm.Processor)
	 */
	public void run(Iterable<Command> commands, Processor invoker) {
		for (Command cmd : commands) {
			invoker.process(cmd);
			line++;
		}
	}

	/* (non-Javadoc)
	 * @see training.stringvm.Runtime#cpu()
	 */
	public Processor cpu() {
		return new Processor() {
			public void process(Command cmd) {
				cmd.invoke(stack);
			}
		};
	}

	/* (non-Javadoc)
	 * @see training.stringvm.Runtime#tracing(training.stringvm.Processor)
	 */
	public Processor tracing(final Processor inner) {
		return new Processor() {
			public void process(Command cmd) {
				System.out.println(line + ": " + cmd);
				inner.process(cmd);
				System.out.println(stack);
			}
		};
	}

	/* (non-Javadoc)
	 * @see training.stringvm.Runtime#breakAt(int, training.stringvm.Processor)
	 */
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

