package top.shaozuo.geektime.java01.week08.service;

import java.util.List;

import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import top.shaozuo.geektime.java01.week08.dao.ShopOrderMapper;
import top.shaozuo.geektime.java01.week08.model.ShopOrder;

@Service
public class ShopOrderServiceImpl implements OrderService {

    @Autowired
    private ShopOrderMapper shopOrderMapper;

    @Transactional
    @ShardingTransactionType(TransactionType.XA)
    @Override
    public void insert(ShopOrder shopOrder) {
        shopOrderMapper.insert(shopOrder);
    }

    @Transactional
    @ShardingTransactionType(TransactionType.XA)
    @Override
    public void update(ShopOrder shopOrder) {
        shopOrderMapper.updateByPrimaryKey(shopOrder);

    }

    @Transactional
    @ShardingTransactionType(TransactionType.XA)
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

    @Transactional
    @ShardingTransactionType(TransactionType.XA)
    @Override
    public void insertOrders(List<ShopOrder> shopOrders) {
        if (CollectionUtils.isEmpty(shopOrders)) {
            return;
        }
        for (ShopOrder order : shopOrders) {
            shopOrderMapper.insert(order);
        }
    }

    @Transactional
    @ShardingTransactionType(TransactionType.XA)
    @Override
    public void insertOrdersFailed(List<ShopOrder> shopOrders) {
        if (CollectionUtils.isEmpty(shopOrders)) {
            return;
        }
        for (ShopOrder order : shopOrders) {
            shopOrderMapper.insert(order);
        }
        throw new RuntimeException("insert failed！");
    }

    @Transactional
    @ShardingTransactionType(TransactionType.XA)
    @Override
    public void deleteByBuyerId(Long id) {
        shopOrderMapper.deleteByBuyerId(id);
    }

}
