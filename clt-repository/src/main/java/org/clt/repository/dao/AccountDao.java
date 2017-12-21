package org.clt.repository.dao;

import java.util.List;

import org.clt.repository.pojo.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountDao extends GenericDao<Account, String>, CustomFieldsSingleObjectDao<Account, String> {
	
	@Override
	@Query("SELECT acc FROM Account acc WHERE acc.id=(SELECT con.account.id FROM Contact con WHERE con.id=:conId)")
	List<Account> findAllByContactId(@Param("conId") String conId);
	
}
