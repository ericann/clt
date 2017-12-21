package org.clt.repository.dao;

import java.util.List;

import org.clt.repository.pojo.FieldPermission;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FieldPermissionDao extends GenericDao<FieldPermission, String> {
	
	@Override
	@Query("SELECT fp FROM FieldPermission fp WHERE fp.id=:conId")
	List<FieldPermission> findAllByContactId(@Param("conId") String conId);
	
	@Query("SELECT fp FROM FieldPermission fp WHERE fp.objectpermission.id=:opId")
	List<FieldPermission> findAllByOpId(@Param("opId") String opId);
}
