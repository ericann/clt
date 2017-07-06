package org.clt.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ServiceTest {
	
	public static void getUUID() {
		System.out.println("UUID: " + UUID.randomUUID());
	}
	
	public static void parseAv() {
		String jsonS = "{\"messages\":[{\"type\":\"Availability\", \"message\":{\"results\":[{\"id\":\"5732800000005GQ\"}]}}]}";
		JSONObject object = new JSONObject(jsonS);
		JSONArray messages = (JSONArray) object.get("messages");
		
		//for test - developer org is different with others.
		try {
			System.out.println("messages.getJSONObject(0): " + messages.getJSONObject(0));
			System.out.println("messages.getJSONObject(0).get(\"String\"): " + messages.getJSONObject(0).get("type").equals("Availability"));
			if(messages.getJSONObject(0).getString("type") == "Availability") {
				System.out.println("has found type");
				//return true;
			}
		} catch(Exception ex) {
			
		}
	}
	
	public static void main(String[] args) {
		getUUID();
		JSONObject object = new JSONObject("{\"messages\":[{\"type\":\"Availability\",\"message\":{\"results\":[{\"id\":\"5732800000005GQ\"}]}}]}");
		JSONArray messages = (JSONArray) object.get("messages");
		String buttonId = "5732800000005GQ";
		Boolean flag = false;
		
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
		
		System.out.println("flag: " + flag);
		
		final String textMsgTemp = "{\"touser\":\"\",\"msgtype\":\"\",\"text\":{\"content\":\"\"}}";
		JSONObject obj = new JSONObject(textMsgTemp);
		obj.put("touser", "openId");
		obj.getJSONObject("content").put("text", "text");
		//obj.append("touser", "openId");
		//obj.accumulate("content", "This is a content.");
        System.out.println("body: " + obj.toString());
        
        try {
			System.out.println(URLDecoder.decode(URLDecoder.decode("æµ\u008bè¯\u0095", "UTF-8"), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("parse av------------------");
        parseAv();
	}
}
