package org.clt.larw.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.clt.service.AccessService;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserListener {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AccessService accessService;

	@RequestMapping(value="/login/", method=RequestMethod.POST, consumes="application/json")
	public @ResponseBody String login(@RequestBody String json) {
		
		logger.debug("-----------------come in login-----------------");
		logger.debug("-- json:" + json);
		String result = null;
		try {
			logger.debug("-- json:" + new JSONObject(URLDecoder.decode(json, "utf-8")));
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
}
