package top.shaozuo.geektime.java01.week08.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import top.shaozuo.geektime.java01.week08.model.ShopOrder;

@SpringBootTest
class ShopOrderServiceImplTest {

    @Autowired
    private OrderService service;

    private ShopOrder createOrder(long buyerId) {
        String sn = Long.toString(System.currentTimeMillis());
        ShopOrder order = new ShopOrder();
        order.setSn(sn);
        order.setOrderStatus("sts");
        order.setBuyerId(buyerId);
        order.setCreateDate(new Date());
        order.setUpdateDate(new Date());
        order.setDelFlag("0");
        return order;
    }

    @Test
    void testInsert() {
        for (long i = 1; i <= 32; i++) {
            service.insert(createOrder(i));
        }
    }

    @Test
    void test() {
        long buyerId = 1L;
        ShopOrder order = createOrder(buyerId);

        service.insert(order);

        List<ShopOrder> orders = service.queryByBuyerId(buyerId);
        assertNotNull(orders);

        order = orders.get(0);
        String orderStatus = "finish";
        order.setOrderStatus(orderStatus);
        service.update(order);

        ShopOrder result = service.queryById(order.getId());
        assertEquals(orderStatus, result.getOrderStatus());

        service.delete(order);
        result = service.queryById(order.getId());
        assertNull(result);
    }

}
