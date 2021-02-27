# 学习总结


## Week06 作业题目（周日）：

2.（必做）基于电商交易场景（用户、商品、订单），设计一套简单的表结构，提交 DDL 的 SQL 文件到 Github（后面 2 周的作业依然要是用到这个表结构）。


```SQL
/*Table structure for table `shop_product_category` */

DROP TABLE IF EXISTS `shop_product_category`;

CREATE TABLE `shop_product_category` (
  `id` varchar(64) NOT NULL COMMENT '商品分类id',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `grade` int(11) NOT NULL COMMENT '层级',
  `name` varchar(255) NOT NULL COMMENT '分类名称',
  `image_url` varchar(255) DEFAULT NULL COMMENT '分类图片路径',
  `parent_names` varchar(2000) DEFAULT NULL COMMENT '所有父名称',
  `parent_ids` varchar(2000) NOT NULL COMMENT '所有父编号',
  `parent_id` varchar(64) DEFAULT NULL COMMENT '父分类',
  `is_show` char(1) NOT NULL DEFAULT '1' COMMENT '是否前台显示(0为否)',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品分类表';
/*Table structure for table `shop_product` */

DROP TABLE IF EXISTS `shop_product`;

CREATE TABLE `shop_product` (
  `id` varchar(64) NOT NULL COMMENT '商品id',
  `name` varchar(255) NOT NULL COMMENT '商品名称',
  `sn` varchar(255) NOT NULL COMMENT '平台编号',
  `subtitle` varchar(255) DEFAULT NULL COMMENT '商品副标题',
  `product_category_id` varchar(64) NOT NULL DEFAULT '0' COMMENT '商品分类',
  `product_category_name` varchar(255) DEFAULT NULL COMMENT '商品分类名称',
  `is_open_sku` char(1) NOT NULL DEFAULT '0' COMMENT '是否开启sku',
  `default_sku_id` varchar(64) DEFAULT NULL COMMENT '默认sku',
  `article_number` varchar(64) DEFAULT NULL COMMENT '商品货号',
  `cost_price` decimal(21,6) DEFAULT NULL COMMENT '成本价',
  `market_price` decimal(21,6) DEFAULT NULL COMMENT '市场价',
  `sales_price` decimal(21,6) NOT NULL COMMENT '销售价',
  `is_wholesale` char(1) NOT NULL COMMENT '是否批发',
  `start_wholesale_quantity` int(11) DEFAULT NULL COMMENT '起批量',
  `default_image` varchar(255) DEFAULT NULL COMMENT '主图',
  `image_more` text COMMENT '多图',
  `introduction` longtext COMMENT '商品介绍',
  `body` longtext COMMENT '商品详细信息',
  `extended_attribute_values` longtext COMMENT '商品扩展属性值',
  `specification_properties_names` longtext COMMENT '商品规格属性值',
  `keyword` varchar(255) DEFAULT NULL COMMENT '商品关键字',
  `publish_status` varchar(25) DEFAULT NULL COMMENT '商品发布状态',
  `publish_date` datetime DEFAULT NULL COMMENT '商品发布时间',
  `un_shelve_date` datetime DEFAULT NULL COMMENT '商品下架时间',
  `product_status` varchar(25) DEFAULT NULL COMMENT '商品状态',
  `product_status_remark` varchar(25) DEFAULT NULL COMMENT '商品状态备注',
  `product_audit_status` varchar(25) DEFAULT NULL COMMENT '商品审核状态',
  `product_audit_remark` varchar(255) DEFAULT NULL COMMENT '商品审核状态备注',
  `is_free_delivery` char(1) NOT NULL DEFAULT '0' COMMENT '是否免运费',
  `area_id` varchar(64) DEFAULT NULL COMMENT '商品所在地一级',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  `favorites` int(11) DEFAULT NULL COMMENT '收藏次数',
  `support_delivery` varchar(64) DEFAULT NULL COMMENT '支持的运送方式',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品';

/*Table structure for table `shop_product_sku` */

DROP TABLE IF EXISTS `shop_product_sku`;

CREATE TABLE `shop_product_sku` (
  `id` varchar(64) NOT NULL COMMENT '商品SKU id',
  `article_number` varchar(64) DEFAULT NULL COMMENT '商品货号',
  `sn` varchar(64) DEFAULT NULL COMMENT '平台编号',
  `product_id` varchar(64) DEFAULT NULL COMMENT '商品',
  `product_name` varchar(64) DEFAULT NULL COMMENT '商品名称',
  `properties` varchar(255) NOT NULL COMMENT '商品规格属性组合字符串',
  `properties_name` varchar(255) NOT NULL COMMENT 'SKU属性组合中文名字符串',
  `price` decimal(21,6) DEFAULT NULL COMMENT 'SKU价格',
  `sales` int(11) DEFAULT NULL COMMENT '销量',
  `stock` int(11) NOT NULL COMMENT '库存',
  `barcode` varchar(64) DEFAULT NULL COMMENT '条形码',
  `is_open` char(1) NOT NULL DEFAULT '1' COMMENT '是否开启规格',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品SKU';

/*Table structure for table `shop_member` */

DROP TABLE IF EXISTS `shop_member`;

CREATE TABLE `shop_member` (
  `id` varchar(64) NOT NULL COMMENT '会员id',
  `username` varchar(100) NOT NULL COMMENT '登录名',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `birth` datetime DEFAULT NULL COMMENT '生日',
  `email` varchar(255) NOT NULL COMMENT '邮箱',
  `gender` char(1) DEFAULT NULL COMMENT '性别',
  `mobile` varchar(255) DEFAULT NULL COMMENT '手机号',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `phone` varchar(255) DEFAULT NULL COMMENT '座机电话',
  `point` varchar(64) NOT NULL DEFAULT '0' COMMENT '积分',
  `register_ip` varchar(255) NOT NULL COMMENT '注册时Ip',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `username` (`username`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员表';

/*Table structure for table `shop_member_address` */

DROP TABLE IF EXISTS `shop_member_address`;

CREATE TABLE `shop_member_address` (
  `id` varchar(64) NOT NULL COMMENT '收货地址id',
  `member_id` varchar(64) NOT NULL COMMENT '会员id',
  `receive_name` varchar(64) NOT NULL COMMENT '收货人名称',
  `area_id` varchar(64) NOT NULL COMMENT '地区id',
  `parent_area_id` varchar(64) NOT NULL COMMENT '上级地区id',
  `detailed_address` varchar(255) NOT NULL COMMENT '详细地址',
  `mobilephone` varchar(64) NOT NULL COMMENT '手机',
  `phone` varchar(64) DEFAULT NULL COMMENT '座机',
  `postcode` varchar(24) NOT NULL COMMENT '邮编',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `is_default` char(1) NOT NULL COMMENT '是否默认',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员收货地址表';

/*Table structure for table `shop_member_attribute` */


/*Table structure for table `shop_order` */

DROP TABLE IF EXISTS `shop_order`;

CREATE TABLE `shop_order` (
  `id` varchar(64) NOT NULL COMMENT '订单id',
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
  `is_allocated_stock` char(1) DEFAULT '0' COMMENT '是否已经分配库存',
  PRIMARY KEY (`id`),
  KEY `sn` (`sn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

/*Table structure for table `shop_order_item` */

DROP TABLE IF EXISTS `shop_order_item`;

CREATE TABLE `shop_order_item` (
  `id` varchar(64) NOT NULL COMMENT '订单项id',
  `order_id` varchar(64) NOT NULL COMMENT '订单',
  `product_id` varchar(64) NOT NULL COMMENT '商品',
  `product_name` varchar(255) NOT NULL COMMENT '商品名称',
  `product_sku_id` varchar(64) DEFAULT NULL COMMENT '商品sku',
  `product_sku_description` varchar(0) DEFAULT NULL COMMENT '商品sku 描述',
  `price` decimal(21,6) NOT NULL COMMENT '价格',
  `price_payable` decimal(21,6) NOT NULL COMMENT '实际应付价格',
  `quantity` int(11) NOT NULL COMMENT '数量',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单项';

/*Table structure for table `shop_order_address` */

DROP TABLE IF EXISTS `shop_order_address`;

CREATE TABLE `shop_order_address` (
  `id` varchar(64) NOT NULL COMMENT '地址id',
  `order_id` varchar(64) NOT NULL COMMENT '订单',
  `contact_name` varchar(64) DEFAULT NULL COMMENT '联系人名称',
  `receive_name` varchar(64) DEFAULT NULL COMMENT '收货人名称',
  `address_type` varchar(25) DEFAULT NULL COMMENT '地址类型（收货地址、发货地址）',
  `area_id` varchar(64) NOT NULL COMMENT '地区',
  `parent_area_id` varchar(64) DEFAULT NULL COMMENT '上级地区',
  `detailed_address` varchar(255) NOT NULL COMMENT '详细地址',
  `mobilephone` varchar(64) NOT NULL COMMENT '手机',
  `phone` varchar(64) DEFAULT NULL COMMENT '座机',
  `postcode` varchar(24) NOT NULL COMMENT '邮编',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单收货地址表';
```
