package org.clt.service;

import java.util.List;

import org.clt.repository.pojo.WechatAccount;
import org.clt.service.base.WechatAccountService;

import static org.clt.util.DefaultMsg.WC_ACCESSTOKEN;
import static org.clt.util.HttpCall.get;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class WechatTokenService {
	
	@Autowired
	private WechatAccountService wechatAccountService;
	
	public String getByAppIdAndSecret(String appId, String appSecret) {
		String result = null;
		String Endpoint = WC_ACCESSTOKEN.replace("{0}", appId).replace("{1}", appSecret);
		ResponseEntity<String> res = get(Endpoint);
		
		try {
			JSONObject o = new JSONObject(res.getBody());
			result = o.getString("access_token");
		} catch(Exception ex) {
			System.out.println("ex: " + ex.getMessage());
		}
		
		return result;
	}
	
	public Boolean refreshSingle(String appId, String appSecret) {
		Boolean flag = false;
		
		String accessToken = this.getByAppIdAndSecret(appId, appSecret);
		if(accessToken != null) {
			this.wechatAccountService.updateWechatAccessToken(appId, appSecret, accessToken);
			flag = true;
		}
		
		return flag;
	}
	
	/**
	 * @function refresh all accessToken(refreshByUs is true only) for external
	 * @return refresh result
	 */
	public Boolean refreshAll() {
		Boolean flag = false;
		List<WechatAccount> waL = this.wechatAccountService.findAllByRefreshByUs(true);
		
		if(waL != null && waL.size() > 0) {
			for(WechatAccount wa : waL) {
				String accessToken = this.getByAppIdAndSecret(wa.getWechatAppId(), wa.getWechatAppSecret());
				if(accessToken != null) {
					this.wechatAccountService.updateWechatAccessToken(wa.getWechatAccount(), accessToken);
					flag = true;
				}
			}
		}
		return flag;
	}
	
	/**
	 * @function refresh accessToken for external user(own only) 
	 * @param json
	 * @return
	 */
	public Boolean refreshTokenByWechatAccount(String json) {
		Boolean flag = false;
		
		try {
			JSONObject o = new JSONObject(json);
			String wechatAccount = String.valueOf(o.get("wechatAccount"));
			String accessToken = String.valueOf(o.get("access_token"));
			
			this.wechatAccountService.updateWechatAccessToken(wechatAccount, accessToken);
			flag = true;
		} catch(Exception ex) {
			System.out.println("--ex: " + ex.getMessage());
		}
		
		return flag;
	}
	
	/**
	 * @function refresh accessTokens for external user(own only) 
	 * @param json
	 * @return
	 */
	public Boolean refreshTokensByWechatAccount(String json) {
		Boolean flag = false;
		
		try {
			JSONArray arr = new JSONArray(json);
			System.out.println("--json: " + json);
			for(Integer i = 0; i < arr.length(); i++) {
				JSONObject o = arr.getJSONObject(i);
				//解决异常数据导致的异常，如：wechatAccount为null，则o.getString报异常
				String wechatAccount = String.valueOf(o.get("wechatAccount"));//o.getString("wechatAccount");
				String wechatAccessToken = String.valueOf(o.get("access_token"));//o.getString("access_token");
				
				this.wechatAccountService.updateWechatAccessToken(wechatAccount, wechatAccessToken);
			}
			
			flag = true;
		} catch(Exception ex) {
			System.out.println("--ex: " + ex.getMessage());
		}
		
		return flag;
	}
}
