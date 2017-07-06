package org.clt.larw.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.clt.service.LARWInforService;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/larwint")
public class LarwInforListener {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private LARWInforService inforService;

	@RequestMapping(value="/insert/bc_b", method=RequestMethod.POST)
	public @ResponseBody String insertBC_B(@RequestBody String json) {
		
		logger.debug("-----------------come in insert-----------------");
		logger.debug("-- json:" + json);
		String result = null;
		try {
			logger.debug("-- json:" + new JSONObject(URLDecoder.decode(json, "utf-8")));
			result = this.inforService.addBasicConfigAndButton(json);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	@RequestMapping(value="/insert/acc_con", method=RequestMethod.POST)
	public @ResponseBody String insertAcc_Con(@RequestBody String json) {
		
		String result = null;
		try {
			logger.debug("-- json:" + new JSONObject(URLDecoder.decode(json, "utf-8")));
			result = this.inforService.addAccAndCon(json);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	@RequestMapping(value="/query/{id}/", method=RequestMethod.GET)
	public @ResponseBody String query(@RequestBody String json, @PathVariable("id") String id) {
		
		String result = null;
		try {
			logger.debug("-- json:" + new JSONObject(URLDecoder.decode(json, "utf-8")));
			result = this.inforService.findInfor(id, json);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(Exception ex) {
			
		}
		
		return result;
	}
	
	@RequestMapping(value="/query/p_infor/", method=RequestMethod.POST)
	public @ResponseBody String find(@RequestBody String json) {
		
		String result = null;
		try {
			logger.debug("-- json:" + new JSONObject(URLDecoder.decode(json, "utf-8")));
			result = this.inforService.findInforByPwd(json);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(Exception ex) {
			
		}
		
		return result;
	}
	
}
