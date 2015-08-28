package exercise.ProductSpec;

/**
 * Created by jacky on 15/8/28.
 */
public class ColorSpec extends Spec {
    private String color;

    public ColorSpec(String color) {
        this.color = color;
    }
    public boolean isSatisfiedBy(Product product) {
        return product.color.equals(color);
    }

}
