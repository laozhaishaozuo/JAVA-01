package top.shaozuo.geektime.java01.week08.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import top.shaozuo.geektime.java01.week08.model.ShopOrder;

@Mapper
public interface ShopOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShopOrder record);

    int insertSelective(ShopOrder record);

    ShopOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShopOrder record);

    int updateByPrimaryKey(ShopOrder record);

    List<ShopOrder> selectByBuyerId(Long id);
}