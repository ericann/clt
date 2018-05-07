package org.clt.repository.dao;

import java.util.List;

import org.clt.repository.pojo.WechatTicket;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface WechatTicketDao extends GenericDao<WechatTicket, String> {
	
	@Query("SELECT wt FROM WechatTicket wt WHERE wt.ticket=:ticket")
	WechatTicket findByTicket(@Param("ticket") String ticket);
	
	@Query("SELECT wt FROM WechatTicket wt WHERE wt.type='bind' AND wt.wechataccount.wechatAccount=:wechatAccount AND wt.wechatuser.id=:wechatUserId")
	WechatTicket findByWechatUserIdAndWechatAcoount(@Param("wechatAccount") String wechatAccount, @Param("wechatUserId") String wechatUserId);
	
	@Query("SELECT wt FROM WechatTicket wt WHERE wt.wechataccount.wechatAccount=:wechatAccount AND wt.wechatuser.id=:wechatUserId AND wt.id!=:id")
	List<WechatTicket> findByWechatAccountAndWechatUserId(@Param("wechatAccount") String wechatAccount, @Param("wechatUserId") String wechatUserId, @Param("id") String id);
	
	@Modifying
	@Query("Update WechatTicket wt SET wt.contact.id=:conId, wt.wechatuser.id=:wuId, wt.isValid=:isValid WHERE wt.id=:id")
	@Transactional
	Integer updateContactAndWUByIdAndIsValid(@Param("conId") String conId, @Param("wuId") String wuId, @Param("isValid") Boolean isValid, @Param("id") String id);
	
	@Override
	@Query("SELECT fp FROM WechatTicket fp WHERE fp.id=:conId")
	List<WechatTicket> findAllByContactId(@Param("conId") String conId);
	
	@Modifying
	@Transactional
	@Query("Update WechatTicket wt SET wt.isValid=false WHERE wt.ticket=:ticket")
	Integer updateIsValidByTicket(@Param("ticket") String ticket);
}
