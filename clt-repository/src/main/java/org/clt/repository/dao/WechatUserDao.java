package org.clt.repository.dao;

import java.util.List;

import org.clt.repository.pojo.WechatUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WechatUserDao extends GenericDao<WechatUser, String> {
	
	WechatUser findByOpenId(String openId);

	WechatUser findByContactIdAndBindTicket(String contactId, String bindTicket);
	
	@Query("SELECT wu FROM WechatUser wu WHERE wu.openId=:openId And wu.wechataccount.wechatAccount=:wechatAccount")
	WechatUser findByOpenIdAndWechataccount(@Param("openId") String openId, 
			@Param("wechatAccount") String wechatAccount);
	
	@Override
	@Query("SELECT wu FROM WechatUser wu WHERE wu.wechataccount.account.id=(SELECT con.account.id FROM Contact con WHERE con.id=:conId)")
	List<WechatUser> findAllByContactId(@Param("conId") String conId);
}
