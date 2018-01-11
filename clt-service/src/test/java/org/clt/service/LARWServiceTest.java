package org.clt.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:applicationContext.xml"})
public class LARWServiceTest {
	
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
