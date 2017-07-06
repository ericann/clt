package org.clt.service;

import java.util.TimerTask;

import org.clt.repository.dao.BasicConfigDao;
import org.clt.repository.pojo.BasicConfig;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
public class UpdateWechatAccessTokenTask extends TimerTask {
	
	private RestTemplate http;
	
	private BasicConfigDao basicConfigDao;
	
	private BasicConfig bc;
	
	public void setBasicConfig(BasicConfig bc) {
		this.bc = bc;
	}
	
	public void init(RestTemplate http, BasicConfigDao basicConfigDao) {
		this.http = http;
		this.basicConfigDao = basicConfigDao;
	}
	
	public void setBasicConfig(String wechatAppId, String wechatAppSecret, String id) {
		this.bc = new BasicConfig();
		this.bc.setId(id);
		this.bc.setWechatAppId(wechatAppId);
		this.bc.setWechatAppSecret(wechatAppSecret);
	}
	
	public UpdateWechatAccessTokenTask() {
		
	}
	
	public UpdateWechatAccessTokenTask(BasicConfig bc) {
		this.bc = bc;
	}
	
	public UpdateWechatAccessTokenTask(String wechatAppId, String wechatAppSecret, String id) {
		this.bc = new BasicConfig();
		this.bc.setId(id);
		this.bc.setWechatAppId(wechatAppId);
		this.bc.setWechatAppSecret(wechatAppSecret);
	}

	@Override
	public void run() {
		this.refreshWechatAccessToken(this.bc);
	}
	
	@Transactional
	private void refreshWechatAccessToken(BasicConfig bc) {
		
		final String ENDPOINT = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential" +
				"&appid=" + bc.getWechatAppId() + "&secret=" + bc.getWechatAppSecret();
		final HttpMethod METHOD = HttpMethod.GET;
		
		ResponseEntity<String> result = http.exchange(ENDPOINT, METHOD, null, String.class);
		JSONObject obj = new JSONObject(result.getBody());
		
		try {
			System.out.println("-- result: " + obj);
			String accessToken = obj.getString("access_token");
			this.basicConfigDao.updateWechatAccessTokenById(accessToken, bc.getId());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}