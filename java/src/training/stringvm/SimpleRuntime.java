package training.stringvm;

import java.util.Stack;

public class SimpleRuntime {
	private Stack stack = new Stack();

	public void run(Iterable<Command> commands, boolean trace, int breakAt) {
		int line = 0;
		for (Command cmd : commands) {
			cmd.invoke(stack);
			if (trace) {
				System.out.println(cmd.toString());
			}
			if (line == breakAt) {
				return;
			}
			line++;
		}
	}
}
