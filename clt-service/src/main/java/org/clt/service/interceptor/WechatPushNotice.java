package org.clt.service.interceptor;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.clt.repository.pojo.WechatAccount;
import org.clt.repository.pojo.WechatTicket;
import org.clt.service.WechatTemplateBaseService;
import org.clt.service.base.WechatAccountService;
import org.clt.service.base.WechatTicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class WechatPushNotice {
	
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WechatTemplateBaseService wechatTemplateBaseService;
	
	@Autowired
	private WechatTicketService wechatTicketService;
	
	@Autowired
	private WechatAccountService wechatAccountService;

	public void pushSignUpNotice(JoinPoint jp) {
		Object[] args = jp.getArgs();
		String ticket = (String)args[0];
		String wechatAccount = (String)args[1];
		String openId = (String)args[2];
		
		WechatTicket wt = this.wechatTicketService.findByTicket(ticket);
		
		if(wt != null && wt.getWechatuser() != null) {
		
			Map<String, Object> infor = new HashMap<String, Object>();
			infor.put("openId", openId);
			infor.put("wechatAccount", wechatAccount);
			infor.put("account", wt.getContact().getAccount().getName());
			infor.put("contact", wt.getContact().getName());
			infor.put("time", wt.getUpdateTime().toString());
			
			WechatAccount wa = this.wechatAccountService.findByUseDefault(true);
			this.wechatTemplateBaseService.sendBind(wa.getWechatAccessToken(), infor);
		}
		
	}
	
	public void pushSignInNotice(JoinPoint jp) {
		Object[] args = jp.getArgs();
		String ticket = (String)args[0];
		String wechatAccount = (String)args[1];
		String openId = (String)args[2];
		
		WechatTicket wt = this.wechatTicketService.findByTicket(ticket);
		
		if(wt != null && wt.getWechatuser() != null) {
		
			Map<String, Object> infor = new HashMap<String, Object>();
			infor.put("openId", openId);
			infor.put("wechatAccount", wechatAccount);
			infor.put("time", wt.getUpdateTime().toString());
			
			WechatAccount wa = this.wechatAccountService.findByUseDefault(true);
			this.wechatTemplateBaseService.sendLogin(wa.getWechatAccessToken(), infor);
		}
		
	}
	
}
