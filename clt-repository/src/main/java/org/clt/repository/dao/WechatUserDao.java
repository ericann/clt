package org.clt.repository.dao;

import org.clt.repository.pojo.WechatUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WechatUserDao extends GenericDao<WechatUser, String> {
	
	WechatUser findByOpenId(String openId);

	WechatUser findByContactIdAndBindTicket(String contactId, String bindTicket);
	
	@Query("SELECT wu FROM WechatUser wu WHERE wu.openId=:openId And wu.wechataccount.wechatAccount=:wechatAccount")
	WechatUser findByOpenIdAndWechataccount(@Param("openId") String openId, 
			@Param("wechatAccount") String wechatAccount);
	
}
