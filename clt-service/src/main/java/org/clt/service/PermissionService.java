package org.clt.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.clt.repository.pojo.ConnectApp;
import org.clt.repository.pojo.Contact;
import org.clt.repository.pojo.WechatAccount;
import org.clt.repository.pojo.WechatTicket;
import org.clt.repository.pojo.WechatUser;
import org.clt.service.base.ConnectAppService;
import org.clt.service.base.UserService;
import org.clt.service.base.WechatAccountService;
import org.clt.service.base.WechatTicketService;
import org.clt.service.base.WechatUserService;
import org.clt.util.Token;
import org.json.JSONObject;
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
	
	public List<ConnectApp> getConnectAppByIdAndName(String conId, String name) {
		return this.connectAppService.findByContactIdAndName(conId, name);
	}
}
