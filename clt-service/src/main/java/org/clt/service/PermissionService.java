package org.clt.service;

import java.util.List;
import java.util.UUID;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.clt.repository.pojo.ConnectApp;
import org.clt.repository.pojo.Contact;
import org.clt.repository.pojo.UserApp;
import org.clt.repository.pojo.WechatAccount;
import org.clt.service.base.ConnectAppService;
import org.clt.service.base.UserAppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {
	
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ConnectAppService connectAppService;
	
	@Autowired
	private UserAppService userAppService;
	
	@Autowired
	private WechatTokenService wechatTokenService;
	
	public List<ConnectApp> getConnectAppByIdAndName(String conId, String name) {
		return this.connectAppService.findByContactIdAndName(conId, name);
	}
	
	public void grantPermissions(JoinPoint jp) {
		System.out.println("--- go in grantPermissions ---");
		Contact con = (Contact) jp.getArgs()[0];
		List<ConnectApp> caL = this.connectAppService.findAll();
		
		for(ConnectApp ca : caL) {
			UserApp ua = new UserApp();
			ua.setConnectApp(ca);
			ua.setId(UUID.randomUUID().toString());
			ua.setContact(con);
			ua.setName("management");
			
			this.userAppService.save(ua);
		}
	}
	
	public void setWechatAccessToken(ProceedingJoinPoint jp) {
		try {
			Object[] args = jp.getArgs();
			WechatAccount wechatAccount = (WechatAccount) args[0];
			String wechatAccessToken = wechatAccount.getFirstTimeRefresh() ? 
					this.wechatTokenService.getByAppIdAndSecret(wechatAccount.getWechatAppId(), wechatAccount.getWechatAppSecret()) : null;
			wechatAccount.setWechatAccessToken(wechatAccessToken);
			
			jp.proceed(args);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
