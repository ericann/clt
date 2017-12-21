package org.clt.repository.dao;

import java.util.List;

import org.clt.repository.pojo.ObjectPermission;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ObjectPermissionDao extends GenericDao<ObjectPermission, String> {
	
	@Override
	@Query("SELECT op FROM ObjectPermission op WHERE op.id=:conId")
	List<ObjectPermission> findAllByContactId(@Param("conId") String conId);
	
	@Query("SELECT op,fp FROM ObjectPermission op LEFT JOIN op.fieldpermissions fp WHERE op.id=:opId")
	List<ObjectPermission> findAllRowsById(@Param("opId") String opId);
}
