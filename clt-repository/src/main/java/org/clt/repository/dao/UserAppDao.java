package org.clt.repository.dao;

import java.util.List;
import java.util.Map;

import org.clt.repository.pojo.UserApp;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserAppDao extends GenericDao<UserApp, String> {
	
	@Query(value = "SELECT ua.id as uaId," + 
						"ua.contact.id as conId," +
						"ua.contact.openId as openId," +
						"ua.contact.name as con_name," +
						"ca.id as caId," +
						"s.name as m_name,s.id as m_id," + 
						"fp.id as fpId," + 
						"op.id as opId,op.objectName as op_name " + 
					"FROM UserApp ua " +
						"LEFT JOIN ua.connectApp ca " +
						"LEFT JOIN ua.connectApp.scopes s " + 
						"LEFT JOIN s.functionpermission fp " + 
						"LEFT JOIN fp.functionobjects fo " +
						"LEFT JOIN fo.objectpermission op " +
					"WHERE ua.contact.id=:conId AND ua.name=:name")
	//@Query("SELECT ua.connectApp.scopes FROM UserApp ua WHERE ua.contact.id=:conId AND ua.name=:name")
	public List<Map<String, Object>> findByContactIdAndName(@Param("conId") String conId, @Param("name") String name);
	
	@Query(value = "SELECT ua.id as uaId," +
			"ua.contact.id as conId," +
			"ua.contact.openId as openId," +
			"ua.contact.name as con_name," +
			"ca.id as caId," +
			"s.name as m_name,s.id as m_id," + 
			"fp.id as fpId," + 
			"op.id as opId,op.objectName as op_name " + 
		"FROM UserApp ua " +
			"LEFT JOIN ua.connectApp ca " +
			"LEFT JOIN ua.connectApp.scopes s " + 
			"LEFT JOIN s.functionpermission fp " + 
			"LEFT JOIN fp.functionobjects fo " +
			"LEFT JOIN fo.objectpermission op " +
		"WHERE ua.contact.id=:conId")
	//@Query("SELECT ua.connectApp.scopes FROM UserApp ua WHERE ua.contact.id=:conId AND ua.name=:name")
	public List<Map<String, Object>> findByContactId(@Param("conId") String conId);
	
	@Query(value = "SELECT ca.id as caId," +
			"s.name as m_name,s.id as m_id," + 
			"fp.id as fpId," + 
			"op.id as opId,op.objectName as op_name " + 
		"FROM UserApp ua " +
			"LEFT JOIN ua.connectApp ca " +
			"LEFT JOIN ua.connectApp.scopes s " + 
			"LEFT JOIN s.functionpermission fp " + 
			"LEFT JOIN fp.functionobjects fo " +
			"LEFT JOIN fo.objectpermission op " +
		"WHERE ua.name=:name")
	public List<Map<String, Object>> findByName(@Param("name") String name);
	
	@Query(value = "SELECT op.id as opId," +
			"op.objectName as op_name " + 
		"FROM UserApp ua " +
			"LEFT JOIN ua.connectApp ca " +
			"LEFT JOIN ua.connectApp.scopes s " + 
			"LEFT JOIN s.functionpermission fp " + 
			"LEFT JOIN fp.functionobjects fo " +
			"LEFT JOIN fo.objectpermission op " +
		"WHERE ua.name=:name")
	public List<Map<String, Object>> findObjectsByName(@Param("name") String name);
	
	@Query(value = "SELECT op.id as opId," +
			"op.objectName as op_name " + 
		"FROM UserApp ua " +
			"LEFT JOIN ua.connectApp ca " +
			"LEFT JOIN ua.connectApp.scopes s " + 
			"LEFT JOIN s.functionpermission fp " + 
			"LEFT JOIN fp.functionobjects fo " +
			"LEFT JOIN fo.objectpermission op " +
		"WHERE ua.contact.id=:conId")
	public List<Map<String, Object>> findObjectsByConId(@Param("conId") String conId);
	
	@Override
	@Query("SELECT fp FROM UserApp fp WHERE fp.id=:conId")
	List<UserApp> findAllByContactId(@Param("conId") String conId);
}
