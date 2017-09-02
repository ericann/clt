package org.clt.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WechatEventService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserBindService userBindService;
	
	public void checkEvent(Map<String, String> msg) {
		switch(msg.get("Event").toLowerCase()) {
		
			case "unsubscribe" :
				break;
			case "subscribe" :
				this.scanEvent(msg.get("EventKey"), msg.get("Ticket"), msg.get("FromUserName"), msg.get("ToUserName"));
				break;
			case "scancode_push" :
				//this.scanEvent(msg.get("EventKey"), msg.get("ScanResult"), msg.get("FromUserName"), msg.get("ToUserName"));
				break;
				
			case "scan":
				this.scanEvent(msg.get("EventKey"), msg.get("Ticket"), msg.get("FromUserName"), msg.get("ToUserName"));
				break;
			default: 
				
		}
	}
	
	private void scanEvent(String eventKey, String ticket, String openId, String wechatAccount) {
		logger.debug("-- scanEvent: " + eventKey);
		switch(eventKey) {
		
			case "qrscene_10001": 
				//与“10001”合并处理
			case "10001":
				this.userBindService.bindWechatUser(ticket, wechatAccount, openId);
				break;
			case "10002":
				logger.debug("-- in case 10002");
				this.userBindService.loginByWechat(ticket, wechatAccount, openId);
				break;
			default:
		}
	}
}
