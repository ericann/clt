package org.clt.service.impl.base;

import java.util.List;

import org.clt.repository.dao.WechatAccountDao;
import org.clt.repository.pojo.WechatAccount;
import org.clt.service.base.WechatAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WechatAccountServiceImpl extends GenericService<WechatAccount, String> implements WechatAccountService {
	
	private WechatAccountDao wechatAccountDao;
	
	@Autowired
	public WechatAccountServiceImpl(WechatAccountDao wechatAccountDao) {
		super(wechatAccountDao);
		this.wechatAccountDao = wechatAccountDao;
	}
	
	@Override
	public WechatAccount findByUseDefault(Boolean flag) {
		return wechatAccountDao.findByUseDefault(flag);
	}

	@Override
	public Integer updateWechatAccessToken(String appId, String appSecret, String accessToken) {
		return this.wechatAccountDao.updateWechatAccessTokenByWechatAppIdAndWechatAppSecret(accessToken, appId, appSecret);
	}

	@Override
	public String findWechatAccessTokenByUseDefault(Boolean flag) {
		return this.wechatAccountDao.findWechatAccessTokenByUseDefault(flag);
	}

	@Override
	public List<WechatAccount> findAllByRefreshByUs(Boolean flag) {
		// TODO Auto-generated method stub
		return this.wechatAccountDao.findAllByRefreshByUs(flag);
	}

	@Override
	public Integer updateWechatAccessToken(String wechatAccount, String accessToken) {
		// TODO Auto-generated method stub
		return this.wechatAccountDao.updateWechatAccessTokenByWechatAccount(wechatAccount, accessToken);
	}

}
