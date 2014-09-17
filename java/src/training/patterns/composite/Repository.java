package training.patterns.composite;

import java.util.ArrayList;
import java.util.List;

public class Repository {

	private List<Product> products = new ArrayList();

	public void add(Product product) {
		this.products.add(product);
	}

	public List<Product> selectBy(Spec spec) {
		List<Product> result = new ArrayList();
		for (Product product : products) {
			if (spec.isSatisfiedBy(product))
				result.add(product);
		}
		return result;
	}

	public List<Product> selectBy(Spec specs[]) {
		List<Product> result = new ArrayList();
		for (Spec spec : specs) {
			for (Product product : products) {
				if (spec.isSatisfiedBy(product))
					result.add(product);
			}
		}
		return result;
	}
}
