package training.patterns.decorator;

public class StringNode extends Node {

	public StringNode(String text) {
		this.text = text;
	}

	public static StringNode newNode(String text, boolean shouldEscape) {
		if (shouldEscape) {
			text = text.replaceAll("%20", " ");
		}

		return new StringNode(text);
	}

	public String toString() {
		return this.text;
	}

}
