package org.clt.service;

import java.util.Map;

import org.clt.repository.pojo.WechatAccount;
import org.clt.repository.pojo.WechatUser;
import org.clt.service.base.UserService;
import org.clt.service.base.WechatUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WechatEventService {
	
	@Autowired
	private UserBindService userBindService;
	
	public void checkEvent(Map<String, String> msg) {
		switch(msg.get("Event").toLowerCase()) {
		
			case "unsubscribe" :
				break;
			case "subscribe" :
				
				break;
			case "scancode_push" :
				this.scanEvent(msg.get("EventKey"), msg.get("ScanResult"), msg.get("FromUserName"), msg.get("ToUserName"));
				break;
			default: 
				
		}
	}
	
	private void scanEvent(String eventKey, String scanResult, String openId, String wechatAccount) {
		switch(eventKey) {
			case "login":
				this.userBindService.bindWechatUser(scanResult, wechatAccount, openId);
				break;
			default:
		}
	}
}
