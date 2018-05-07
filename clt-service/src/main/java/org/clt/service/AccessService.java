package org.clt.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.clt.repository.pojo.Contact;
import org.clt.repository.pojo.WechatAccount;
import org.clt.repository.pojo.WechatTicket;
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
			wt.setIsValid(true);
			
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
		
		WechatTicket wt = this.wechatTicketService.findByTicket(ticket);
		
		if(wt.getWechatuser() != null) {
			flag = 0;
		} else if(!wt.getIsValid() && wt.getWechatuser() == null) {
			flag = -1;
		} else {
			flag = 1;
		}
		
		switch(flag) {
			case 0:
				String token = this.getAccessToken(wt.getContact().getId());
				result.put("access_token", token);
				break;
			case 1:
				result.put("code", flag);
				result.put("msg", "Waiting for response.");
				break;
			default: 
				result.put("code", flag);
				result.put("msg", "Bind failed.");
		}
		
		return result;
	}
	
	public Map<String, Object> loginByWechat(String ticket) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 0);
		result.put("msg", "Login successd.");
		
		WechatTicket wt = wechatTicketService.findByTicket(ticket);
		
		if(wt != null) {
			
			if(wt.getContact() != null && wt.getWechatuser() != null) {
				result.put("access_token", this.getAccessToken(wt.getContact().getId()));
			} else if (wt.getWechatuser() == null && wt.getIsValid()) {
				result.put("code", 1);
				result.put("msg", "Waiting for message.");
			} else if (!wt.getIsValid() && wt.getWechatuser() == null) {
				result.put("msg", "User is not exist. Please sign up.");
				result.put("code", -1);
			} else {
				
			}
		} else {
			result.put("msg", "User is not exist.");
			result.put("code", -1);
		}
		
		return result;
	}
	
	public String setQRExpried(String ticket) {
		return String.valueOf(this.wechatTicketService.updateIsValidByTicket(ticket));
	}
	
	public String getProfile(String token) {
		String conId = (String) this.getAccessTokenInfo(token).get("jti");
		logger.debug("-- getProfile conId: " + conId);
		logger.debug("-- :" + this.getAccessTokenInfo(token));
		List<Map<String, Object>> access = this.userAppService.findByContactId(conId);
		Map<String, Object> accessSort = this.metadataService.convertAccess(access);
		return new JSONObject(accessSort).toString();
	}
	
	public String getAccessToken(String conId) {
//		List<Map<String, Object>> access = this.userAppService.findByContactId(conId);
//		logger.debug("-- getAccessToken: " + access);
//		logger.debug("-- conId: " + conId);
//		Map<String, Object> accessSort = this.metadataService.convertAccess(access);
		String access_token = Token.generateAccessToken(3600 * 1000, conId);
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
