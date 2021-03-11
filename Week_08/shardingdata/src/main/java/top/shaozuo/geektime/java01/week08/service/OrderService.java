package top.shaozuo.geektime.java01.week08.service;

import java.util.List;

import top.shaozuo.geektime.java01.week08.model.ShopOrder;

public interface OrderService {

    public void insert(ShopOrder shopOrder);

    public void update(ShopOrder shopOrder);

    public void delete(ShopOrder shopOrder);

    public ShopOrder queryById(Long id);

    public List<ShopOrder> queryByBuyerId(Long buyerId);

}
