package org.clt.larw.controller;

import java.util.Map;

import org.clt.service.AccessService;
import org.clt.service.BasicConfigService;
import org.clt.service.LARWService;
import org.clt.service.WechatEventService;
import org.clt.service.WechatTokenService;
import org.clt.util.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/wechat")
public class WechatListener {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private LARWService larwService;
	
	@Autowired
	private WechatTokenService w_tokenS;
	
	@Autowired
	private BasicConfigService bcService;
	
	@Autowired
	private WechatEventService wechatEventService;
	
	@SuppressWarnings("unused")
	@Autowired
	private AccessService accessService;
	
	@RequestMapping(value="/msg/text", method=RequestMethod.GET)
	public @ResponseBody String verification_v1(@RequestParam String signature,@RequestParam String timestamp,
			@RequestParam String nonce, @RequestParam String echostr) {
		
		return echostr;
		//return this.verification(signature, timestamp, nonce, echostr);
	}
	
	@RequestMapping(value="/msg/text", method=RequestMethod.POST, consumes="text/xml")
	public @ResponseBody String textMsg(@RequestBody String xml) {
		
		return this.msg(xml);
	}
	
	@RequestMapping(value="/msg/", method=RequestMethod.GET)
	public @ResponseBody String verification(@RequestParam String signature,@RequestParam String timestamp,
			@RequestParam String nonce, @RequestParam String echostr) {
		
		logger.debug("-----------------come in WechatListener-----------------");
		
		String result = "Verifcation is failed.";
		Boolean flag = this.larwService.verfiyAddress(signature, timestamp, nonce);
		
		return flag ? echostr : result;
	}
	
	@RequestMapping(value="/msg/", method=RequestMethod.POST, consumes="text/xml")
	public @ResponseBody String msg(@RequestBody String xml) {
		logger.debug("-- WechatListener xml: " + xml);
		final String MESSAGE = "success";
		
		Map<String, String> result = XML.parse(xml);
		
		switch(result.get("MsgType")) {
			case "event" :
				wechatEventService.checkEvent(result);
				break;
			case "text" : 
				this.larwService.setXmlString(xml);
				new Thread(this.larwService).start();
				break;
			default: 
				
		}
		
		return MESSAGE;
	}
	
	@RequestMapping(value="/token/refresh/single", method=RequestMethod.POST)
	public @ResponseBody String refreshToken(@RequestBody String json) {
		
		return (this.w_tokenS.refreshTokenByWechatAccount(json) ? "Refresh success." : "Refresh failed." );
		//return true;
	}
	
	@RequestMapping(value="/token/refresh/all", method=RequestMethod.POST)
	public @ResponseBody String refreshTokens(@RequestBody String json) {
		
		return (this.w_tokenS.refreshTokensByWechatAccount(json) ? "Refresh All success." : "Refresh failed." );
		//return true;
	}
	
	@RequestMapping(value="/bi/infor", method=RequestMethod.POST)
	public @ResponseBody String getAll(@RequestBody String json) {
		logger.debug("-- getAll json:" + json);
		String expect = "refresh_v1.0";
		String error = "UnSupported Interface.";
		//json  For secret
		
		return expect.equals(json) ? this.bcService.getAll() : error;
	}
	
}
