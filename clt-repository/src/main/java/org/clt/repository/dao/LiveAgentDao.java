package org.clt.repository.dao;

import org.clt.repository.pojo.LiveAgent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LiveAgentDao extends GenericDao<LiveAgent, String> {
	
	@Query("SELECT la FROM LiveAgent la WHERE la.sfdc.orgId=:orgId")
	LiveAgent findByOrgId(@Param("orgId") String orgId);
}
