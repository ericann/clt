package org.clt.service;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.clt.repository.pojo.Button;
import org.clt.repository.pojo.ChatMessage;
import org.clt.repository.pojo.LiveAgent;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LiveAgentService {
	
	@Autowired
	private RestTemplate http;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private LiveAgent liveagent;
	private ChatMessage chatMessage;
	
	public ChatMessage createSession(String openId, String buttonId) {
		
        final String ENDPOINT = this.liveagent.getLiveAgentEndPoint() + "/System/SessionId";
        final HttpMethod METHOD = HttpMethod.GET;
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-LIVEAGENT-API-VERSION", "37");
        headers.add("X-LIVEAGENT-AFFINITY", "null");
        
        String result = this.doCall(ENDPOINT, METHOD, new HttpEntity<String>(headers));
        JSONObject sessionObj = new JSONObject(result);
        Button b = new Button();
        b.setButtonId(buttonId);
        
        this.chatMessage = new ChatMessage();
        this.chatMessage.setId(String.valueOf(UUID.randomUUID()));
        this.chatMessage.setSessionId((String) sessionObj.get("id"));
        this.chatMessage.setAffinityToken((String) sessionObj.get("affinityToken"));
        this.chatMessage.setSessionKey((String) sessionObj.get("key"));
        this.chatMessage.setSequence(this.chatMessage.getSequence() + 1);
        this.chatMessage.setButton(b);
        this.chatMessage.setOpenId(openId);
        this.chatMessage.setSequence(1);
        
        this.chasitorInit(this.chatMessage, openId, buttonId);
        
        return this.chatMessage;
    }
	
    public void chasitorInit(ChatMessage cm, String openId, String buttonId) {
        final String ENDPOINT = this.liveagent.getLiveAgentEndPoint() + "/Chasitor/ChasitorInit";
        final HttpMethod METHOD = HttpMethod.POST;
        JSONObject body = new JSONObject();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-LIVEAGENT-API-VERSION", "37");
        headers.add("X-LIVEAGENT-AFFINITY", cm.getAffinityToken());
        headers.add("X-LIVEAGENT-SESSION-KEY", cm.getSessionKey());
        headers.add("X-LIVEAGENT-SEQUENCE", "1");
        
        body.put("organizationId", this.liveagent.getLiveAgentOrgId());
        body.put("deploymentId", this.liveagent.getLiveAgentDeploymentId());
        body.put("buttonId", buttonId);
        body.put("sessionId", chatMessage.getSessionId());
        body.put("trackingId", "");
        body.put("userAgent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
        body.put("language", "zh-CN");
        body.put("screenResolution", "1440x900");
        body.put("visitorName", "Wechat");
        body.put("prechatDetails", new ArrayList<String>());
        body.put("receiveQueueUpdates", true);
        body.put("prechatEntities", new ArrayList<String>());
        body.put("isPost", true);
        
        String result = this.doCall(ENDPOINT, METHOD, new HttpEntity<String>(body.toString(), headers));
        logger.debug("--chasitorInit: " + result);
    }
    
	public String availability(String buttonId) {
        final StringBuilder ENDPOINT = new StringBuilder(this.liveagent.getLiveAgentEndPoint());
        final HttpMethod METHOD = HttpMethod.GET;
        
        ENDPOINT.append("/Visitor/Availability");
        ENDPOINT.append("?org_id=");
        ENDPOINT.append(liveagent.getLiveAgentOrgId()); 
        ENDPOINT.append("&deployment_id=");
        ENDPOINT.append(liveagent.getLiveAgentDeploymentId());
        ENDPOINT.append("&Availability.ids=[");
        ENDPOINT.append(buttonId);
        ENDPOINT.append("]");
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-LIVEAGENT-API-VERSION", "37");
        
        return doCall(ENDPOINT.toString(), METHOD, new HttpEntity<String>(headers));
    }
	
    public void chatMessage(ChatMessage cm, String text) {

        final String ENDPOINT = this.liveagent.getLiveAgentEndPoint() + "/Chasitor/ChatMessage";
        final HttpMethod METHOD = HttpMethod.POST;
        JSONObject body = new JSONObject();
        HttpHeaders headers = new HttpHeaders();
        //MediaType type = MediaType.parseMediaType("application/x-www-form-urlencoded; charset=UTF-8");
        headers.add("X-LIVEAGENT-API-VERSION", "37");
        headers.add("X-LIVEAGENT-AFFINITY", cm.getAffinityToken());
        headers.add("X-LIVEAGENT-SESSION-KEY", cm.getSessionKey());
        //headers.add("X-LIVEAGENT-SEQUENCE", String.valueOf(cm.getSequence() + 1));
		//headers.setContentType(type);
		
        body.put("text", text);
        
        doCall(ENDPOINT, METHOD, new HttpEntity<String>(body.toString(), headers));
        
    }
    
    public String messages(ChatMessage cm) {
	    
    	final String ENDPOINT = this.liveagent.getLiveAgentEndPoint() + "/System/Messages";
        final HttpMethod METHOD = HttpMethod.GET;

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-LIVEAGENT-API-VERSION", "37");
        headers.add("X-LIVEAGENT-AFFINITY", cm.getAffinityToken());
        headers.add("X-LIVEAGENT-SESSION-KEY", cm.getSessionKey());
        //headers.setContentType(MediaType.parseMediaType("application/x-www-form-urlencoded; charset=UTF-8"));
        //headers.setAccept(Arrays.asList(MediaType.parseMediaType("application/x-www-form-urlencoded; charset=UTF-8")));
        
        return doCall(ENDPOINT, METHOD, new HttpEntity<String>(headers));
	}
    
    public String getVisitorId() {
        final String ENDPOINT = this.liveagent.getLiveAgentEndPoint() + "/Visitor/VisitorId" +
            "?org_id=" + this.liveagent.getLiveAgentOrgId() + "&deployment_id=" + this.liveagent.getLiveAgentDeploymentId();
        final HttpMethod METHOD = HttpMethod.GET;
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-LIVEAGENT-API-VERSION", "37");
        
        return this.doCall(ENDPOINT, METHOD, new HttpEntity<String>(headers));
    }
	
	private String doCall(String endPoint, HttpMethod method, HttpEntity<String> reqEntity) {
		logger.debug("-----------------In Live Agent doCall ------------------");
		logger.debug(" endPoint: " + endPoint);
        logger.debug(" req Entity: " + reqEntity);
        
    	String result = null;
    	try {
    		//解决中文乱码问题
    		StringHttpMessageConverter m = new StringHttpMessageConverter(Charset.forName("UTF-8")); 
    		List<HttpMessageConverter<?>> mL = new ArrayList<HttpMessageConverter<?>>();
    		mL.add(m);
    		http.setMessageConverters(mL);
    		
    		ResponseEntity<String> res = http.exchange(endPoint, method, reqEntity, String.class);
    		result = res.getBody();
    		logger.debug("- header: " + res.getHeaders());
            logger.debug("- status: " + res.getStatusCode());
            logger.debug("- res body: " + res.getBody());
    	} catch(Exception ex) {
    		logger.debug(" Exception Message: " + ex.getMessage());
    		logger.debug(" Exception StackTrace: " + ex.getStackTrace());
    	}
    	
    	logger.debug("-----------------Out Live Agent doCall ------------------\n");
        return result;
    }
    
	public void setLiveagent(LiveAgent liveagent) {
		this.liveagent = liveagent;
	}
	
}
