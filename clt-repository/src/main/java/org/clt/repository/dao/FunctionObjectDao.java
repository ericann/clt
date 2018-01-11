package org.clt.repository.dao;

import java.util.List;

import org.clt.repository.pojo.FunctionObject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FunctionObjectDao extends GenericDao<FunctionObject, String> {
	
	@Override
	@Query("SELECT fp FROM FunctionObject fp WHERE fp.id=:conId")
	List<FunctionObject> findAllByContactId(@Param("conId") String conId);
}
