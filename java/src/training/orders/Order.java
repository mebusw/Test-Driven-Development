package training.orders;

import java.util.ArrayList;

public class Order {
    private static final int AMOUNT_LIMIT = 800000;
    private ArrayList<OrderLine> orderLines;
    private Customer customer;

    public Order() {
        super();
        orderLines = new ArrayList<OrderLine>();
    }

    public Order(Customer c) {
        this();
        this.customer = c;
    }

    public int getOrderLinesSize() {
        return orderLines.size();
    }

    public void addOrderLine(OrderLine ol) throws ExceedOrderAmountException {
        if (ol.getProductAmount() * ol.getProduct().getPrice() > AMOUNT_LIMIT)
            throw new ExceedOrderAmountException();

        for (OrderLine aol : this.orderLines) {
            if (aol.getProduct().equals(aol.getProduct())) {
                aol.setProductAmount(aol.getProductAmount()
                        + ol.getProductAmount());
                return;
            }
        }
        orderLines.add(ol);
        ol.setOrder(this);
    }

    public void addOrderLine(Product product, int productAmount)
            throws ExceedOrderAmountException {
        OrderLine ol = new OrderLine(product, productAmount);
        this.addOrderLine(ol);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ArrayList<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(ArrayList<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public void assignTo(Customer c) {
        this.customer = c;

    }

    public Object getBelongsTo() {
        return this.customer;

    }

}
