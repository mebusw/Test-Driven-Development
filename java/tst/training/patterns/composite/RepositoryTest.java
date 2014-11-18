package training.patterns.composite;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.*;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

//import static org.junit.matchers.JUnitMatchers.*;

public class RepositoryTest {

	private Repository repository;
	private Product p1;
	private Product p2;

	@Before
	public void setUp() throws Exception {
		repository = new Repository();
		p1 = new Product("RED", 5);
		p2 = new Product("BLUE", 8);

		repository.add(p1);
		repository.add(p2);
	}

	@Test
	public void singleSpec() {
		List<Product> result = repository.selectBy(new ColorSpec("RED"));
		
		assertThat(result, hasItem(p1));
		assertThat(result, not(hasItem(p2)));
	}

	@Test
	public void multipleSpec() {
		List<Product> result = repository.selectBy(new Spec[] {
				new ColorSpec("RED"), new SizeSpec(8) });
		
		assertThat(result, hasItem(p1));
		assertThat(result, hasItem(p2));
	}
}
