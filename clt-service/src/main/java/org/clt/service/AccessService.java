package org.clt.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.clt.repository.pojo.Contact;
import org.clt.repository.pojo.WechatAccount;
import org.clt.repository.pojo.WechatTicket;
import org.clt.repository.pojo.WechatUser;
import org.clt.service.base.UserAppService;
import org.clt.service.base.WechatAccountService;
import org.clt.service.base.WechatTicketService;
import org.clt.service.base.WechatUserService;
import org.clt.service.metadata.CreateObjectMetadataService;
import org.clt.util.Token;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessService {
	
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@SuppressWarnings("unused")
	@Autowired
	private UserAppService userAppService;
	
	@Autowired
	private WechatAccountService wechatAccountService;
	
	@Autowired
	private WechatService wechatService;
	
	@Autowired
	private WechatTicketService wechatTicketService;
	
	@Autowired
	private WechatUserService wechatUserService;
	
	@Autowired
	private CreateObjectMetadataService metadataService;
	
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
		//后期整合
		String type = conId == null ? "login" : "bind";
		WechatAccount waDefault = this.wechatAccountService.findByUseDefault(true);
		String qrInfoStr = this.wechatService.getQRInfoShort(waDefault.getWechatAccessToken(), type);
		//String qrInfoStr = this.wechatService.getQRInfoShort("2IpVUi59vEKqeSo41wEfh8h6uNiUXeMzuf_DN8wMr9NEpEOvNqawdd-hW-3b53Wjz4HNorRqzIGzTuyIEdOv18nC-WUr2-nYc7TdIhHi1ZAIFKeADAZWB");
		try {
			JSONObject qrInfo = new JSONObject(qrInfoStr);
			String ticket = qrInfo.getString("ticket");
			
			WechatTicket wt = new WechatTicket();
			wt.setId(UUID.randomUUID().toString());
			wt.setTicket(ticket);
			
			wt.setWechataccount(waDefault);
			wt.setType(type);
			
			if(conId != null) {
				Contact con = new Contact();
				con.setId(conId);
				wt.setContact(con);
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
		Integer flag = -1;
		
		WechatUser wu = null;
		WechatTicket wt = null;
		
		final Integer totalSeconds = 120;
		Long lastTime = System.currentTimeMillis();
		Long currentTime = lastTime;
		
		while(lastTime + totalSeconds * 1000 > currentTime) {
			currentTime = System.currentTimeMillis();
			wu = this.wechatUserService.findByConIdAndTicket(conId, ticket);
			wt = this.wechatTicketService.findByTicket(ticket);
			if(wu != null) {
				flag = 0;
				break;
			}
			
			if(wt.getWechatuser() != null) {
				flag = 1;
				break;
			}
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		switch(flag) {
			case 0:
				Map<String, Object> infor = new HashMap<String, Object>();
				infor.put("conId", wu.getContact().getId());
				infor.put("wcId", wu.getId());
				String token = Token.generateAccessToken(3600, infor.toString());
				result.put("access_token", token);
				break;
			case 1:
				result.put("code", flag);
				result.put("msg", "This wechat user has already been bind the others. ");
				break;
			default: 
				result.put("code", flag);
				result.put("msg", "Bind failed.");
		}
		
		return result;
	}
	
	public String loginByWechat(String ticket) {
		
		final Integer totalSeconds = 120;
		Long lastTime = System.currentTimeMillis();
		Long currentTime = lastTime;
		String result = null;
		
		while(lastTime + totalSeconds * 1000 > currentTime) {
			currentTime = System.currentTimeMillis();
			WechatTicket wt = wechatTicketService.findByTicket(ticket);
			
			if(wt != null) {
				
				if(wt.getContact() != null && wt.getWechatuser() != null) {
					result = Token.generateAccessToken(3600, wt.getContact().getId());
					break;
				}
			}
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public String getAccessToken(String conId) {
		List<Map<String, Object>> access = this.userAppService.findByContactId(conId);
		
		Map<String, Object> accessSort = this.metadataService.convertAccess(access);
		String access_token = Token.generateAccessToken(3600 * 1000, new JSONObject(accessSort).toString());
		return access_token;
	}
	
	public Map<String, Object> getAccessTokenInfo(String access) {
		return Token.parse(access);
	}
	
	public String refreshAccessToken(String token) {
		Map<String, Object> accessToken_old = this.getAccessTokenInfo(token);
		return Token.generateAccessToken(3600 * 1000, new JSONObject(accessToken_old).toString());
	}
}
