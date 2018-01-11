package org.clt.repository.dao;

import java.util.List;

import org.clt.repository.pojo.LiveAgent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LiveAgentDao extends GenericDao<LiveAgent, String> {
	
	@Query("SELECT la FROM LiveAgent la WHERE la.sfdc.orgId=:orgId")
	LiveAgent findByOrgId(@Param("orgId") String orgId);
	
	@Override
	@Query("SELECT la FROM LiveAgent la WHERE la.account.id=(SELECT con.account.id FROM Contact con WHERE con.id=:conId)")
	List<LiveAgent> findAllByContactId(@Param("conId") String conId);
}
