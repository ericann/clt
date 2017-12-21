package org.clt.service.impl.base;

import java.util.List;

import org.clt.repository.dao.ObjectPermissionDao;
import org.clt.repository.pojo.ObjectPermission;
import org.clt.service.base.ObjectPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObjectPermissionServiceImpl extends GenericServiceImpl<ObjectPermission, String> implements ObjectPermissionService {

	private ObjectPermissionDao objectPermissionDao;
	
	@Autowired
	public ObjectPermissionServiceImpl(ObjectPermissionDao objectPermissionDao) {
		super(objectPermissionDao);
		this.objectPermissionDao = objectPermissionDao;
	}

	@Override
	public List<ObjectPermission> findAllRowsById(String opId) {
		return objectPermissionDao.findAllRowsById(opId);
	}
	
}
