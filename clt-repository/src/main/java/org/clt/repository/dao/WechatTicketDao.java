package org.clt.repository.dao;

import java.util.List;

import org.clt.repository.pojo.WechatAccount;
import org.clt.repository.pojo.WechatTicket;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface WechatTicketDao extends GenericDao<WechatTicket, String> {
	
	@Query("SELECT wt, wa.wechatAccount FROM WechatTicket wt JOIN wt.wechataccount wa WHERE wt.wechataccount.wechatAccount=wa.wechatAccount AND wt.ticket=:ticket")
	WechatTicket findByTicket(String ticket);
	
}
