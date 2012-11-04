package training.orders;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private static OrderRepository instance = null;
    private ArrayList<Order> orders = new ArrayList<Order>();

    public static OrderRepository getOrderRepository() {
        if (null == instance) {
            synchronized (OrderRepository.class) {
                if (null == instance)
                    instance = new OrderRepository();
            }
        }
        return instance;
    }

    public void addOrder(Order o) {
        orders.add(o);
    }

    public List<Order> findOrdersByCustomer(Customer c) {
        ArrayList<Order> result = new ArrayList<Order>();
        for (Order o : orders) {
            if (o.getCustomer().equals(c)) {
                result.add(o);
            }
        }
        return orders;
    }

}
