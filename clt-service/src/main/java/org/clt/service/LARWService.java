package org.clt.service;

import java.util.List;
import java.util.Map;

import org.clt.repository.dao.ButtonDao;
import org.clt.repository.dao.ChatMessageDao;
import org.clt.repository.dao.WechatAccountDao;
import org.clt.repository.pojo.Button;
import org.clt.repository.pojo.ChatMessage;
import org.clt.repository.pojo.WechatAccount;
import org.clt.util.DefaultMsg;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LARWService implements Runnable {
	
    @Autowired
    private ChatMessageDao chatMessageDao;
    @Autowired
    private ButtonDao buttonDao;
    @Autowired
    private WechatAccountDao waDao;
    
    @Autowired
	private WechatService wechatService;
	@Autowired
    private LiveAgentService laService;
    
    private Button button;
    private ChatMessage chatSession;
    private WechatAccount wa;
    private String xmlString;
    
	@Override
	public void run() {
		this.startChat();
	}
	
	public void setXmlString(String xmlString) {
		this.xmlString = xmlString;
	}
    
    public void startChat() {
    	
        Map<String, String> chatInfo = this.wechatService.parseChatXML(this.xmlString);
        this.chatSession = this.chatMessageDao.findByOpenId(chatInfo.get("openId"));
        this.wa = this.waDao.findAndLiveAgentByWechatAccount(chatInfo.get("wechatAccount"));
        
        if(this.chatSession == null) {
        	
        	//platform limit connection count check by org id
        	//this.chatMessageDao.findCountByButtonId(buttonId);
        	
            this.button = this.buttonDao.findByIsDefaultAndLaId(true, this.wa.getLiveagent().getId());
            
            //platform limit connection count check by button id
            Integer buttonLimitCount = this.chatMessageDao.findCountByButtonId(this.button.getButtonId());
            
            if(this.button.getLimitCount() <= buttonLimitCount) {
            	this.wechatService.sendTextMSG(chatInfo.get("openId"), chatInfo.get("wechatAccount"), DefaultMsg.get("BUTTON_LIMIT"));
            	return;
            }
            
            this.wechatService.setWechatAccessToken(this.wa.getWechatAccessToken());
            
            this.laService.setLiveagent(this.wa.getLiveagent());
        	this.chatSession = this.initLiveAgentChat(chatInfo.get("openId"), this.button.getButtonId());
            
            if(this.chatSession == null) {
                this.wechatService.sendTextMSG(chatInfo.get("openId"), chatInfo.get("wechatAccount"), this.button.getDisplayInfo());
            } else {
            	this.chatSession.setWechatAccount(this.wa.getWechatAccount());
            	this.chatSession.setLiveagent(this.wa.getLiveagent());
                this.chatMessageDao.save(this.chatSession);
                this.laService.chatMessage(this.chatSession, chatInfo.get("msg"));
                
                LiveAgentMessagesBatch messages = new LiveAgentMessagesBatch(this.chatSession, this.laService, this.wechatService, this.chatMessageDao);
                new Thread(messages).start();
            }
        } else {
            this.laService.chatMessage(this.chatSession, chatInfo.get("msg"));
        }
        
    }
    
    private ChatMessage initLiveAgentChat(String openId, String buttonId) {
    	ChatMessage cm = null;
    
        if(this.checkButtonAvailability(buttonId)) {
            cm = this.laService.createSession(openId, buttonId);
        }
        
        return cm; 
    }
    
    private Boolean checkButtonAvailability(String buttonId) {
        String jsonS = this.laService.availability(buttonId);
        
        Boolean flag = false;
    	JSONObject object = new JSONObject(jsonS);
		JSONArray messages = (JSONArray) object.get("messages");
		
		//for test - developer org is different with others.
		/*try {
			if(messages.getJSONObject(0).get("type").equals("Availability")) {
				return true;
			}
		} catch(Exception ex) {
			
		}*/
		
		for(Integer i = 0; i < messages.length(); i++) {
			JSONObject message = messages.getJSONObject(i);
			JSONArray results = message.getJSONObject("message").getJSONArray("results");
			
			for(Integer j = 0; j < results.length(); j++) {
				JSONObject result = results.getJSONObject(j);
				String id = result.getString("id");

				if(id.equals(buttonId)) {
					try {
						flag = result.getBoolean("isAvailable");
					} catch (JSONException e) {
						e.printStackTrace();
					}
					break;
				}
			}
			
			if(flag) {
				break;
			}
		}

        return flag;
    }
    
    public Boolean verfiyAddress(String signature, String timestamp, String nonce) {
    	List<String> wechatTokens = this.waDao.findWechatToken();
    	Boolean flag = false;
    	
    	for(String wechatToken : wechatTokens) {
    		flag = this.wechatService.verifyWechatConfig(signature, timestamp, nonce, wechatToken);
    		if(flag) {
    			break;
    		}
    	}
    	
    	return flag;
    }
    
}
