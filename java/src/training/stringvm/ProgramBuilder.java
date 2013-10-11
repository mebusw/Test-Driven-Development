package training.stringvm;

import java.util.ArrayList;
import java.util.Stack;

public class ProgramBuilder {
	ArrayList<Command> program = new ArrayList<Command>();

	public ProgramBuilder push(String value) {
		program.add(new Push(value));
		return this;
	}

	public ProgramBuilder concat() {
		program.add(new Concat());
		return this;
	}

	public ProgramBuilder print() {
		program.add(new Print());
		return this;
	}

	public Iterable<Command> getProgram() {
		return program;
	}
	

}
