package org.clt.service.impl.base;

import org.clt.repository.dao.WechatUserDao;
import org.clt.repository.pojo.WechatUser;
import org.clt.service.base.WechatUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WechatUserServiceImpl extends GenericServiceImpl<WechatUser, String> implements WechatUserService {
	
	private WechatUserDao wechatUserDao;
	
	@Autowired
	public WechatUserServiceImpl(WechatUserDao wechatUserDao) {
		super(wechatUserDao);
		this.wechatUserDao = wechatUserDao;
	}

	@Override
	public WechatUser findByOpenId(String openId) {
		return this.wechatUserDao.findByOpenId(openId);
	}

	@Override
	public WechatUser findByConIdAndTicket(String conId, String ticket) {
		return this.wechatUserDao.findByContactIdAndBindTicket(conId, ticket);
	}
	

}
