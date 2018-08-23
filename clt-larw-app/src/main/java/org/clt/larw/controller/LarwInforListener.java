package org.clt.larw.controller;

import org.clt.service.LARWInforService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/larw")
public class LarwInforListener {
	
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private LARWInforService inforService;
	
	//保存当前的account对象
	@RequestMapping(value="/account", method=RequestMethod.POST)
	public @ResponseBody String insertBC_B(@RequestBody String json) {
		return this.inforService.addAccountCascade(json);
	}
	
	//保存当前的sfdc、liveagent、button，wechataccount及关联关系
	@RequestMapping(value="/sfdc", method=RequestMethod.POST)
	public @ResponseBody String insertAcc_Con(@RequestBody String json) {
		return this.inforService.addSfdcCascade(json);
	}
}