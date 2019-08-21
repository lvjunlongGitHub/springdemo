CREATE TABLE `game_divide` (
 `id` int (11) NOT NULL COMMENT '主键ID',
 `masterID` int (11) NOT NULL COMMENT '渠道ID',
 `appID` int (11) NOT NULL COMMENT '游戏ID',
 `divide` int (11) NOT NULL COMMENT '分成比,0～100',
 `ctime` int (11) NOT NULL COMMENT '创建时间',
 `utime` int (11) NOT NULL COMMENT '更新时间',
 PRIMARY KEY (`id`),
 UNIQUE KEY `uk_masterID_appID` (`masterID`, `appID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='游戏分成表';

CREATE TABLE `game_divide_history` (
 `id` int (11) NOT NULL COMMENT '主键ID',
 `masterID` int (11) NOT NULL COMMENT '渠道商ID',
 `appID` int (11) NOT NULL COMMENT '游戏ID',
 `divideBefor` int (11) NOT NULL COMMENT '修改分成比前',
 `divideAfter` int (11) NOT NULL COMMENT '修改分成比后',
 `operatorID` int (11) NOT NULL COMMENT '操作人，关联表uadmin',
 `utime` int (11) NOT NULL COMMENT '更新时间',
 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='游戏分成历史表';

CREATE TABLE `yesterday_game_settle` (
 `id` int (11) NOT NULL COMMENT '主键ID',
 `masterID` int (11) NOT NULL COMMENT '渠道商ID',
 `appID` int (11) NOT NULL COMMENT '游戏ID',
 `totalAmount` int (11) COMMENT '总金额,单位：分',
 `totalDate` bigint(20) NOT NULL COMMENT '统计日期',
 `ctime` bigint(20) NOT NULL COMMENT '创建时间',
 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='昨日订单统计表';

CREATE TABLE `week_game_divide` (
 `gameWeekID` int (11) NOT NULL COMMENT '主键ID',
 `masterID` int (11) NOT NULL COMMENT '渠道ID',
 `appID` int (11) NOT NULL COMMENT '游戏ID',
 `divide` int (11) NOT NULL COMMENT '分成',
 `divideUtime` int (11) NOT NULL COMMENT '当周修改游戏分成的最终时间',
 `sumAmount` int (11) COMMENT '总结额',
 `enableSettleAmount` int (11) COMMENT '可结算金额',
 `startTime` int (11) NOT NULL COMMENT '结算开始时间(周一)',
 `endTime` int (11) NOT NULL COMMENT '结算结束时间(周日）',
 `ctime` int (11) NOT NULL COMMENT '创建时间',
 PRIMARY KEY (`gameWeekID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='单个游戏上周分成比统计表';

CREATE TABLE `week_master_settle` (
 `masterWeekID` bigint (20) NOT NULL COMMENT '主键 渠道商ID+日期',
 `masterID` int (11) NOT NULL COMMENT '渠道ID',
 `sumAmount` int (11) NOT NULL COMMENT '总金额,单位:分',
 `enableSettleAmount` int (11) NOT NULL COMMENT '可结算总金额,单位:分',
 `startTime` int (11) NOT NULL COMMENT '结算开始时间(周一)',
 `endTime` int (11) NOT NULL COMMENT '结算结束时间(周日)',
 `ctime` int (11) NOT NULL COMMENT '创建时间',
 PRIMARY KEY (`masterWeekID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='七日统计表';

CREATE TABLE `settle_record` (
 `settlementID` int (11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
 `masterID` int (11) NOT NULL COMMENT '渠道ID',
 `outUniqueID` bigint (20) NOT NULL COMMENT '外部业务唯一标识，masterWeekID',
 `totalAmount` int (11) NOT NULL COMMENT '总金额',
 `verifyState` SMALLINT(6) NOT NULL COMMENT '审核状态(1.未审核,2.审核中,3.审核通过)',
 `settlementState` SMALLINT(6) NOT NULL COMMENT '结算状态(0.未结算,1.结算中,2.结算成功)',
 `operatorID` int (11) NOT NULL COMMENT '操作员ID',
 `remark` varchar(32) COMMENT '备注',
 `ctime` int (11) NOT NULL COMMENT '创建时间',
 `utime` int (11) NOT NULL COMMENT '更新时间',
 `batchNo` bigint (11) NOT NULL COMMENT '结算记录批次号',
 PRIMARY KEY (`settlementID`),
 UNIQUE key `uk_outUniqueID` (`outUniqueID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='结算记录表';

CREATE TABLE `capital_account` (
 `masterID` int (11) NOT NULL COMMENT '主键,渠道商ID',
 `balance` int (11) NOT NULL COMMENT '金额(余额),单位:分',
 `ctime` int (11) NOT NULL COMMENT '创建时间',
 `utime` int (11) NOT NULL COMMENT '更新时间',
 PRIMARY KEY (`masterID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='资金帐号表';

CREATE TABLE `capital_flow` (
 `flowID` int (11) NOT NULL AUTO_INCREMENT COMMENT '主键流水ID',
 `amount` int (11) NOT NULL COMMENT '金额，入账为正，出帐为负',
 `status` SMALLINT(6) NOT NULL COMMENT '状态（0-未处理，1-处理中，2-处理成功，3-处理失败）',
 `masterID` int (11) NOT NULL COMMENT '渠道商ID',
 `outUniqueID` int (11) NOT NULL COMMENT '外部唯一标识(如：结算ID，转账ID)',
 `bizType` SMALLINT(6) NOT NULL COMMENT '类型,1.结算,2.转账',
 `balanceBefore` int (11) NOT NULL COMMENT '帐号转账前金额,单位:分',
 `balanceAfter` int (11) NOT NULL COMMENT '帐号转账后金额,单位:分',
 `remark` varchar(256) COMMENT '备注',
 `ctime` int (11) NOT NULL COMMENT '创建时间',
 `utime` int (11) NOT NULL COMMENT '更新时间',
 PRIMARY KEY (`flowID`),
 UNIQUE KEY `uk_outUniqueID` (`masterID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='资金流水表';

CREATE TABLE `capital_transfer_record` (
 `transferID` int (11) NOT NULL AUTO_INCREMENT COMMENT '主键',
 `masterID` int (11) NOT NULL COMMENT '渠道商ID',
 `money` int (11) NOT NULL COMMENT '金额 单位:分',
 `status` SMALLINT(6) NOT NULL COMMENT '处理状态（0-未处理，1-处理中，2-处理成功，3-处理失败）',
 `transferType` smallint (6) NOT NULL COMMENT '转账类型(1.单笔,2.批次)',
 `batchNo` int(11) NOT NULL COMMENT '批次号',
 `cardID` varchar (64) NOT NULL COMMENT '银行卡号',
 `cardBank` varchar (256) NOT NULL COMMENT '开户行',
 `cardName` varchar(32) COMMENT '持卡人姓名',
 `remark` varchar (256) COMMENT '备注',
 `ctime` int (11) NOT NULL COMMENT '创建时间',
 `utime` int (11) NOT NULL COMMENT '更新时间',
 PRIMARY KEY (`transferID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='资金转账记录表';

CREATE TABLE `channel_user` (
 `masterID` int (11) NOT NULL COMMENT '主键,渠道商ID',
 `mobile` varchar (64) NOT NULL COMMENT '手机号',
 `userName` varchar (32) COMMENT '姓名',
 `status` SMALLINT(6) NOT NULL COMMENT '状态,0.正常,1.冻结',
 `remark` varchar (256) COMMENT '备注',
 `ctime` int (11) NOT NULL COMMENT '创建时间',
 `utime` int (11) NOT NULL COMMENT '更新时间',
 PRIMARY KEY (`masterID`),
 UNIQUE key `uk_mobile` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='登录用户表';

CREATE TABLE `sms_tmpl` (
  `tmpl_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tmpl_type` smallint(6) NOT NULL COMMENT '模板类型:1通知，2验证码',
  `tmpl_no` varchar(32) DEFAULT NULL COMMENT '模板编号',
  `tmpl_content` varchar(500) CHARACTER SET utf8mb4 NOT NULL COMMENT '模板内容',
  `tmpl_desp` varchar(200) DEFAULT NULL COMMENT '模板描述',
  `ctime` bigint(20) NOT NULL COMMENT '创建时间',
  `utime` bigint(20) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`tmpl_id`),
  UNIQUE KEY `uk_tmpl_no` (`tmpl_no`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='短信模板表';

CREATE TABLE `sms_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `mobile` varchar(16) NOT NULL COMMENT '手机号',
  `tmpl_no` varchar(16) NOT NULL COMMENT '模板编号, 关联表t_sms_tmpl.tmpl_no',
  `sms_content` varchar(500) CHARACTER SET utf8mb4 NOT NULL COMMENT '短信内容',
  `send_status` smallint(6) NOT NULL COMMENT '短信发送状态：1待发送，2发送中，3发送成功，4发送失败',
  `sms_provider` varchar(32) NOT NULL COMMENT '短信服务商',
  `out_biz_no` varchar(64) DEFAULT NULL COMMENT '外部业务编号',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  `ctime` bigint(20) NOT NULL COMMENT '创建时间',
  `utime` bigint(20) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=191 DEFAULT CHARSET=utf8 COMMENT='短信发送记录表';

CREATE TABLE `sys_token` (
  `masterID` int (11) NOT NULL COMMENT '管理员ID',
  `token` varchar(128) NOT NULL COMMENT '登录token',
  `ctime` bigint(20) NOT NULL COMMENT '创建时间',
  `utime` bigint(20) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`masterID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='渠道商登录token表';

CREATE TABLE `batch_transfer` (
  `batchNo` int (11) NOT NULL AUTO_INCREMENT COMMENT '批次号',
  `masterID` int (11) NOT NULL COMMENT '渠道商ID',
  `batchStatus` smallint(6) NOT NULL COMMENT '批次转账状态(0.未转账,1.部分转账,2.已转账)',
  `transferType` smallint (6) NOT NULL COMMENT '转账类型(1.单笔,2.批次)',
  `amounts` int (11) COMMENT '提现金额',
  `ctime` int(11) NOT NULL COMMENT '创建时间',
  `utime` int(11) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`batchNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='批次转账状态表';

CREATE TABLE `channel_user_password` (
 `masterID` int (11) NOT NULL COMMENT '主键,渠道商ID',
 `password` varchar (32) COMMENT '密码',
 `ctime` int (11) NOT NULL COMMENT '创建时间',
 `utime` int (11) NOT NULL COMMENT '更新时间',
 PRIMARY KEY (`masterID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='登录用户密码表';

alter table sys_token add first_login SMALLINT(6) not Null COMMENT '状态,0.非第一次登录,1.第一次登录';

insert into `sms_tmpl` values
('3','2','SMS_150577520','验证码${code}，您正在登录，若非本人操作，请勿泄露。','通用验证码模板','1524580336','1524580336')

===========================================================================
CREATE TABLE `channel_user_company_ext` (
 `uid` bigint(20) NOT NULL COMMENT '用户ID',
 `companyName` varchar(50) NOT NULL COMMENT '企业名称',
`uniCreditUrl` varchar(255) NOT NULL COMMENT '统一社会信用代码证下载链接',
 `uniCredit` varchar(64) DEFAULT NULL COMMENT '统一社会信用代码证',
 `legalName` varchar(30) NOT NULL COMMENT '法人姓名',
 `legalIds` varchar(20) NOT NULL COMMENT '身份证号',
 `mobile` varchar(11) NOT NULL COMMENT '绑定手机号',
 `accountNo` varchar(30) NOT NULL COMMENT '对公账户',
 `parentBankName` varchar(30) NOT NULL COMMENT '开户银行',
 `province` varchar(30) NOT NULL COMMENT '开户行所在省份',
 `city` varchar(20) DEFAULT NULL COMMENT '市',
 `accountLicence` varchar(64) NOT NULL COMMENT '对公账户许可证',
 `unionBank` varchar(20) DEFAULT NULL COMMENT '支付行号',
 `bankName` varchar(255) DEFAULT NULL COMMENT '支行名称',
 `identityFront` varchar(255) NOT NULL COMMENT '身份证正面',
 `identityBack` varchar(255) NOT NULL COMMENT '身份证背面',
 `authStatus` smallint(6) NOT NULL DEFAULT '1' COMMENT '审核状态 1-未审核，2-审核通过，3-审核未通过',
 `remake` varchar(255) DEFAULT NULL COMMENT '备注',
 `ctime` int(11) NOT NULL COMMENT '创建时间',
 `utime` int(11) NOT NULL COMMENT '修改时间',
 PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='企业渠道额外信息表';

CREATE TABLE `channel_user_bankcard` (
 `uid` bigint(20) NOT NULL COMMENT '用户ID',
 `cardNo` varchar(30) NOT NULL COMMENT '银行卡号',
 `cardType` smallint(6) NOT NULL COMMENT '卡种类型 1-储蓄卡 2-信用卡',
 `parentBankName` varchar(50) NOT NULL COMMENT '开户银行',
 `cvv2` varchar(100) DEFAULT NULL COMMENT 'CVV2',
 `validity` varchar(30) DEFAULT NULL COMMENT '有效期',
 `reservedMobile` varchar(11) NOT NULL COMMENT '预留手机号',
 `ctime` int(11) NOT NULL COMMENT '创建时间',
 `utime` int(11) NOT NULL COMMENT '修改时间',
 PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='银行卡绑定表';

CREATE TABLE `channel_user_info_auth` (
 `uid` bigint(20) NOT NULL COMMENT '用户ID',
 `name` varchar(30) NOT NULL COMMENT '真实姓名',
 `identityNo` varchar(20) NOT NULL COMMENT '身份证号',
 `ctime` int(11) NOT NULL COMMENT '创建时间',
 `utime` int(11) NOT NULL COMMENT '修改时间',
 PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='个人信息实名认证表';

CREATE TABLE `channel_user_mobile` (
 `uid` bigint(20) NOT NULL COMMENT 'ID',
 `mobile` varchar(11) NOT NULL COMMENT '手机号',
 `ctime` int(11) NOT NULL COMMENT '创建时间',
 `utime` int(11) NOT NULL COMMENT '修改时间',
 PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='手机绑定表'

CREATE TABLE `channel_user_info` (
 `uid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
 `masterID` int(11) NOT NULL COMMENT '渠道商id',
 `outerUserId` varchar(50) NOT NULL DEFAULT '0' COMMENT '云商通用户唯一标识 id\n',
 `userType` smallint(6) NOT NULL COMMENT '用户类型 1-个人，2-企业',
 `userStatus` smallint(6) NOT NULL DEFAULT '1' COMMENT '用户状态 1-正常，2-冻结',
 `materialStatus` smallint(6) NOT NULL DEFAULT '1' COMMENT '资料状态 1-完善中，2-已完善',
 `ctime` int(11) NOT NULL COMMENT '创建时间',
 `utime` int(11) NOT NULL COMMENT '修改时间',
 `isDelete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0 -否 1- 是',
 PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=10002 DEFAULT CHARSET=utf8mb4 COMMENT='渠道用户信息表'