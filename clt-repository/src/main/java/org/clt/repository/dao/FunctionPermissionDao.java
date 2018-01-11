package org.clt.repository.dao;

import java.util.List;

import org.clt.repository.pojo.FunctionPermission;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FunctionPermissionDao extends GenericDao<FunctionPermission, String> {
	
	@Override
	@Query("SELECT fp FROM FunctionPermission fp WHERE fp.id=:conId")
	List<FunctionPermission> findAllByContactId(@Param("conId") String conId);
}
