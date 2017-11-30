package org.clt.repository.dao;

import java.util.List;

import org.clt.repository.pojo.ObjectPermission;
import org.clt.repository.pojo.Scope;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ScopeDao extends GenericDao<Scope, String> {
	
	@Override
	@Query("SELECT fp FROM Scope fp WHERE fp.id=:conId")
	List<Scope> findAllByContactId(@Param("conId") String conId);
}
