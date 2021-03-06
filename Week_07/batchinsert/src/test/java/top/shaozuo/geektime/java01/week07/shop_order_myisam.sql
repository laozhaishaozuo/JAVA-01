-- mysql_in_action.shop_order definition
drop table shop_order_myisam;

CREATE TABLE `shop_order_myisam` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `sn` varchar(100) NOT NULL COMMENT '订单编号',
  `order_status` varchar(25) NOT NULL COMMENT '订单状态',
  `buyer_id` varchar(64) NOT NULL COMMENT '买家id',
  `buyer_username` varchar(64) DEFAULT NULL COMMENT '买家名称',
  `payment_method_code` varchar(255) DEFAULT NULL COMMENT '支付方式code',
  `payment_status` varchar(25) DEFAULT NULL COMMENT '支付状态',
  `shipping_status` varchar(25) DEFAULT NULL COMMENT '配送状态',
  `expire` datetime DEFAULT NULL COMMENT '到期时间',
  `lock_expire` datetime DEFAULT NULL COMMENT '锁定到期时间',
  `fee` decimal(21,6) DEFAULT NULL COMMENT '支付手续费',
  `freight` decimal(21,6) DEFAULT NULL COMMENT '运费',
  `tax` decimal(21,6) DEFAULT NULL COMMENT '税',
  `total_amount` decimal(21,6) DEFAULT NULL COMMENT '总金额',
  `amount_payable` decimal(21,6) DEFAULT NULL COMMENT '订单应付金额',
  `amount_paid` decimal(21,6) DEFAULT NULL COMMENT '已付金额',
  `finish_date` datetime DEFAULT NULL COMMENT '订单完成时间',
  `order_time` datetime DEFAULT NULL COMMENT '下单时间',
  `order_message` varchar(255) DEFAULT NULL COMMENT '订单留言',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4;