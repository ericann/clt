package org.clt.service.base;

import java.util.List;

import org.clt.repository.pojo.FieldPermission;
import org.springframework.stereotype.Service;

@Service
public interface FieldPermissionService extends GenericService<FieldPermission, String> {
	
	List<FieldPermission> findAllByOpId(String opId);
}
