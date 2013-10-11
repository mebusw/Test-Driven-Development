package training.stringvm;

import java.util.Stack;

public class SimpleRuntime {
	private Stack stack = new Stack();

	public void run(Iterable<Command> commands) {
		for (Command cmd : commands) {
			cmd.invoke(stack);
			System.out.println(cmd.toString());
		}
	}
}
