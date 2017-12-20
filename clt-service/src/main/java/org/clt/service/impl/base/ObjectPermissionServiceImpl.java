package org.clt.service.impl.base;

import org.clt.repository.dao.ObjectPermissionDao;
import org.clt.repository.pojo.ObjectPermission;
import org.clt.service.base.ObjectPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObjectPermissionServiceImpl extends GenericServiceImpl<ObjectPermission, String> implements ObjectPermissionService {

	@Autowired
	public ObjectPermissionServiceImpl(ObjectPermissionDao genericDao) {
		super(genericDao);
		// TODO Auto-generated constructor stub
	}
	
}
