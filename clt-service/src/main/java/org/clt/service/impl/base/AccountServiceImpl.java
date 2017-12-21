package org.clt.service.impl.base;

import java.util.Set;

import org.clt.repository.dao.AccountDao;
import org.clt.repository.pojo.Account;
import org.clt.service.base.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl extends GenericServiceImpl<Account, String> implements AccountService {
	
	private AccountDao accountDao;
	
	@Autowired
	public AccountServiceImpl(AccountDao accountDao) {
		super(accountDao);
		// TODO Auto-generated constructor stub
		this.accountDao = accountDao;
	}

	@Override
	public Account findByEnabledById(Set<String> fields, String id) {
		
		return this.accountDao.findEnableFieldsById(fields, id);
	}
	
}
