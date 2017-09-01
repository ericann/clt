package org.clt.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.clt.repository.pojo.Contact;
import org.clt.repository.pojo.WechatAccount;
import org.clt.repository.pojo.WechatTicket;
import org.clt.repository.pojo.WechatUser;
import org.clt.service.base.UserService;
import org.clt.service.base.WechatAccountService;
import org.clt.service.base.WechatTicketService;
import org.clt.service.base.WechatUserService;
import org.json.JSONObject;
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
	
	@Autowired
	private WechatTicketService wechatTicketService;
	
	@Autowired
	private WechatUserService wechatUserService;
	
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
		return this.wechatAccountService.findWechatAccessTokenByUseDefault(flag);
	}
	
	public String getQRInfoShort(String conId) {
		WechatAccount waDefault = this.wechatAccountService.findByUseDefault(true);
		String qrInfoStr = this.wechatService.getQRInfoShort(waDefault.getWechatAccessToken());
		//String qrInfoStr = this.wechatService.getQRInfoShort("2IpVUi59vEKqeSo41wEfh8h6uNiUXeMzuf_DN8wMr9NEpEOvNqawdd-hW-3b53Wjz4HNorRqzIGzTuyIEdOv18nC-WUr2-nYc7TdIhHi1ZAIFKeADAZWB");
		try {
			JSONObject qrInfo = new JSONObject(qrInfoStr);
			String ticket = qrInfo.getString("ticket");
			
			WechatTicket wt = new WechatTicket();
			wt.setId(UUID.randomUUID().toString());
			wt.setTicket(ticket);
			
			wt.setWechataccount(waDefault);
			
			if(conId != null) {
				Contact con = new Contact();
				con.setId(conId);
				wt.setContact(con);
				wt.setType("bind");
			} else {
				wt.setType("login");
			}
			
			this.wechatTicketService.save(wt);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return qrInfoStr;
	}
	
	public Map<String, Object> confirmBind(String conId, String ticket) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 0);
		result.put("msg", "Bind Successd.");
		
		WechatUser wu = null;
		
		final Integer totalSeconds = 120;
		Long lastTime = System.currentTimeMillis();
		Long currentTime = lastTime;
		
		while(lastTime + totalSeconds * 1000 > currentTime) {
			currentTime = System.currentTimeMillis();
			wu = this.wechatUserService.findByConIdAndTicket(conId, ticket);
			if(wu != null) {
				break;
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(wu == null) {
			result.put("code", 1);
			result.put("msg", "Bind failed.");
			
			//delete wechatuser
		}
		
		return result;
	}
}
