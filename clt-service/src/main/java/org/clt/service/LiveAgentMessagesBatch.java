package org.clt.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.clt.repository.dao.ChatMessageDao;
import org.clt.repository.pojo.ChatMessage;
import org.json.JSONArray;
import org.json.JSONObject;

public class LiveAgentMessagesBatch implements Runnable {
	
	private ChatMessageDao chatMessageDao;
	private LiveAgentService laService;
	private WechatService wechatSerivce;
	private ChatMessage chatMessage;
	
	public LiveAgentMessagesBatch(ChatMessage chatMessage, LiveAgentService laService, WechatService wechatSerivce, ChatMessageDao chatMessageDao) {
		this.chatMessage = chatMessage;
		this.laService = laService;
		this.wechatSerivce = wechatSerivce;
		this.chatMessageDao = chatMessageDao;
	}
	
	@Override
	public void run() {
		try {
			this.getMessagesBacth();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void getMessagesBacth() throws InterruptedException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("Thread info: " + Thread.currentThread().getName() + "\t" + df.format(new Date()));
		Thread.sleep(3000);
		String result = this.laService.messages(this.chatMessage);
		Boolean flag = true;
		
		if(result != null) {
			JSONObject obj = new JSONObject(result);
			JSONArray messages = obj.getJSONArray("messages");
			
			for(int i = 0; i < messages.length(); i++) {
				JSONObject message = messages.getJSONObject(i);
				
				if(message.getString("type").equals("ChatMessage")) {
					this.wechatSerivce.sendTextMSG(this.chatMessage.getOpenId(), this.chatMessage.getWechatAccount(), message.getJSONObject("message").getString("text"));
				}
				
				if(message.getString("type").equals("ChatEnded")) {
					flag = false;
				}
			}
		}
		
		if(flag) {
			this.getMessagesBacth();
		} else {
			//this.chatMessageDao.deleteByOpenId(this.chatMessage.getOpenId());
			this.chatMessageDao.updateByOpenId(false, this.chatMessage.getOpenId());
		}
	}
	
}
