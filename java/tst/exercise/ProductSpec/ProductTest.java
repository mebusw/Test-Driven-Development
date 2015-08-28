package exercise.ProductSpec;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jacky on 15/8/28.
 */
public class ProductTest {
    private ProductFinder productFinder;

    @Before
    public void setUp() throws Exception {
        productFinder = new ProductFinder();
        productFinder.add(new Product("doll", "pink", 10, 120));
        productFinder.add(new Product("car", "red", 96, 2000));
        productFinder.add(new Product("bike", "red", 26, 1000));
    }

    @Test
    public void findByPrice() {
        assertEquals("doll", productFinder.byPrice(120).get(0).name);
    }

    @Test
    public void findByColor() {
        assertEquals("car", productFinder.byColor("red").get(0).name);
        assertEquals("bike", productFinder.byColor("red").get(1).name);
    }

    @Test
    public void findByColorAndBelowPrice() {
        assertEquals("bike", productFinder.byColorAndBelowPrice("red", 1500).get(0).name);
    }

    @Test
    public void selectBySpec() {
        assertEquals("car", productFinder.selectBy(new ColorSpec("red")).get(0).name);
        assertEquals("bike", productFinder.selectBy(new ColorSpec("red")).get(1).name);
        assertEquals("bike", productFinder.selectBy(new AndSpec(new ColorSpec("red"), new PriceSpec(1000))).get(0).name);
    }
}