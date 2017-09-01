package org.clt.service;

import java.util.HashMap;
import java.util.Map;

import org.clt.repository.pojo.WechatAccount;
import org.clt.repository.pojo.WechatTicket;
import org.clt.repository.pojo.WechatUser;
import org.clt.service.base.WechatTicketService;
import org.clt.service.base.WechatUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBindService {
	
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
				wu.setBindTicket(ticket);
				wu.setOpenId(openId);
				wu.setWechataccount(wa);
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
}
