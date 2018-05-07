package org.clt.service.impl.base;

import java.util.List;

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
		return this.wechatTicketDao.findByTicket(ticket);
	}
	
	@Override
	public WechatTicket findByWechatUserIdAndWechatAcoount(String wechatAccount, String wechatUserId) {
		return this.wechatTicketDao.findByWechatUserIdAndWechatAcoount(wechatAccount, wechatUserId);
	}
	
	@Override
	public List<WechatTicket> findByWechatAccountAndWechatUserId(String wechatAccount, String wechatUserId, String id) {
		return this.wechatTicketDao.findByWechatAccountAndWechatUserId(wechatAccount, wechatUserId, id);
	}

	@Override
	public Integer updateContactAndWUByIdAndIsValid(WechatTicket wt) {
		return this.wechatTicketDao.updateContactAndWUByIdAndIsValid(wt.getContact().getId(),
				wt.getWechatuser().getId(), wt.getIsValid(), wt.getId());
		
	}

	@Override
	public Integer updateIsValidByTicket(String ticket) {
		return this.wechatTicketDao.updateIsValidByTicket(ticket);
	}
	
}
