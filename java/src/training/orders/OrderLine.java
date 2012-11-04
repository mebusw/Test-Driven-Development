package training.orders;

public class OrderLine {

    private Order order;
    private Product product;
    private int productAmount;

    public OrderLine(Product product, int productAmount) {
        this.product = product;
        this.productAmount = productAmount;
    }

    public OrderLine() {
        this(new Product(), 0);
    }

    public Order getOrder() {
        return this.order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {

        return this.product;
    }

    public int getProductAmount() {

        return this.productAmount;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
