package org.clt.service;

import java.util.Map;

import org.clt.repository.pojo.WechatTemplate;
import org.clt.service.base.WechatTemplateService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WechatTemplateBaseService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WechatTemplateService wechatTemplateService;
	
	@Autowired
	private RestTemplate http;
	
	public void sendBind(String accessToken, Map<String, Object> infor) {
		logger.debug("-- sendBind: " + (String)infor.get("wechatAccount") + ", " + "SignUp");
		WechatTemplate wt = this.wechatTemplateService.findByWechatAccountAndName((String)infor.get("wechatAccount"), "SignUp");
		
		JSONObject jo = new JSONObject(wt.getTemplateJSON());
		jo.put("touser", infor.get("openId"));
		jo.put("template_id", wt.getTemplateId());
		JSONObject data = jo.getJSONObject("data");
		((JSONObject)data.get("SignUpTime")).put("value", infor.get("time"));
		((JSONObject)data.get("Account")).put("value", infor.get("account"));
		((JSONObject)data.get("Contact")).put("value", infor.get("contact"));
		((JSONObject)data.get("Remark")).put("value", "You will be use free CLT service from now.");
		
		final String ENDPOINT = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + accessToken;
		
		this.send(ENDPOINT, HttpMethod.POST, jo.toString());
	}
	
	public void sendLogin(String accessToken, Map<String, Object> infor) {
		WechatTemplate wt = this.wechatTemplateService.findByWechatAccountAndName((String)infor.get("wechatAccount"), "SignIn");
		
		JSONObject jo = new JSONObject(wt.getTemplateJSON());
		jo.put("touser", infor.get("openId"));
		jo.put("template_id", wt.getTemplateId());
		JSONObject data = jo.getJSONObject("data");
		((JSONObject)data.get("SignInTime")).put("value", infor.get("time"));
		((JSONObject)data.get("Remark")).put("value", "You have just sign in.");
		
		final String ENDPOINT = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + accessToken;
		
		this.send(ENDPOINT, HttpMethod.POST, jo.toString());
	}
	
	private void send(final String ENDPOINT, final HttpMethod method, final String body) {
		http.exchange(ENDPOINT, method, new HttpEntity<String>(body), String.class);
	}
}
