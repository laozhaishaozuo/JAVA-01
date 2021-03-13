# 学习笔记

## week08 周三

2. 水平分库分表
（必做）设计对前面的订单表数据进行水平分库分表，拆分2个库，每个库16张表。
并在新结构在演示常见的增删改查操作。代码、sql 和配置文件，上传到 Github。

代码地址是： https://github.com/laozhaishaozuo/JAVA-01/tree/main/Week_08/shardingdata

测试代码地址是： top.shaozuo.geektime.java01.week08.service.ShopOrderServiceImplTest


>注意事项
1. 有四种配置规则，选择合适的配置规则，不要混用，当配置的规则不生效时，去[官网](https://shardingsphere.apache.org/document/legacy/4.x/document/cn/manual/sharding-jdbc/configuration/)查看有没有错漏
2. 在更新语句中不要更新分片键， MyBatis 生成的代码中可能会更新所有字段

## week08 周日
2. （必做）基于 hmily TCC 或 ShardingSphere 的 Atomikos XA 实现一个简单的分布式事务应用 demo（二选一），提交到 Github

使用的是 ShardingSphere 实现的 XA 分布式应用

代码地址是： https://github.com/laozhaishaozuo/JAVA-01/tree/main/Week_08/sharding-xa

测试代码地址是： top.shaozuo.geektime.java01.week08.service.ShopOrderServiceImplTest
