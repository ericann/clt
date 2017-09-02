package org.clt.repository.dao;

import org.clt.repository.pojo.WechatTicket;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface WechatTicketDao extends GenericDao<WechatTicket, String> {
	
	@Query("SELECT wt FROM WechatTicket wt WHERE wt.ticket=:ticket")
	WechatTicket findByTicket(@Param("ticket") String ticket);
	
	@Modifying
	@Query("Update WechatTicket wt SET wt.contact.id=:conId, wt.wechatuser.id=:wuId WHERE id=:id")
	@Transactional
	Integer updateContactAndWUById(@Param("conId") String conId, @Param("wuId") String wuId, @Param("id") String id);
	
}
