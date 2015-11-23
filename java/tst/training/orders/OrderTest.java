package training.orders;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class OrderTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * T1: 订单可以有多个订单明细
     */
    @Test
    public void testOrderSize() {
        Order o = new Order();
        int size = o.getOrderLinesSize();
        OrderLine ol = new OrderLine(new Product(), 1);
        try {
            o.addOrderLine(ol);
        } catch (ExceedOrderAmountException e) {
            e.printStackTrace();
        }
        assertEquals(size + 1, o.getOrderLinesSize());
    }

    /**
     * T2: 一个订单明细必须对应一个订单
     */
    @Test
    public void testOrderLineBelongsToOrder() {
        Order o = new Order();
        OrderLine ol = new OrderLine();
        try {
            o.addOrderLine(ol);
        } catch (ExceedOrderAmountException e) {
            e.printStackTrace();
        }
        assertEquals(o, ol.getOrder());
    }

    /**
     * T3:一个订单明细对应一个购买的产品
     */
    @Test
    public void testOrderLineHasProduct() {
        Product p = new Product();
        OrderLine ol = new OrderLine(p, 5);
        assertEquals(p, ol.getProduct());
        assertEquals(5, ol.getProductAmount());
    }

    /**
     * T4: Order中包含的OrderLine对应的产品，不允许重复
     */
    @Test
    public void testOneProductCanOnlyInOneOrderLineOfAnOrder() {
        Order o = new Order();
        Product p = new Product();
        OrderLine ol1 = new OrderLine(p, 5);
        OrderLine ol2 = new OrderLine(p, 3);
        try {
            o.addOrderLine(ol1);
            o.addOrderLine(ol2);
        } catch (ExceedOrderAmountException evt) {
            evt.printStackTrace();
            fail();
        }
        assertEquals(1, o.getOrderLinesSize());
        assertEquals(p, ((OrderLine) o.getOrderLines().get(0)).getProduct());
        assertEquals(5 + 3,
                ((OrderLine) o.getOrderLines().get(0)).getProductAmount());
    }

    /**
     * T5:
     */
    @Test
    public void testAddProduct() {
        OrderService orderService = new OrderServiceImpl();
        Product p = new Product();
        orderService.addProduct(p, 3);
        // //TODO
        // assertEquals((), 1);

    }

    /**
     * T6: customer作为order的聚合根，可以获得所属的 order集合
     */
    @Test
    public void testCustomerHasSeveralOrders() {
        Customer c = new Customer();
        c.buildAndAddOrder(new Order());
        // 将o添加到customer的表单集合中
        assertEquals(1, c.getOrderSize());

        c.buildAndAddOrder(new Order());
        assertEquals(2, c.getOrderSize());
    }

    /**
     * T7:只有从Order到Customer的引用关系
     */
    @Test
    public void testCustomerHasSeveralOrders2() {
        Customer c = new Customer();

        // 客戶同訂單相關聯
        Order o = new Order(c);
        Order o2 = new Order(c);
        

        // 将订单添加到仓库
        OrderRepository orderRepo = OrderRepository.getOrderRepository();
        orderRepo.addOrder(o);
        orderRepo.addOrder(o2);

        // 根据customer来查找相关的订单集合
        List<Order> orders = orderRepo.findOrdersByCustomer(c);
        assertEquals(orders.size(), 2);
        assertEquals(orders.get(0), o);
        assertEquals(orders.get(1), o2);
    }

    /**
     * T8:
     */
    @Test
    public void testOrderBelongsToCustomer() {
        Customer c = new Customer();
        Order o = new Order();
        o.assignTo(c);
        assertEquals(o.getBelongsTo(), c);
    }

    /**
     * T9: 订单总额不能超过80万元
     * 
     */
    @Test
    public void testOrderAmountCantExceed80w() {
        Order o = new Order();
        Product p = new Product("龙迈笔记本", 10000);
        Product p2 = new Product("龙迈PC", 80000);

        try {
            o.addOrderLine(p, 5);
            // 如果該異常不可到大家則會報錯為“不可到達的異常”
        } catch (ExceedOrderAmountException e) {
            fail();
        }
        try {
            o.addOrderLine(p2, 11);
            fail();
        } catch (ExceedOrderAmountException ex) {
        }
    }
}
