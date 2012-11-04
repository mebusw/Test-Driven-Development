package training.orders;

import java.util.ArrayList;

public class Customer {

    private ArrayList<Order> orders = new ArrayList<Order>();

    public void buildAndAddOrder(Order order) {
        orders.add(order);
    }

    public Object getOrderSize() {
        return orders.size();
    }

}
