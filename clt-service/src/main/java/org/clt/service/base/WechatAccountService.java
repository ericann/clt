package org.clt.service.base;

import org.clt.repository.pojo.WechatAccount;
import org.springframework.stereotype.Service;

@Service
public interface WechatAccountService extends GenericService<WechatAccount, String>  {
	
	WechatAccount findByUseDefault(Boolean flag);
}
