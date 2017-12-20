package org.clt.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.clt.util.DigitalSignature;
import org.clt.util.HttpCall;
import org.clt.util.XML;

import static org.clt.util.DefaultMsg.WC_SENDMSG;
import static org.clt.util.DefaultMsg.WC_CREATEQR;
import static org.clt.util.DefaultMsg.WC_GETQRIMG;
import static org.clt.util.DefaultMsg.WC_T_TEXTMSG;
import static org.clt.util.DefaultMsg.WC_T_QRSHORTLIVE;
import static org.clt.util.DefaultMsg.LOGIN_QR_EXPIRE;

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
	
    public Map<String, String> parseChatXML(String xmlString) {
    	Map<String, String> chatInfo = new HashMap<String, String>();
		
		try {
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
        
        final String ENDPOINT = WC_SENDMSG.replace("{0}", wechatAccessToken);
        final HttpMethod METHOD = HttpMethod.POST;
        JSONObject body = new JSONObject(WC_T_TEXTMSG);
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
    
    public String getQRTicket(String accessToken, String type) {
        final String ENDPOINT = WC_CREATEQR.replace("{0}", accessToken);
        final String sceneId = type.equals("login") ? "10002" : "10001";
        String body = WC_T_QRSHORTLIVE.replace("{0}", LOGIN_QR_EXPIRE).replace("{1}", sceneId);
        
        ResponseEntity<String> res = HttpCall.post(ENDPOINT, null, body, String.class);
        JSONObject obj = new JSONObject(res.getBody());
        
        return obj.getString("ticket");
    }
    
    public String getQRInfoShort(String accessToken, String type) {
    	Map<String, String> result = new HashMap<String, String>();
    	String ticket = this.getQRTicket(accessToken, type);
    	result.put("url", WC_GETQRIMG.replace("{0}", ticket));
    	result.put("ticket", ticket);
    	return JSONObject.valueToString(result);
    }
    
    public String getQRImage(String ticket) {
        final String ENDPOINT = WC_GETQRIMG.replace("{0}", ticket);
        //ResponseEntity<String> res = HttpCall.get(ENDPOINT, null, null, String.class);
        ResponseEntity<String> res = http.exchange(ENDPOINT, HttpMethod.GET, null ,String.class);
        return res.getBody();
    }
    
	public void setWechatAccessToken(String wechatAccessToken) {
		this.wechatAccessToken = wechatAccessToken;
	}
	
	public static void main(String[] args) {
		String xml = "<xml><ToUserName><![CDATA[toUser]]></ToUserName>" +
			"<FromUserName><![CDATA[FromUser]]></FromUserName>" +
			"<CreateTime>123456789</CreateTime>" +
			"<MsgType><![CDATA[event]]></MsgType>" +
			"<Event><![CDATA[subscribe]]></Event>" +
			"<EventKey><![CDATA[qrscene_123123]]></EventKey>" +
			"<Ticket><![CDATA[TICKET]]></Ticket>" +
			"</xml>";
		
		System.out.println(XML.parse(xml));
		
		//WechatService ws = new WechatService();
		//String image = ws.getQRTicket("hDdWmNGpI7dYKlXNORkPScjujicQTDEqOVoIxjS0KPxGGsePMy4jx38AZtYGHdh0pZX6skR2KK6qgoXYF-yJRBMl_4hyppCpZ7ND_7wn7lkhlL_tQJpIydV70n3danyeVQPaADAHCB");
		//System.out.println(image);
	}
}
