package top.shaozuo.geektime.java01.week08.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import top.shaozuo.geektime.java01.week08.model.ShopOrder;

@SpringBootTest
class ShopOrderServiceImplTest {

    private long buyerId1 = 1L;
    private long buyerId2 = 2L;

    @Autowired
    private OrderService service;

    @BeforeEach
    void before() {
        service.deleteByBuyerId(buyerId1);
        service.deleteByBuyerId(buyerId2);
    }

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

    // @Test
    void testInsert() {
        long buyerId = 1L;
        ShopOrder order = createOrder(buyerId);
        service.insert(order);
        System.out.println(order);
    }

    @Test
    void testXaSuccess() {

        ShopOrder order1 = createOrder(buyerId1);
        ShopOrder order2 = createOrder(buyerId2);

        List<ShopOrder> ordersForInsert = new ArrayList<>(2);
        ordersForInsert.add(order1);
        ordersForInsert.add(order2);

        List<ShopOrder> ordersOfBuyer1 = service.queryByBuyerId(buyerId1);
        int sizeBeforInsert = ordersOfBuyer1.size();

        service.insertOrders(ordersForInsert);

        ordersOfBuyer1 = service.queryByBuyerId(buyerId1);

        int sizeAfterInsert = ordersOfBuyer1.size();

        assertEquals(sizeBeforInsert + 1, sizeAfterInsert);

    }

    @Test
    void testXaFailed() {
        ShopOrder order1 = createOrder(buyerId1);
        ShopOrder order2 = createOrder(buyerId2);

        List<ShopOrder> ordersForInsert = new ArrayList<>(2);
        ordersForInsert.add(order1);
        ordersForInsert.add(order2);

        List<ShopOrder> ordersOfBuyer1 = service.queryByBuyerId(buyerId1);
        int sizeBeforInsert = ordersOfBuyer1.size();

        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.insertOrdersFailed(ordersForInsert);
        });
        String expectedMessage = "insert failed";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

        ordersOfBuyer1 = service.queryByBuyerId(buyerId1);

        int sizeAfterInsert = ordersOfBuyer1.size();

        assertEquals(sizeBeforInsert, sizeAfterInsert);
    }

}
