package org.clt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import org.clt.repository.dao.BasicConfigDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
public class UpdateWechatAccessTokenSchedule implements Runnable {
	
	@Autowired
	private RestTemplate http;
	
	@Autowired
	private BasicConfigDao basicConfigDao;
	
	private List<UpdateWechatAccessTokenTask> tasks;
	private final Integer refreshTime = 6000000;
	private Timer timer;
	
	public UpdateWechatAccessTokenSchedule() {
		this.timer = new Timer();
		this.tasks = new ArrayList<UpdateWechatAccessTokenTask>();
	}
	
	@Override
	public void run() {
		for(UpdateWechatAccessTokenTask task : tasks) {
			this.refresh(task);
		}
	}
	
	public void addTask(UpdateWechatAccessTokenTask task) {
		
		this.tasks.add(this.initTask(task));
		this.refresh(task);
	}
	
	public void addTasks(List<UpdateWechatAccessTokenTask> tasks) {
		for(UpdateWechatAccessTokenTask task : tasks) {
			this.tasks.add(this.initTask(task));
		}
		//this.tasks.addAll(tasks);
	}
	
	private void refresh(UpdateWechatAccessTokenTask task) {
		System.out.println(Thread.currentThread().getName());
		this.timer.schedule(task, 1000, this.refreshTime);
	}
	
	private UpdateWechatAccessTokenTask initTask(UpdateWechatAccessTokenTask task) {
		System.out.println("before: http: " + http + ", basicConfigDao: " + basicConfigDao);
		task.init(this.http, this.basicConfigDao);
		System.out.println("after: http: " + http + ", basicConfigDao: " + basicConfigDao);
		return task;
	}
}
