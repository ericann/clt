package org.clt.service;

import java.util.Map;

import org.clt.repository.pojo.Contact;
import org.clt.service.base.UserService;
import org.clt.service.base.WechatAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private WechatAccountService wechatAccountService;
	
	@Autowired
	private WechatService wechatService;
	
	public Map<String, String> getPermissions() {
		return null;
	}
	
	public Map<String, String> getApps() {
		return null;
	}
	
	public Contact getUser() {
		return null;
	}
	
	public String getAccessTokenForUseDefault(Boolean flag) {
		return this.wechatAccountService.findAccessTokenByUseDefault(flag);
	}
	
	public String getQRInfoShort() {
		String accessToken = this.wechatAccountService.findAccessTokenByUseDefault(true);
		String qrInfo = this.wechatService.getQRImgShort(accessToken);
		return qrInfo;
	}
}
