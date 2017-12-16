package org.clt.service;

import java.util.List;
import java.util.Map;

import org.clt.repository.pojo.ConnectApp;
import org.clt.repository.pojo.Scope;
import org.clt.repository.pojo.UserApp;
import org.clt.service.base.ConnectAppService;
import org.clt.service.base.ScopeService;
import org.clt.service.base.UserAppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
	
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ConnectAppService connectAppService;
	
	@Autowired
	private UserAppService userAppService;
	
	@Autowired
	private ScopeService scopeService;
	
	public List<ConnectApp> getConnectAppByIdAndName(String conId, String name) {
		return this.connectAppService.findByContactIdAndName(conId, name);
	}
	
	public List<Map<String, Object>> getUserAppByIdAndName(String conId, String name) {
		return this.userAppService.findByContactIdAndName(conId, name);
	}
	
}
