package org.clt.larw.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.clt.repository.pojo.ConnectApp;
import org.clt.repository.pojo.FieldPermission;
import org.clt.repository.pojo.ObjectPermission;
import org.clt.service.TestService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestListener {
	
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TestService testService;
	
	
	@RequestMapping(value="/getMenus/0", method=RequestMethod.GET)
	public @ResponseBody String getMenus_0(@RequestParam String conId, @RequestParam String name) {
		
		List<ConnectApp> caL = this.testService.getConnectAppByIdAndName(conId, name);
		return JSONObject.valueToString(caL);
	}
	
	@RequestMapping(value="/getMenus/1", method=RequestMethod.GET)
	public @ResponseBody String getMenus_1(@RequestParam String conId, @RequestParam String name) {
		
		List<Map<String, Object>> uaL = this.testService.getUserAppByIdAndName(conId, name);
//		for(UserApp ua : uaL) {
//			logger.debug("--uaId: " + ua.getId());
//			logger.debug("--caId: " + ua.getConnectapp().getId());
//			logger.debug("--scopes: " + ua.getConnectapp().getScopes());
//		}
		return JSONObject.valueToString(uaL);
	}
	
	@RequestMapping(value="/op/{opId}", method=RequestMethod.GET)
	public @ResponseBody String getOPs(@PathVariable("opId") String opId) {
		
		List<ObjectPermission> caL = this.testService.getObjectPermissions(opId);
		return JSONObject.valueToString(caL);
	}
	
	@RequestMapping(value="/fp/{opId}/all", method=RequestMethod.GET)
	public @ResponseBody String getFPs(@PathVariable("opId") String opId) {
		
		List<FieldPermission> caL = this.testService.getFieldPermissions(opId);
		return JSONObject.valueToString(caL);
	}
	
	@RequestMapping(value="/at/{conId}", method=RequestMethod.GET)
	public @ResponseBody String getAccessToken(@PathVariable("conId") String conId) {
		
		return this.testService.getAccessToken(conId);
	}
	
	@RequestMapping(value="/at/{token}/p", method=RequestMethod.GET)
	public @ResponseBody String parseAccessToken(@PathVariable("token") String token) {
		
		return this.testService.getAccessTokenInfo(token);
	}
	
	private Map<String, Object> sortMenu(List<Map<String, Object>> maps) {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> menu = new HashMap<String, Object>(); 
		
		for(Map<String, Object> m : maps) {
			if(result.get("uaId") == null) {
				result.put("uaId", m.get("uaId"));
			}
			
			if(result.get("caId") == null) {
				result.put("caId", m.get("caId"));
			}
			
			if(menu.get(m.get("m_id")) == null) {
				Object mId = m.get("m_id");
				Object mName = m.get("m_name");
				Object fpId = m.get("fpId");
				Object opId = m.get("opId");
				Object opName = m.get("op_name");
				
				menu.put("m_id", mId);
				menu.put("m_name", mName);
				menu.put("fpId", fpId);
				
				Map<String, Object> objs = new HashMap<String, Object>();
				objs.put("opId", m.get("opId"));
				objs.put("op_name", m.get("op_name"));
				
				menu.put("objs", objs);
			} else {
				if(((Map<String,Object>) menu.get(m.get("m_id"))).get("objs") != null) {
					Map<String, Object> objs = new HashMap<String, Object>();
					objs.put("opId", m.get("opId"));
					objs.put("op_name", m.get("op_name"));
					
					menu.put("objs", objs);
				}
			}
			
		}
		
		return result;
	}
	
//	@RequestMapping(value="/getMenus/2", method=RequestMethod.GET)
//	public @ResponseBody String getMenus_2(@RequestParam String conId,@RequestParam String name) {
//		
//		List<Scope> uaL = this.testService.getUserAppById(conId);
//		return JSONObject.valueToString(uaL);
//	}
}
