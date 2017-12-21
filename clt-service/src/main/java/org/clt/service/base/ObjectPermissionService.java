package org.clt.service.base;

import java.util.List;

import org.clt.repository.pojo.ObjectPermission;
import org.springframework.stereotype.Service;

@Service
public interface ObjectPermissionService extends GenericService<ObjectPermission, String> {
	
	List<ObjectPermission> findAllRowsById(String opId);
}
