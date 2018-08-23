package org.clt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.clt.repository.pojo.Account;
import org.clt.repository.pojo.ConnectApp;
import org.clt.repository.pojo.Contact;
import org.clt.repository.pojo.Sfdc;
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
	
	//解决级联插入的问题
	public void grantPermissions(JoinPoint jp) {
		System.out.println("--- go in grantPermissions ---");
		String className = jp.getArgs()[0].getClass().getSimpleName();
		List<Contact> conL = new ArrayList<Contact>();
		
		if(className.equals("Account")) {
			Account acc = (Account) jp.getArgs()[0];
			conL = acc.getContacts();
		} else if(className.equals("Contact")) {
			Contact con = (Contact) jp.getArgs()[0];
			conL.add(con);
		}
		
		if(!conL.isEmpty()) {
			for(Contact con : conL) {
				ConnectApp ca = this.connectAppService.findByName("top_m_management");
				
				UserApp ua = new UserApp();
				ua.setConnectApp(ca);
				ua.setId(UUID.randomUUID().toString());
				ua.setContact(con);
				ua.setName("management");
				
				this.userAppService.save(ua);
			}
		}
	}
	
	//解决级联插入的问题
	public void setWechatAccessToken(ProceedingJoinPoint jp) {
		try {
			Object[] args = jp.getArgs();
			String className = jp.getArgs()[0].getClass().getSimpleName();
			List<WechatAccount> waL = new ArrayList<WechatAccount>();
			
			if(className.equals("Sfdc")) {
				Sfdc sfdc = (Sfdc) args[0];
				if(sfdc.getLiveagents() != null) {
					waL = sfdc.getLiveagents().get(0).getWechataccounts();
				}
			} else if(className.equals("WechatAccount")) {
				WechatAccount wa = (WechatAccount) args[0];
				waL.add(wa);
			}
			
			for(WechatAccount wa : waL) {
				String wechatAccessToken = wa.getFirstTimeRefresh() ? 
						this.wechatTokenService.getByAppIdAndSecret(wa.getWechatAppId(), wa.getWechatAppSecret()) : null;
				wa.setWechatAccessToken(wechatAccessToken);
			}
			
			jp.proceed(args);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
