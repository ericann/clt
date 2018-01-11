package org.clt.service;

import java.util.List;
import java.util.UUID;

import org.aspectj.lang.JoinPoint;
import org.clt.repository.pojo.ConnectApp;
import org.clt.repository.pojo.Contact;
import org.clt.repository.pojo.UserApp;
import org.clt.service.base.ConnectAppService;
import org.clt.service.base.UserAppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {
	
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ConnectAppService connectAppService;
	
	@Autowired
	private UserAppService userAppService;
	
	public List<ConnectApp> getConnectAppByIdAndName(String conId, String name) {
		return this.connectAppService.findByContactIdAndName(conId, name);
	}
	
	public void grantPermissions(JoinPoint jp) {
		System.out.println("--- go in grantPermissions ---");
		String conId = null;
		List<ConnectApp> caL = this.connectAppService.findAll();
		Contact con = new Contact();
		con.setId(conId);
		
		for(ConnectApp ca : caL) {
			UserApp ua = new UserApp();
			ua.setConnectApp(ca);
			ua.setId(UUID.randomUUID().toString());
			ua.setContact(con);
			
			this.userAppService.save(ua);
		}
	}
}
