package org.clt.service;

import java.util.List;

import org.clt.repository.pojo.BasicConfig;
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
		
		StringBuilder Endpoint = new StringBuilder("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential");
		Endpoint.append("&appid=");
		Endpoint.append(appId);
		Endpoint.append("&secret=");
		Endpoint.append(appSecret);
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
		List<BasicConfig> bcL = this.bcService.findAll();
		
		if(bcL != null && bcL.size() > 0) {
			for(BasicConfig bc : bcL) {
				StringBuilder Endpoint = new StringBuilder("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential");
				Endpoint.append("&appid=");
				Endpoint.append(bc.getWechatAppId());
				Endpoint.append("&secret=");
				Endpoint.append(bc.getWechatAppSecret());
				ResponseEntity<String> res = http.exchange(Endpoint.toString(), HttpMethod.GET, null, String.class);
				
				System.out.println("---------------------WechatAccessToken Refresh---------------------");
				System.out.println("-- Id:" + bc.getId() + " request result:" + res.getBody());
				System.out.println();
				
				try {
					JSONObject o = new JSONObject(res.getBody());
					String accessToken = o.getString("access_token");
					this.bcService.updateWechatAccessToken(bc.getWechatAccount(), accessToken);
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
