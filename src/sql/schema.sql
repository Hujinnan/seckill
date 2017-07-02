-- 数据库初始化脚本

CREATE DATABASE seckill;

use seckill;

CREATE TABLE seckill(
`seckill_id` bigint NOT NULL AUTO_INCREMENT comment '商品库存id',
`name` VARCHAR(120) NOT NULL comment '商品名称',
`number` INT NOT NULL comment '库存数量',
`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP comment '创建时间',
`start_time` TIMESTAMP NOT NULL comment '秒杀开始时间',
`end_time` TIMESTAMP NOT NULL comment '秒杀结束时间',

PRIMARY KEY (seckill_id),
KEY idx_start_time(start_time),
KEY idx_end_time(end_time),
KEY idx_create_time(create_time)
)engine=innodb auto_increment=1000 default charset=utf8 comment='秒杀库存';

--初始化数据
INSERT INTO seckill(name,number,start_time,end_time)
VALUES
('1000元秒杀iphone7', 100,'2017-6-18 00:00:00','2017-6-19 00:00:00'),
('1000元秒杀ipad4', 200,'2017-6-18 00:00:00','2017-6-19 00:00:00'),
('1000元秒杀macpro', 300,'2017-6-18 00:00:00','2017-6-19 00:00:00'),
('1000元秒杀小米6', 400,'2017-6-18 00:00:00','2017-6-19 00:00:00');

-- 用户登录信息表
CREATE TABLE success_killed(
`seckill_id` bigint NOT NULL comment '秒杀商品id',
`user_phone` bigint NOT NULL comment '用户手机号',
`state` tinyint NOT NULL DEFAULT -1 comment '状态标识：-1无效,0成功,1已付款，2已发货',
`create_time` TIMESTAMP NOT NULL comment '创建时间',
PRIMARY KEY (seckill_id,user_phone), --联合索引
KEY idx_create_time(create_time)
)engine=innodb DEFAULT charset=utf8 comment='秒杀成功表';



