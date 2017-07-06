package org.clt.service;

import java.util.ArrayList;
import java.util.List;

import org.clt.repository.dao.BasicConfigDao;
import org.clt.repository.pojo.BasicConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:applicationContext.xml"})
public class RefreshWechatAccessTokenTest {
	
	@Autowired
	private BasicConfigDao bcDao;
	
	@Autowired
	private UpdateWechatAccessTokenSchedule schedule;
	
	@Autowired
	private UpdateWechatAccessTokenTask task;
	
	@Test
	public void test() throws InterruptedException {
		
//		List<BasicConfig> bcL = this.bcDao.findAll();
//		List<UpdateWechatAccessTokenTask> tasks = null;
//		
//		if(bcL != null && bcL.size() > 0) {
//			tasks = new ArrayList<UpdateWechatAccessTokenTask>();
//			
//			for(BasicConfig bc : bcL) {
//				task = new UpdateWechatAccessTokenTask(bc);
//				tasks.add(task);
//			}
//			schedule.addTasks(tasks);
//			new Thread(schedule).start();
//		}
	}
}
