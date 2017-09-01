package org.clt.service.impl.base;

import org.clt.repository.dao.FieldPermissionDao;
import org.clt.repository.pojo.FieldPermission;
import org.clt.service.base.FieldPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FieldPermissionServiceImpl extends GenericServiceImpl<FieldPermission, String> implements FieldPermissionService {

	@Autowired
	public FieldPermissionServiceImpl(FieldPermissionDao genericDao) {
		super(genericDao);
		// TODO Auto-generated constructor stub
	}
	
}
