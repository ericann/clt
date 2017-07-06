package org.clt.larw.controller;

import java.util.ArrayList;
import java.util.List;

import org.clt.repository.dao.BasicConfigDao;
import org.clt.repository.pojo.BasicConfig;
import org.clt.service.UpdateWechatAccessTokenSchedule;
import org.clt.service.UpdateWechatAccessTokenTask;
import org.clt.service.WechatTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestListener {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BasicConfigDao bcDao;

	@Autowired
	private UpdateWechatAccessTokenSchedule schedule;
	
	@Autowired
	private UpdateWechatAccessTokenTask task;
	
	@Autowired
	private WechatTokenService refreshWToken;
	
	@RequestMapping(value="/refresh/all", method=RequestMethod.GET)
	public @ResponseBody void verification() {
		
		logger.debug("-----------------In TestListener-----------------");
		
		this.refreshWToken.refreshAll();
		
		logger.debug("-----------------Out TestListener-----------------");
	}
	
	@RequestMapping(value="/refresh/thread", method=RequestMethod.GET)
	public @ResponseBody void threadTest() {
		
		List<BasicConfig> bcL = this.bcDao.findAll();
		List<UpdateWechatAccessTokenTask> tasks = null;
		
		if(bcL != null && bcL.size() > 0) {
	
			tasks = new ArrayList<UpdateWechatAccessTokenTask>();
			
			for(BasicConfig bc : bcL) {
				task.setBasicConfig(bc);
				//UpdateWechatAccessTokenTask task = new UpdateWechatAccessTokenTask(bc);
				tasks.add(task);
			}
			schedule.addTasks(tasks);
			new Thread(schedule).start();
		}
	}
}
