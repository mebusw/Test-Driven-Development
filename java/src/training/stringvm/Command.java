package training.stringvm;

import java.util.Stack;

public interface Command {

	public abstract void invoke(Stack stack);

}