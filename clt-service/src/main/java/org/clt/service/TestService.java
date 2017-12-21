package org.clt.service;

import java.util.List;
import java.util.Map;

import org.clt.repository.pojo.ConnectApp;
import org.clt.repository.pojo.FieldPermission;
import org.clt.repository.pojo.ObjectPermission;
import org.clt.service.base.ConnectAppService;
import org.clt.service.base.FieldPermissionService;
import org.clt.service.base.ObjectPermissionService;
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
	
	@SuppressWarnings("unused")
	@Autowired
	private ScopeService scopeService;
	
	@Autowired
	private ObjectPermissionService objectPermissionService;
	
	@Autowired
	private FieldPermissionService fieldPermissionService;
	
	@Autowired
	private AccessService accessService;
	
	public List<ConnectApp> getConnectAppByIdAndName(String conId, String name) {
		return this.connectAppService.findByContactIdAndName(conId, name);
	}
	
	public List<Map<String, Object>> getUserAppByIdAndName(String conId, String name) {
		return this.userAppService.findByContactIdAndName(conId, name);
	}
	
	public List<ObjectPermission> getObjectPermissions(String opId) {
		return this.objectPermissionService.findAllRowsById(opId);
	}
	
	public List<FieldPermission> getFieldPermissions(String opId) {
		return this.fieldPermissionService.findAllByOpId(opId);
	}
	
	public String getAccessToken(String conId) {
		return this.accessService.getAccessToken(conId);
	}
	
	public String getAccessTokenInfo(String token) {
		return this.accessService.getAccessTokenInfo(token);
	}
}
