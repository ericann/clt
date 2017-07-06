package org.clt.repository.dao;

import org.clt.repository.pojo.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface AccountDao extends Repository<Account, String> {
	
	public Account save(Account acc);
	
	@Query("SELECT a FROM Account a WHERE a.id=:id")
	public Account findById(@Param("id") String id);
	
	//@Query("SELECT a FROM Account WHERE a.id=:id")
	//public Account findByIdAndMaster(@Param("id") String id, @Param("master") Boolean master);
}
