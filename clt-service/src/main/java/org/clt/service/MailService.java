package org.clt.service;

import java.util.Date;
import java.util.Map;

import org.clt.util.mail.MailUtil;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MailUtil mail;

	public Map<String, Object> sendCode(String address, String subject) {
		Integer random = (int) Math.floor(Math.random() * 899998 + 100000);
		final String content = "Dear user,</br></br><span style='display:block;text-indent:2em;'>The verification code you applied for binding Wechat is: {1}. And it'll be invalid in 2 minutes.</span></br></br>CLT Service";
		Map<String, Object> result = mail.send(new String[]{address}, subject, content.replace("{1}", String.valueOf(random)));
		result.put("verifyCode", random);
		result.put("time", new Date().getTime());
		
		return result;
	}
	
	public Object sendEmail(String jsonStr) {
		JSONObject json = new JSONObject(jsonStr);
		Object result = null;
		
		String[] addresses = json.getString("receiver").split(";");
		
		if(!json.getBoolean("single")) {
			result = mail.send(addresses, json.getString("subject"), json.getString("content"));
		} else {
			result = mail.sendBatch(addresses, json.getString("subject"), json.getString("content"));
		}
		
		return result;
	}
}
