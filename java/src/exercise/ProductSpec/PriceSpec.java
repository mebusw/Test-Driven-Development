package exercise.ProductSpec;

/**
 * Created by jacky on 15/8/28.
 */
public class PriceSpec extends Spec{
    private int price;

    public PriceSpec(int price) {
        this.price = price;
    }

    public boolean isSatisfiedBy(Product product) {
        return product.price == price;
    }

}
