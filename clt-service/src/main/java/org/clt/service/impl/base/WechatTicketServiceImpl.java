package org.clt.service.impl.base;

import org.clt.repository.dao.WechatTicketDao;
import org.clt.repository.pojo.WechatTicket;
import org.clt.service.base.WechatTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WechatTicketServiceImpl extends GenericServiceImpl<WechatTicket, String> implements WechatTicketService {
	
	private WechatTicketDao wechatTicketDao;
	
	@Autowired
	public WechatTicketServiceImpl(WechatTicketDao wechatTicketDao) {
		super(wechatTicketDao);
		this.wechatTicketDao = wechatTicketDao;
	}

	@Override
	public WechatTicket findByTicket(String ticket) {
		this.wechatTicketDao.findByTicket(ticket);
		return null;
	}
	

}
