package org.clt.larw.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import org.clt.util.DefaultMsg;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/sfdc")
public class SFDCOauth {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RestTemplate http;
	
	@RequestMapping(value="/oauth/post", method=RequestMethod.POST)
	public @ResponseBody String forPost() {
		
		return "success";
	}
	
	@RequestMapping(value="/oauth/get", method=RequestMethod.GET)
	public @ResponseBody String forGet() {
		
		return "success";
	}
	
	@RequestMapping(value="/oauth/ws", method=RequestMethod.POST)
	public @ResponseBody String WSGetCode(@RequestBody String json) {
		
		logger.debug("-----------------come in WSGetCode-----------------");
		Map<String, Object> result = DefaultMsg.initErrorResult("E_1");
		
		try {
			logger.debug("-- json:" + json);
			String s = URLDecoder.decode(json, "UTF-8");
			JSONObject o = new JSONObject(s.substring(0, s.length() - 1));
			
			String base_uri = DefaultMsg.get("BASE_URL").replace("{0}", o.getString("pre_domain"));
			String path = DefaultMsg.get("O_CODE").replace("{0}", o.getString("code")).
					replace("{1}", o.getString("client_id")).
					replace("{2}", o.getString("client_secret")).
					replace("{3}", o.getString("redirect_uri"));
			String url = base_uri + path;
			
			String body = this.getUserConfirm(url, HttpMethod.POST).getBody().toString();
			result = DefaultMsg.getErrorResult("E_0", body);
		} catch(Exception ex) {
			System.out.println("-- ex: " + ex.getMessage());
		}
		
		return JSONObject.valueToString(result);
	}
	
	@RequestMapping(value="/transfer", method=RequestMethod.POST, consumes="application/x-www-form-urlencoded;charset=UTF-8")
	public @ResponseBody String transfer(@RequestBody String json) {
		System.out.println("-- o: " + json);
		Map<String, Object> result = DefaultMsg.initErrorResult("E_1");
		
		try {
			String s = URLDecoder.decode(json, "UTF-8");
			JSONObject o = new JSONObject(s.substring(0, s.length() - 1));
		
			String base_uri = DefaultMsg.get("BASE_URL").replace("{0}", o.getString("pre_domain"));
			String path = null;
			String url = null;
			
			switch((String)(o.get("flow"))) {
				case "0": 
					path = DefaultMsg.get("O_WS_CONFIRM").replace("{0}", o.getString("client_id")).replace("{1}", o.getString("redirect_uri"));
					url = base_uri + path;
					result = DefaultMsg.getErrorResult("E_0", url);
					break;
				case "1": 
					path = DefaultMsg.get("O_UA_CONFIRM").replace("{0}", o.getString("client_id")).replace("{1}", o.getString("redirect_uri"));
					url = base_uri + path;
					result = DefaultMsg.getErrorResult("E_0", url);
					break;
				case "2": 
					String secritytoken = "";
					try {
						secritytoken = o.getString("secritytoken");
					} catch(Exception ex) {
						
					}
					path = DefaultMsg.get("O_UP").replace("{0}", o.getString("client_id")).
							replace("{1}", o.getString("client_secret")).
							replace("{2}", o.getString("username")).
							replace("{3}", o.getString("password") + secritytoken);
					url = base_uri + path;
					String body = this.getUserConfirm(url, HttpMethod.POST).getBody().toString();
					result = DefaultMsg.getErrorResult("E_0", body);
					break;
				default: 
					break;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception ex) {
			//logger.debug("--ex: " + ex.printStackTrace());
		}
		
		System.out.println("--: " + result);
		return JSONObject.valueToString(result);
	}
	
	private ResponseEntity<String> getUserConfirm(String url, HttpMethod type) {
		return http.exchange(url, type, null, String.class);
	}
}
