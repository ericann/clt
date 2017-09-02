package org.clt.repository.dao;

import org.clt.repository.pojo.WechatTicket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface WechatTicketDao extends GenericDao<WechatTicket, String> {
	
	@Query("SELECT wt FROM WechatTicket wt WHERE wt.ticket=:ticket")
	WechatTicket findByTicket(@Param("ticket") String ticket);
	
}
