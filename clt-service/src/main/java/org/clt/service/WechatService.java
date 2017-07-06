package org.clt.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.clt.util.DigitalSignature;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WechatService {

	private final String textMsgTemp = "{\"touser\":\"\",\"msgtype\":\"text\",\"text\":{\"content\":\"\"}}";
	private String wechatAccessToken;
	
	@Autowired
	private RestTemplate http;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public Boolean verifyWechatConfig(String signature, String timestamp, String nonce, String wechatToken) {
		
		String result = null;
		Boolean flag = false;
		
		String[] assembly = new String[]{timestamp, nonce, wechatToken};
		Arrays.sort(assembly);
		
		StringBuilder sb = new StringBuilder();
		for(String str : assembly) {
			sb.append(str);
		}
		
		try {
			result = DigitalSignature.SHA1(sb.toString());
			logger.debug("signature: " + signature);
			logger.debug("signature to compare: " + result);
			if(result.equals(signature)) {
				flag = true;
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flag;
	}
	
    public Map<String, String> parseXML(String xmlString) {
    	Map<String, String> chatInfo = new HashMap<String, String>();
		
		try {
			//服务器不需要这句
			//xmlString = xmlString.substring(0, xmlString.length() - 1);
			
			logger.debug(" textXML: " + xmlString);
			logger.debug("Decode textXML: " + URLDecoder.decode(xmlString, "utf-8"));
			Document textMsg = DocumentHelper.parseText(URLDecoder.decode(xmlString, "utf-8"));
			Element root = textMsg.getRootElement();  
	        
			chatInfo.put("openId", root.element("FromUserName").getText());
			chatInfo.put("wechatAccount", root.element("ToUserName").getText());
			chatInfo.put("msg", root.element("Content").getText());
			chatInfo.put("result", "0");
	        
			logger.debug("chatInfo: " + chatInfo);
		} catch (UnsupportedEncodingException e) {
			chatInfo.put("result", "1");
			e.printStackTrace();
		} catch (DocumentException e) {
			chatInfo.put("result", "2");
			e.printStackTrace();
		}
		
    	return chatInfo;
    }
    
    public void sendTextMSG(String openId, String wechatAccount, String msg) {
        
        final String ENDPOINT = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + wechatAccessToken;
        final HttpMethod METHOD = HttpMethod.POST;
        JSONObject body = new JSONObject(textMsgTemp);
        body.put("touser", openId);
        body.getJSONObject("text").put("content", msg);
        
        ResponseEntity<String> res = http.exchange(ENDPOINT, METHOD, new HttpEntity<String>(body.toString()), String.class);
        String result = res.getBody();
        logger.debug("------------In WechatSerivce sendTextMSG-------------");
        logger.debug("----- EndPoint: " + ENDPOINT);
        logger.debug("----- body: " + body);
        logger.debug("----- result: " + result);
        //logger.debug("----- header: " + res.getHeaders());
        logger.debug("------------Out WechatSerivce sendTextMSG-------------");
    }
    
	public void setWechatAccessToken(String wechatAccessToken) {
		this.wechatAccessToken = wechatAccessToken;
	}
	
}
