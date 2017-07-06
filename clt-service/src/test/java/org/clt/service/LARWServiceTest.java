package org.clt.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:applicationContext.xml"})
public class LARWServiceTest {
	
	@Autowired
	private LARWService larwService;
	private final String xmlString = "<xml><ToUserName><![CDATA[gh_c55f730e90a2]]></ToUserName><FromUserName><![CDATA[onw0TtzUnI78FWeZi10XKGn-QKYw]]></FromUserName><CreateTime>1348831860</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[this is a test.这是一个测试语句。]]></Content><MsgId>1234567890123456</MsgId></xml>";
	
	@Test
	public void test() {
		//this.larwService.setXmlString(this.xmlString);
		//this.larwService.startChat();
		String jsonS = "{\"messages\":[{\"type\":\"Availability\", \"message\":{\"results\":[{\"id\":\"5732800000005GQ\"}]}}]}";
		JSONObject object = new JSONObject(jsonS);
		JSONArray messages = (JSONArray) object.get("messages");
		
		//for test - developer org is different with others.
		try {
			System.out.println("messages.getJSONObject(0): " + messages.getJSONObject(0));
			if(messages.getJSONObject(0).getString("type") == "Availability") {
				System.out.println("has found type");
				//return true;
			}
		} catch(Exception ex) {
			
		}
	}
}
