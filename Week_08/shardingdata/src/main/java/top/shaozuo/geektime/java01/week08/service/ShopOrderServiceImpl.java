package top.shaozuo.geektime.java01.week08.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.shaozuo.geektime.java01.week08.dao.ShopOrderMapper;
import top.shaozuo.geektime.java01.week08.model.ShopOrder;

@Service
public class ShopOrderServiceImpl implements OrderService {

    @Autowired
    private ShopOrderMapper shopOrderMapper;

    @Override
    public void insert(ShopOrder shopOrder) {
        shopOrderMapper.insert(shopOrder);
    }

    @Override
    public void update(ShopOrder shopOrder) {
        shopOrderMapper.updateByPrimaryKey(shopOrder);

    }

    @Override
    public void delete(ShopOrder shopOrder) {
        shopOrderMapper.deleteByPrimaryKey(shopOrder.getId());
    }

    @Override
    public ShopOrder queryById(Long id) {
        return shopOrderMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ShopOrder> queryByBuyerId(Long buyerId) {
        return shopOrderMapper.selectByBuyerId(buyerId);
    }

}
