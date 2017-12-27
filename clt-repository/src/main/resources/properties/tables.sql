#############################################################################
# Delete the tables if exist in database and Insert the new tables
# You have to input"TABLE " -> FIND ALL then add the database
#############################################################################

DROP TABLE `sandbox`.`wechatticket`;
DROP TABLE `sandbox`.`wechatuser`;
DROP TABLE `sandbox`.`userapp`;
DROP TABLE `sandbox`.`scope`;
DROP TABLE `sandbox`.`fieldpermission`;
DROP TABLE `sandbox`.`functionobject`;
DROP TABLE `sandbox`.`functionpermission`;
DROP TABLE `sandbox`.`objectpermission`;
DROP TABLE `sandbox`.`contact`;
DROP TABLE `sandbox`.`connectapp`;
DROP TABLE `sandbox`.`chatmessage`;
DROP TABLE `sandbox`.`wechataccount`;
DROP TABLE `sandbox`.`button`;
DROP TABLE `sandbox`.`liveagent`;
DROP TABLE `sandbox`.`sfdc`;
DROP TABLE `sandbox`.`account`;

CREATE TABLE `sandbox`.`account` (
  `id` varchar(36) NOT NULL,
  `name` varchar(45) NOT NULL,
  `master` tinyint(1) DEFAULT '1',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `updateTime` datetime ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `sandbox`.`sfdc` (
  `id` varchar(36) NOT NULL,
  `domain` varchar(36) NOT NULL DEFAULT 'login',
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `securityToken` varchar(100) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `package` varchar(45) DEFAULT NULL,
  `orgId` varchar(15) DEFAULT NULL,
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `updateTime` datetime ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `orgId_UNIQUE` (`orgId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `sandbox`.`liveagent` (
  `id` varchar(36) NOT NULL,
  `deploymentId` varchar(15) NOT NULL,
  `endPoint` varchar(100) NOT NULL,
  `orgId` varchar(15) DEFAULT NULL,
  `updateTime` datetime ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `accountId` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `la_acc_idx` (`accountId`),
  KEY `la_sfdc_idx` (`orgId`),
  CONSTRAINT `la_acc` FOREIGN KEY (`accountId`) REFERENCES `account` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `la_sfdc` FOREIGN KEY (`orgId`) REFERENCES `sfdc` (`orgId`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `sandbox`.`button` (
  `id` varchar(36) NOT NULL,
  `buttonId` varchar(15) NOT NULL,
  `displayInfo` varchar(200) NOT NULL,
  `isDefault` tinyint(1) NOT NULL,
  `laId` varchar(36) DEFAULT NULL,
  `limitCount` int(3) NOT NULL,
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `updateTime` datetime ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `buttonId_UNIQUE` (`buttonId`),
  KEY `b_bc` (`laId`),
  CONSTRAINT `button_liveagent` FOREIGN KEY (`laId`) REFERENCES `liveagent` (`id`),
  CONSTRAINT `b_bc` FOREIGN KEY (`laId`) REFERENCES `liveagent` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `sandbox`.`wechataccount` (
  `id` varchar(36) NOT NULL,
  `wechatAccessToken` varchar(300) DEFAULT NULL,
  `wechatAccount` varchar(45) NOT NULL,
  `wechatAppId` varchar(45) DEFAULT NULL,
  `wechatAppSecret` varchar(45) DEFAULT NULL,
  `wechatToken` varchar(45) NOT NULL,
  `updateTime` datetime ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `firstTimeRefresh` tinyint(1) DEFAULT '0',
  `refreshByUs` tinyint(1) DEFAULT '0',
  `accountId` varchar(36) DEFAULT NULL,
  `laId` varchar(36) DEFAULT NULL,
  `limitCount` int(3) NOT NULL,
  `useDefault` tinyint(1) DEFAULT '0',
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `wechatAccount_UNIQUE` (`wechatAccount`),
  KEY `wa_acc_idx` (`accountId`),
  KEY `wa_la_idx` (`laId`),
  CONSTRAINT `wa_liveagent` FOREIGN KEY (`laId`) REFERENCES `liveagent` (`id`),
  CONSTRAINT `wa_account` FOREIGN KEY (`accountId`) REFERENCES `account` (`id`),
  CONSTRAINT `wa_acc` FOREIGN KEY (`accountId`) REFERENCES `account` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `wa_la` FOREIGN KEY (`laId`) REFERENCES `liveagent` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `sandbox`.`chatmessage` (
  `id` varchar(36) NOT NULL,
  `affinityToken` varchar(45) NOT NULL,
  `buttonId` varchar(15) DEFAULT NULL,
  `openId` varchar(45) NOT NULL,
  `sequence` int(11) NOT NULL,
  `sessionId` varchar(45) NOT NULL,
  `sessionKey` varchar(100) NOT NULL,
  `wechatAccount` varchar(45) NOT NULL,
  `laId` varchar(36) DEFAULT NULL,
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `updateTime` datetime ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `valid` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `cm_b_idx` (`buttonId`),
  KEY `cm_bc_idx` (`laId`),
  CONSTRAINT `chatmessage_liveagent` FOREIGN KEY (`laId`) REFERENCES `liveagent` (`id`),
  CONSTRAINT `chatmessage_button` FOREIGN KEY (`buttonId`) REFERENCES `button` (`id`),
  CONSTRAINT `cm_b` FOREIGN KEY (`buttonId`) REFERENCES `button` (`buttonId`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `cm_bc` FOREIGN KEY (`laId`) REFERENCES `liveagent` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `sandbox`.`connectapp` (
  `id` varchar(36) NOT NULL,
  `clientId` varchar(36) NOT NULL,
  `clientSecret` varchar(200) NOT NULL,
  `redirectURI` varchar(100) NOT NULL,
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `updateTime` datetime ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `sandbox`.`contact` (
  `id` varchar(36) NOT NULL,
  `email` varchar(45) NOT NULL,
  `mobile` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `accountId` varchar(36) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `master` tinyint(1) DEFAULT '1',
  `sessionId` varchar(36) DEFAULT NULL,
  `openId` varchar(36) DEFAULT NULL,
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `updateTime` datetime ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `sessionUpdateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `session_UNIQUE` (`sessionId`),
  UNIQUE KEY `openId_UNIQUE` (`openId`),
  KEY `acc_con_idx` (`accountId`),
  CONSTRAINT `contact_account` FOREIGN KEY (`accountId`) REFERENCES `account` (`id`),
  CONSTRAINT `acc_con` FOREIGN KEY (`accountId`) REFERENCES `account` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `sandbox`.`objectpermission` (
  `id` varchar(36) NOT NULL,
  `objectName` varchar(36) NOT NULL,
  `read` tinyint(1) NOT NULL,
  `edit` tinyint(1) NOT NULL,
  `del` tinyint(1) NOT NULL,
  `add` tinyint(1) NOT NULL,
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `updateTime` datetime ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `Boolean` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `sandbox`.`functionpermission` (
  `id` varchar(36) NOT NULL,
  `url` varchar(200) NOT NULL,
  `name` varchar(36) NOT NULL,
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `updateTime` datetime ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `sandbox`.`functionobject` (
  `id` varchar(36) NOT NULL,
  `functionPermissionId` varchar(36) NOT NULL,
  `objectPermissionId` varchar(36) NOT NULL,
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `updateTime` datetime ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fo_fp_idx` (`functionPermissionId`),
  KEY `fo_op_idx` (`objectPermissionId`),
  CONSTRAINT `functionobject_object` FOREIGN KEY (`objectPermissionId`) REFERENCES `objectpermission` (`id`),
  CONSTRAINT `functionobject_function` FOREIGN KEY (`functionPermissionId`) REFERENCES `functionpermission` (`id`),
  CONSTRAINT `fo_fp` FOREIGN KEY (`functionPermissionId`) REFERENCES `functionpermission` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fo_op` FOREIGN KEY (`objectPermissionId`) REFERENCES `objectpermission` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `sandbox`.`fieldpermission` (
  `id` varchar(36) NOT NULL,
  `objectId` varchar(36) NOT NULL,
  `field` varchar(36) NOT NULL,
  `read` tinyint(1) NOT NULL,
  `edit` tinyint(1) NOT NULL,
  `del` tinyint(1) NOT NULL,
  `add` tinyint(1) NOT NULL,
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `updateTime` datetime ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `field_object_idx` (`objectId`),
  CONSTRAINT `field_objectpermission` FOREIGN KEY (`objectId`) REFERENCES `objectpermission` (`id`),
  CONSTRAINT `field_object` FOREIGN KEY (`objectId`) REFERENCES `objectpermission` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `sandbox`.`scope` (
  `id` varchar(36) NOT NULL,
  `name` varchar(36) NOT NULL,
  `appId` varchar(36) NOT NULL,
  `functionPermissionId` varchar(36) NOT NULL,
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `updateTime` datetime ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `scope_app_idx` (`appId`),
  KEY `scope_fp_idx` (`functionPermissionId`),
  CONSTRAINT `scope_function` FOREIGN KEY (`functionPermissionId`) REFERENCES `functionpermission` (`id`),
  CONSTRAINT `scope_connectapp` FOREIGN KEY (`appId`) REFERENCES `connectapp` (`id`),
  CONSTRAINT `scope_app` FOREIGN KEY (`appId`) REFERENCES `connectapp` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `scope_fp` FOREIGN KEY (`functionPermissionId`) REFERENCES `functionpermission` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `sandbox`.`userapp` (
  `id` varchar(36) NOT NULL,
  `contactId` varchar(36) NOT NULL,
  `name` varchar(36) NOT NULL,
  `connectAppId` varchar(36) NOT NULL,
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `updateTime` datetime ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `ua_app_idx` (`connectAppId`),
  KEY `ua_con_idx` (`contactId`),
  CONSTRAINT `ua_contact` FOREIGN KEY (`contactId`) REFERENCES `contact` (`id`),
  CONSTRAINT `ua_connectapp` FOREIGN KEY (`connectAppId`) REFERENCES `connectapp` (`id`),
  CONSTRAINT `ua_app` FOREIGN KEY (`connectAppId`) REFERENCES `connectapp` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ua_con` FOREIGN KEY (`contactId`) REFERENCES `contact` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `sandbox`.`wechatuser` (
  `id` varchar(36) NOT NULL,
  `openId` varchar(45) NOT NULL,
  `bindTicket` varchar(100) DEFAULT NULL,
  `nickname` varchar(45) DEFAULT NULL,
  `sex` tinyint(1) DEFAULT NULL,
  `language` varchar(4) DEFAULT NULL,
  `city` varchar(10) DEFAULT NULL,
  `province` varchar(10) DEFAULT NULL,
  `country` varchar(15) DEFAULT NULL,
  `headimgurl` varchar(100) DEFAULT NULL,
  `subscribeTime` datetime DEFAULT NULL,
  `unionid` varchar(45) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  `wechatAccount` varchar(45) NOT NULL,
  `contactId` varchar(36) DEFAULT NULL,
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `updateTime` datetime ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `wt_con_idx` (`contactId`),
  KEY `wu_wa_idx` (`wechatAccount`),
  CONSTRAINT `wu_con` FOREIGN KEY (`contactId`) REFERENCES `contact` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `wu_wa` FOREIGN KEY (`wechatAccount`) REFERENCES `wechataccount` (`wechatAccount`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `sandbox`.`wechatticket` (
  `id` varchar(36) NOT NULL,
  `ticket` varchar(100) NOT NULL,
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `updateTime` datetime ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `wechatUserId` varchar(36) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `wechatAccount` varchar(45) NOT NULL,
  `conId` varchar(36) DEFAULT NULL,
  `expiredIn` int(4) DEFAULT '7200',
  `isValid` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ticket_UNIQUE` (`ticket`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `wt_wu_idx` (`wechatUserId`),
  KEY `wt_con_idx` (`conId`),
  KEY `wt_wa_idx` (`wechatAccount`),
  CONSTRAINT `wt_con` FOREIGN KEY (`conId`) REFERENCES `contact` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `wt_wa` FOREIGN KEY (`wechatAccount`) REFERENCES `wechataccount` (`wechatAccount`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `wt_wu` FOREIGN KEY (`wechatUserId`) REFERENCES `wechatuser` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
