package org.clt.service.impl.base;

import java.util.List;

import org.clt.repository.pojo.WechatAccount;
import org.clt.service.base.WechatAccountService;

public class WechatAccountServiceImpl extends GenericService<WechatAccount, String> implements WechatAccountService {

	@Override
	public WechatAccount findByUseDefault(Boolean flag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateWechatAccessToken(String appId, String appSecret, String accessToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findAccessTokenByUseDefault(Boolean flag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WechatAccount> findAllByRefreshByUs(Boolean flag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateWechatAccessToken(String wechatAccount, String accessToken) {
		// TODO Auto-generated method stub
		return null;
	}

}
