package org.clt.service.impl.base;

import org.clt.repository.dao.FunctionPermissionDao;
import org.clt.repository.dao.GenericDao;
import org.clt.repository.pojo.FunctionPermission;
import org.clt.service.base.FunctionPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FunctionPermissionServiceImpl extends GenericServiceImpl<FunctionPermission, String> implements FunctionPermissionService {

	@Autowired
	public FunctionPermissionServiceImpl(FunctionPermissionDao genericDao) {
		super(genericDao);
		// TODO Auto-generated constructor stub
	}
	
}
