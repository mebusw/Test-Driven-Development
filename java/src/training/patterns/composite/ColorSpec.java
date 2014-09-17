package training.patterns.composite;

public class ColorSpec extends Spec {
	private String color;

	public ColorSpec(String color) {
		this.color = color;
	}

	public boolean isSatisfiedBy(Product product) {
		return this.color.equals(product.color);
	}
}
