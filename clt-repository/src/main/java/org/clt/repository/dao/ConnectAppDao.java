package org.clt.repository.dao;

import java.util.List;

import org.clt.repository.pojo.ConnectApp;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ConnectAppDao extends GenericDao<ConnectApp, String> {
	
	//@Query("SELECT ca FROM ConnectApp ca WHERE ca.userapps.id=:conId AND ca.userapps.name=:name")
	@Query("SELECT s,ca,ua FROM UserApp ua, Scope s,ConnectApp ca  WHERE ua.connectApp.id=ca.id AND s.connectApp.id=ca.id AND ua.contact.id=:conId AND ua.name=:name")
	public List<ConnectApp> findByContactIdAndName(@Param("conId") String conId, @Param("name") String name);
	
	@Override
	@Query("SELECT ca FROM ConnectApp ca LEFT JOIN ca.userapps ua LEFT JOIN ua.contact c WHERE c.id=(SELECT con.id FROM Contact con WHERE con.id=:conId)")
	List<ConnectApp> findAllByContactId(@Param("conId") String conId);
}
