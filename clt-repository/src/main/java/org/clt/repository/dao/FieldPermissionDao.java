package org.clt.repository.dao;

import java.util.List;

import org.clt.repository.pojo.FieldPermission;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FieldPermissionDao extends GenericDao<FieldPermission, String> {
	
	@Override
	@Query("SELECT fp FROM FieldPermission fp WHERE fp.id=:conId")
	List<FieldPermission> findAllByContactId(@Param("conId") String conId);
}
