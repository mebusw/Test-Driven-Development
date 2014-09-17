package training.patterns.decorator;

public class StringParser {

	public Node parse(String xml, boolean shouldEscape) {
		int o1 = xml.indexOf("<");
		int o2 = xml.indexOf(">");
		int c1 = xml.lastIndexOf("<");
		int c2 = xml.lastIndexOf(">");

		String text = xml.substring(o2 + 1, c1);
		return StringNode.newNode(text, shouldEscape);
	}
}
