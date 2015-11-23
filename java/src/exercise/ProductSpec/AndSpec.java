package exercise.ProductSpec;

/**
 * Created by jacky on 15/8/28.
 */
public class AndSpec extends Spec {
    private Spec spec1;
    private Spec spec2;

    public AndSpec(Spec spec1, Spec spec2) {
        this.spec1 = spec1;
        this.spec2 = spec2;
    }
    public boolean isSatisfiedBy(Product product) {
        return spec1.isSatisfiedBy(product) && spec2.isSatisfiedBy(product);
    }

}
