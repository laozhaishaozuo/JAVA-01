package top.shaozuo.geektime.java01.week07.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import top.shaozuo.geektime.java01.week07.model.ShopOrder;

@SpringBootTest
class ShopOrderServiceImplTest {

    @Autowired
    private OrderService service;

    @Test
    void testInsert() {
        String sn = Long.toString(System.currentTimeMillis());
        ShopOrder order = new ShopOrder();
        order.setSn(sn);
        order.setOrderStatus("sts");
        order.setBuyerId(sn);
        order.setCreateDate(new Date());
        order.setUpdateDate(new Date());
        order.setDelFlag("0");
        service.insert(order);
        System.out.println(order.getId());
    }

    @Test
    void testQueryById() {
        ShopOrder order = service.queryById(1);
        assertNotNull(order);
    }

}
