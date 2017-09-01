package org.clt.service.base;

import java.util.List;

import org.clt.repository.pojo.WechatAccount;
import org.springframework.stereotype.Service;

@Service
public interface WechatAccountService {
	
	WechatAccount findByUseDefault(Boolean flag);
	
	Integer updateWechatAccessToken(String appId, String appSecret, String accessToken);

	String findWechatAccessTokenByUseDefault(Boolean flag);
		
	List<WechatAccount> findAllByRefreshByUs(Boolean flag);
	
	Integer updateWechatAccessToken(String wechatAccount, String accessToken);
	
}
