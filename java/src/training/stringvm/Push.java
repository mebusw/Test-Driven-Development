package training.stringvm;

import java.util.Stack;

public class Push {

	private String value;

	public Push(String value) {
		this.value = value;
	}

	public void invoke(Stack stack) {
		stack.push(value);
	}

	public String toString() {
		return "CMD push \"" + value + "\"";
	}
}
