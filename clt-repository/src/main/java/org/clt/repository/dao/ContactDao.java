package org.clt.repository.dao;

import java.util.List;

import org.clt.repository.pojo.Contact;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContactDao extends GenericDao<Contact, String> {
	
	@Query("SELECT con FROM Contact con WHERE con.account.id=:accId")
	public Contact findByAccountId(@Param("accId") String accId);
	
	@Query("SELECT con FROM Contact con WHERE con.mobile=:mobile AND con.password=:password")
	public Contact findByMobileAndPassword(@Param("mobile") String mobile, @Param("password") String password);

	@Query("SELECT con FROM Contact con WHERE con.mobile=:mobile AND con.password=:email")
	public Contact findByMobileAndEmail(@Param("mobile") String mobile, @Param("email") String password);
	
	@Query("SELECT con FROM Contact con WHERE con.sessionId=:sessionId")
	public Contact findBySessionId(@Param("sessionId") String sessionId);
	
	@Modifying
	@Query("UPDATE Contact c SET c.sessionId=:sessionId WHERE id=:id")
	public Contact updateBySessionIdAndId(@Param("sessionId") String sessionId, @Param("id") String id);
	
	@Override
	@Query("SELECT c FROM Contact c WHERE c.account.id=(SELECT con.account.id FROM Contact con WHERE con.id=:conId)")
	public List<Contact> findAllByContactId(@Param("conId") String conId);

}
