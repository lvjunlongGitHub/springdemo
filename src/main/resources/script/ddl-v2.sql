CREATE TABLE `teacher` (
 `id` bigint(20) NOT NULL COMMENT 'ID',
 `name` varchar(11) NOT NULL COMMENT '姓名',
 `ctime` int(11) NOT NULL COMMENT '创建时间',
 `utime` int(11) NOT NULL COMMENT '修改时间',
 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='测试表'