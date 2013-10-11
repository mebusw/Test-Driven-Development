package training.stringvm;

public interface Runtime {

	public void run(Iterable<Command> commands, Processor invoker);

	public Processor cpu();

	public Processor tracing(Processor inner);

	public Processor breakAt(int breakPoint, Processor inner);

}