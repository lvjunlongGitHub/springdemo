insert into game_server(appID, serverID, serverName, ctime, utime) select appID, serverID, serverName, ctime, utime from uuser_game_role GROUP BY appID,serverID;


alter table `eusdk`.`usysmenu` add `icons` varchar(150) DEFAULT NULL COMMENT '图标';

alter table `eusdk`.`usysmenu` DROP column `icons`;

INSERT into `tradedb1`.`sdk_game_role_info` (`uid`, `open_id`, `outter_game_id`, `role_id`, `role_name`, `role_server`, `base_server`, `base_server_id`, `server_id`, `role_grade`, `ctime`, `utime`) values(4791234, '5ea391731018a24cab781cc91d6fe8b4', 'yiyou_chuanqifengyunQY_1', '33690', '聚义快剑', '热血18服', '热血17服', '3520', '3690', '77', UNIX_TIMESTAMP(), UNIX_TIMESTAMP());



INSERT into `tradedb1`.`sdk_game_role_info` (`uid`, `open_id`, `outter_game_id`, `role_id`, `role_name`, `role_server`, `base_server`, `base_server_id`, `server_id`, `role_grade`, `ctime`, `utime`) values(4791234, '5ea391731018a24cab781cc91d6fe8b4', 'yiyou_chuanqifengyunQY_1', '33690', '聚义快剑', '热血18服', '热血18服', '3690', '3690', '77', 1561618834, 1561649924());

alter table `eusdk`.`game_alias` add `cp_id` int(11) DEFAULT NULL COMMENT 'cpID关联表ucpinfo' after alias_name;
alter table `eusdk`.`game_alias` add `is_Single` smallint(6) DEFAULT NULL COMMENT '是否纯单机，无游戏服务器。1：纯单机;0:非纯单机' before ctime;

alter table game_alias modify `is_Single` smallint(6) NOT NULL COMMENT '是否纯单机，无游戏服务器。1：纯单机;0:非纯单机';

UPDATE `eusdk`.`uorder` SET channelOrderID = 'Q20190708111314495217353', completeTime = '2019-07-08 11:13:12', realMoney = 1000, state = 3 WHERE orderID = 1562555592011844;

update game_alias t1 inner join ugame t2 on t1.alias_id = t2.alias_id set t1.cp_id=t2.cpID, t1.is_single=t2.isSingle;






