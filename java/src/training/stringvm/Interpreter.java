package training.stringvm;

public class Interpreter {

	public void run(Iterable<Command> program, boolean optimized,
			boolean trace, int breakPoint) {
		Runtime runtime = optimized ? new OptimizedRuntime()
				: new SimpleRuntime();
		Processor processor = runtime.cpu();
		processor = trace ? runtime.tracing(processor) : processor;
		processor = breakPoint >= 0 ? runtime.breakAt(breakPoint, processor)
				: processor;
		runtime.run(program, processor);
	}

	public Iterable<Command> parse(String sourceCode) {
		return null;
	}
}
