package org.clt.service.base;

import java.util.List;

import org.clt.repository.pojo.WechatTicket;
import org.springframework.stereotype.Service;

@Service
public interface WechatTicketService extends GenericService<WechatTicket, String> {
	
	WechatTicket findByTicket(String ticket);
}
