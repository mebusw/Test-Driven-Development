package exercise.ProductSpec;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jacky on 15/8/28.
 */
public class ProductFinder {
    private List<Product> products = new ArrayList();

    public void add(Product product) {
        products.add(product);
    }

    public List<Product> byPrice(int price) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.price == price)
                result.add(product);
        }
        return result;
    }

    public List<Product> byColor(String color) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.color.equals(color))
                result.add(product);
        }
        return result;
    }

    public List<Product> byColorAndBelowPrice(String color, int price) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.color.equals(color) && product.price < price)
                result.add(product);
        }
        return result;
    }
}
