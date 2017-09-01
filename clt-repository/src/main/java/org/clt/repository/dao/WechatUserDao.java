package org.clt.repository.dao;

import org.clt.repository.pojo.WechatUser;

public interface WechatUserDao extends GenericDao<WechatUser, String> {
	
	WechatUser findByOpenId(String openId);

	WechatUser findByContactIdAndBindTicket(String contactId, String bindTicket);
	
}
