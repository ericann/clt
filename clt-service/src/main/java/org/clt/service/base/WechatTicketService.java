package org.clt.service.base;

import org.clt.repository.pojo.WechatTicket;
import org.springframework.stereotype.Service;

@Service
public interface WechatTicketService extends GenericService<WechatTicket, String> {
	
	WechatTicket findByTicket(String ticket);

	Integer updateContactAndWUById(WechatTicket wt);
}
