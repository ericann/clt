package org.clt.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.clt.repository.pojo.Contact;
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
			List<WechatTicket> wtL = null;
			
			if(wu == null) {
				WechatAccount wa = new WechatAccount();
				wa.setWechatAccount(wechatAccount);
				
				wu = new WechatUser();
				wu.setId(UUID.randomUUID().toString());
				wu.setOpenId(openId);
				wu.setWechataccount(wt.getWechataccount());
				wu.setContact(wt.getContact());
				this.wechatUserService.save(wu);
				
				wt.setWechatuser(wu);
				wt.setContact(wu.getContact());
			} else {
				wtL = this.wechatTicketService.findByWechatAccountAndWechatUserId(wechatAccount, wu.getId(), wt.getId());
				if(!wtL.isEmpty()) {
					wt.setWechatuser(new WechatUser());
					result.put("code", 1043);
					result.put("msg", "The user has already exsit.");
				} else {
					wt.setWechatuser(wu);
					wt.setContact(wu.getContact());
				}
			}

			wt.setIsValid(false);
			this.wechatTicketService.updateContactAndWUByIdAndIsValid(wt);
		
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
			WechatTicket wt_bind = this.wechatTicketService.findByWechatUserIdAndWechatAcoount(wechatAccount, wu.getId());
			WechatTicket wt = this.wechatTicketService.findByTicket(ticket);
			
			if(wt != null && wu != null && wt_bind != null) {
				wt.setContact(wu.getContact());
				wt.setWechatuser(wu);
			} else {
				wt.setContact(new Contact());
				wt.setWechatuser(new WechatUser());
			}
			
			wt.setIsValid(false);
			this.wechatTicketService.updateContactAndWUByIdAndIsValid(wt);
		} catch(Exception ex) {
			result.put("code", 1011);
			result.put("msg", "Login information is not correct or unsign up.");
			ex.printStackTrace();
		}
		
		return result;
	}
}
