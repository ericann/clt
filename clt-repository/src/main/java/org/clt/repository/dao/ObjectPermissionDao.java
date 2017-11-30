package org.clt.repository.dao;

import java.util.List;

import org.clt.repository.pojo.FunctionPermission;
import org.clt.repository.pojo.ObjectPermission;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ObjectPermissionDao extends GenericDao<ObjectPermission, String> {
	
	@Override
	@Query("SELECT fp FROM ObjectPermission fp WHERE fp.id=:conId")
	List<ObjectPermission> findAllByContactId(@Param("conId") String conId);
}
