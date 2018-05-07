package org.clt.service.base;

import java.util.List;

import org.clt.repository.pojo.WechatTicket;
import org.springframework.stereotype.Service;

@Service
public interface WechatTicketService extends GenericService<WechatTicket, String> {
	
	WechatTicket findByTicket(String ticket);
	
	WechatTicket findByWechatUserIdAndWechatAcoount(String wechatAccount, String wechatUserId);
	
	List<WechatTicket> findByWechatAccountAndWechatUserId(String wechatAccount, String wechatUserId, String id);

	Integer updateContactAndWUByIdAndIsValid(WechatTicket wt);
	
	Integer updateIsValidByTicket(String ticket);
}
