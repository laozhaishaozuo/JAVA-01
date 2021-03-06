package top.shaozuo.geektime.java01.week07.service;

import top.shaozuo.geektime.java01.week07.model.ShopOrder;

public interface OrderService {

    public void insert(ShopOrder shopOrder);

    public void update(ShopOrder shopOrder);

    public void delete(ShopOrder shopOrder);

    public ShopOrder queryById(Integer id);
}
