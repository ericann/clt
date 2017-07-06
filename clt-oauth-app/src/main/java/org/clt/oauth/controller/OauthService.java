package org.clt.oauth.controller;

import java.util.Map;

import org.clt.util.DefaultMsg;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/oauth2")
public class OauthService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value="/token", method=RequestMethod.POST)
	public @ResponseBody String token(@RequestBody String json) {
		Map<String, Object> result = DefaultMsg.initErrorResult("E_1");
		
		try {
			
			JSONObject o = new JSONObject(json);
			String response_type = (String)o.get("response_type");
			String client_id = (String)o.get("client_id");
			String redirect_uri = (String)o.get("redirect_uri");
			
			switch(response_type) {
				case "code" :
					break;
				case "token" :
					break;
				default :
					result = DefaultMsg.getErrorResult("N_0", "");
			}
			
		} catch(JSONException ex) {
			logger.debug("JSONExcetion");
			ex.printStackTrace();
			result = DefaultMsg.getErrorResult("J_0", ex.getMessage());
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		
		return JSONObject.valueToString(result);
	}
	
	@RequestMapping(value="/authorize", method=RequestMethod.GET)
	public @ResponseBody String authorize(@RequestParam("grant_type") String grant_type,
			@RequestParam("client_id") String client_id, 
			@RequestParam("client_secret") String client_secret) {
		Map<String, Object> result = DefaultMsg.initErrorResult("E_1");
		
		try {
			
		} catch(JSONException ex) {
			logger.debug("JSONExcetion");
			ex.printStackTrace();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return "rediect: https://baidu.com";
		//return JSONObject.valueToString(result);
	}
}
