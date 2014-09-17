package training.patterns.composite;

public class SizeSpec extends Spec {
	private int size;

	public SizeSpec(int size) {
		this.size = size;
	}

	public boolean isSatisfiedBy(Product product) {
		return this.size == product.size;
	}
}
