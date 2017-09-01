package org.clt.service.base;

import org.clt.repository.pojo.WechatUser;
import org.springframework.stereotype.Service;

@Service
public interface WechatUserService extends GenericService<WechatUser, String> {
	
	WechatUser findByOpenId(String openId);

	WechatUser findByConIdAndTicket(String conId, String ticket);
}
