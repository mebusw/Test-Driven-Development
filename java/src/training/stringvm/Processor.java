package training.stringvm;

/** There will be different processing to each command. */
public interface Processor {
	abstract void process(Command cmd);
}
