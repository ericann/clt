package org.clt.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.clt.repository.pojo.WechatAccount;
import org.clt.repository.pojo.WechatTicket;
import org.clt.repository.pojo.WechatUser;
import org.clt.service.base.WechatTicketService;
import org.clt.service.base.WechatUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBindService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WechatUserService wechatUserService;
	
	@Autowired
	private WechatTicketService wechatTicketService;
	
	public Map<String, Object> bindWechatUser(String ticket, String wechatAccount, String openId) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 0);
		result.put("msg", "Bind Success.");
		
		try {
			WechatUser wu = this.wechatUserService.findByOpenId(openId);
			WechatTicket wt = this.wechatTicketService.findByTicket(ticket);
			
			if(wu == null) {
				WechatAccount wa = new WechatAccount();
				wa.setWechatAccount(wechatAccount);
				
				wu = new WechatUser();
				wu.setId(UUID.randomUUID().toString());
				wu.setBindTicket(ticket);
				wu.setOpenId(openId);
				wu.setWechataccount(wt.getWechataccount());
				wu.setContact(wt.getContact());
				this.wechatUserService.save(wu);
				
			} else {
				result.put("msg", "The user has already bind success.");
			}
			
		} catch(Exception ex) {
			ex.printStackTrace();
			result.put("msg", "Bind failed.");
			result.put("code", "1");
		}
		return result;
	}
	
	public Map<String, Object> loginByWechat(String ticket, String wechatAccount, String openId) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 0);
		result.put("msg", "Login Success.");
		logger.debug("-- in loginByWechat");
		try {
			WechatUser wu = this.wechatUserService.findByOpenIdAndWechatAccount(openId, wechatAccount);
			WechatTicket wt = this.wechatTicketService.findByTicket(ticket);
			
			if(wt != null && wu != null) {
				wt.setContact(wu.getContact());
				wt.setWechatuser(wu);
				
				this.wechatTicketService.updateContactAndWUById(wt);
			}
		} catch(Exception ex) {
			result.put("code", -1);
			result.put("msg", "Login information is not correct.");
			ex.printStackTrace();
		}
		
		return result;
	}
}
