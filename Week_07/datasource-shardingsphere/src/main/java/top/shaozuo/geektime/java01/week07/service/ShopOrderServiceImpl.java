package top.shaozuo.geektime.java01.week07.service;

import org.springframework.stereotype.Service;

import top.shaozuo.geektime.java01.week07.dao.ShopOrderMapper;
import top.shaozuo.geektime.java01.week07.model.ShopOrder;

@Service
public class ShopOrderServiceImpl implements OrderService {

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
    public ShopOrder queryById(Integer id) {
        return shopOrderMapper.selectByPrimaryKey(id);
    }

}
