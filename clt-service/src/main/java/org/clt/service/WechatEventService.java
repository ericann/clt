package org.clt.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WechatEventService {
	
	@Autowired
	private UserService userService;
	
	public void checkEvent(Map<String, String> msg) {
		switch(msg.get("Event").toLowerCase()) {
		
			case "unsubscribe" :
				break;
			case "subscribe" :
				
				break;
			case "scancode_push" :
				this.scanEvent(msg.get("EventKey"), msg.get("ScanResult"), msg.get("FromUserName"));
				break;
			default: 
				
		}
	}
	
	private void scanEvent(String eventKey, String scanResult, String openId) {
		switch(eventKey) {
			case "login":
				//userService.login(openId, scanResult);
				break;
			default:
		}
	}
}
