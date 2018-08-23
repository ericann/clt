package org.clt.main.controller;

import org.clt.service.MailService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/mail")
public class MailListener {
	
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MailService mailService;
	
	//保存当前的account对象
	@RequestMapping(value="/notifications", method=RequestMethod.POST)
	public @ResponseBody String sendEmails(@RequestBody String json, @RequestHeader("CLT-ACCESS-TOKEN") String token) {
		return JSONObject.valueToString(this.mailService.sendEmail(json));
	}
	
}