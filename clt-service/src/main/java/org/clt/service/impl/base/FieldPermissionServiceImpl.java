package org.clt.service.impl.base;

import java.util.List;

import org.clt.repository.dao.FieldPermissionDao;
import org.clt.repository.pojo.FieldPermission;
import org.clt.service.base.FieldPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FieldPermissionServiceImpl extends GenericServiceImpl<FieldPermission, String> implements FieldPermissionService {
	
	private FieldPermissionDao fieldPermissionDao;
	
	@Autowired
	public FieldPermissionServiceImpl(FieldPermissionDao fieldPermissionDao) {
		super(fieldPermissionDao);
		this.fieldPermissionDao = fieldPermissionDao;
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<FieldPermission> findAllByOpId(String opId) {
		// TODO Auto-generated method stub
		return fieldPermissionDao.findAllByOpId(opId);
	}
	
}
