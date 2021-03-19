CREATE DATABASE IF NOT EXISTS `ds0` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin ;

USE ds0;

DROP TABLE IF EXISTS `t_account_rmb`;

CREATE TABLE `t_account_rmb` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(128) COLLATE utf8mb4_bin NOT NULL,
  `balance` decimal(15,2) NOT NULL DEFAULT 0.00 COMMENT '用户余额',
  `freeze_amount_add` decimal(15,2) NOT NULL DEFAULT 0.00 COMMENT '冻结金额，增加暂存余额',
  `freeze_amount_minus` decimal(15,2) NOT NULL DEFAULT 0.00 COMMENT '冻结金额，扣款暂存余额', 
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='人民币账户';

DROP TABLE IF EXISTS `t_account_usd`;

CREATE TABLE `t_account_usd` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(128) COLLATE utf8mb4_bin NOT NULL,
  `balance` decimal(15,2) NOT NULL DEFAULT 0.00 COMMENT '用户余额',
  `freeze_amount_add` decimal(15,2) NOT NULL DEFAULT 0.00 COMMENT '冻结金额，增加暂存余额',
  `freeze_amount_minus` decimal(15,2) NOT NULL DEFAULT 0.00 COMMENT '冻结金额，扣款暂存余额', 
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='人民币账户';

INSERT INTO ds0.t_account_rmb
(user_id, balance, create_time, update_time)
VALUES('102', 1000,  now(), now());


INSERT INTO ds0.t_account_usd
(user_id, balance, create_time, update_time)
VALUES('102', 5000, now(), now());


CREATE DATABASE IF NOT EXISTS `ds1` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin ;

USE ds1;

DROP TABLE IF EXISTS `t_account_rmb`;

CREATE TABLE `t_account_rmb` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(128) COLLATE utf8mb4_bin NOT NULL,
  `balance` decimal(15,2) NOT NULL DEFAULT 0.00 COMMENT '用户余额',
  `freeze_amount_add` decimal(15,2) NOT NULL DEFAULT 0.00 COMMENT '冻结金额，增加暂存余额',
  `freeze_amount_minus` decimal(15,2) NOT NULL DEFAULT 0.00 COMMENT '冻结金额，扣款暂存余额', 
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='人民币账户';

DROP TABLE IF EXISTS `t_account_usd`;

CREATE TABLE `t_account_usd` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(128) COLLATE utf8mb4_bin NOT NULL,
  `balance` decimal(15,2) NOT NULL DEFAULT 0.00 COMMENT '用户余额',
  `freeze_amount_add` decimal(15,2) NOT NULL DEFAULT 0.00 COMMENT '冻结金额，增加暂存余额',
  `freeze_amount_minus` decimal(15,2) NOT NULL DEFAULT 0.00 COMMENT '冻结金额，扣款暂存余额', 
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='人民币账户';


INSERT INTO ds1.t_account_rmb
(user_id, balance, create_time, update_time)
VALUES('101', 1000, now(), now());

INSERT INTO ds1.t_account_usd
(user_id, balance, create_time, update_time)
VALUES('101', 5000,now(), now());





