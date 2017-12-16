package org.clt.repository.dao;

import java.util.List;

import org.clt.repository.pojo.Sfdc;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SfdcDao extends GenericDao<Sfdc, String> {
	
	@Override
	@Query("SELECT sfdc FROM Sfdc sfdc LEFT JOIN sfdc.liveagents la WHERE la.account.id=(SELECT con.account.id FROM Contact con WHERE con.id=:conId)")
	List<Sfdc> findAllByContactId(@Param("conId") String conId);
}
