package org.clt.service;

import java.util.List;

import org.clt.repository.pojo.WechatAccount;

import static org.clt.util.DefaultMsg.WC_ACCESSTOKEN;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WechatTokenService {
	
	@Autowired
	private BasicConfigService bcService;
	
	@Autowired
	private RestTemplate http;
	
	public String refreshSingle(String appId, String appSecret) {
		String result = "Get Token Failed";
		
		String Endpoint = WC_ACCESSTOKEN.replace("{0}", appId).replace("{1}", appSecret);
		ResponseEntity<String> res = http.exchange(Endpoint.toString(), HttpMethod.GET, null, String.class);
		
		try {
			JSONObject o = new JSONObject(res.getBody());
			result = o.getString("access_token");
			
		} catch(Exception ex) {
			System.out.println("ex: " + ex.getMessage());
		}
		
		return result;
	}
	
	public Boolean refreshAll() {
		Boolean flag = false;
		List<WechatAccount> waL = this.bcService.findAll();
		
		if(waL != null && waL.size() > 0) {
			for(WechatAccount wa : waL) {
				String Endpoint = WC_ACCESSTOKEN.replace("{0}", wa.getWechatAppId()).replace("{1}", wa.getWechatAppSecret());
				ResponseEntity<String> res = http.exchange(Endpoint.toString(), HttpMethod.GET, null, String.class);
				
				System.out.println("---------------------WechatAccessToken Refresh---------------------");
				System.out.println("-- Id:" + wa.getId() + " request result:" + res.getBody());
				System.out.println();
				
				try {
					JSONObject o = new JSONObject(res.getBody());
					String accessToken = o.getString("access_token");
					this.bcService.updateWechatAccessToken(wa.getWechatAccount(), accessToken);
					flag = true;
				} catch(Exception ex) {
					System.out.println("ex: " + ex.getMessage());
				}
			}
		}
		return flag;
	}
	
	public Boolean refreshTokenByWechatAccount(String json) {
		Boolean flag = false;
		
		try {
			JSONObject o = new JSONObject(json);
			String wechatAccount = String.valueOf(o.get("wechatAccount"));
			String wechatAccessToken = String.valueOf(o.get("access_token"));
			
			this.bcService.updateWechatAccessToken(wechatAccount, wechatAccessToken);
			flag = true;
		} catch(Exception ex) {
			System.out.println("--ex: " + ex.getMessage());
		}
		
		return flag;
	}
	
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
				
				this.bcService.updateWechatAccessToken(wechatAccount, wechatAccessToken);
			}
			
			flag = true;
		} catch(Exception ex) {
			System.out.println("--ex: " + ex.getMessage());
		}
		
		return flag;
	}
}
