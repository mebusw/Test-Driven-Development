package training.stringvm;

import java.util.Stack;

public class Concat implements Command {
	public String toString() {
		return "CMD concat";
	}

	public void invoke(Stack stack) {
		String tail = (String) stack.pop();
		String head = (String) stack.pop();
		stack.push(head + tail);
	}
}
