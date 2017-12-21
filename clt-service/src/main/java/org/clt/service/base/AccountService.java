package org.clt.service.base;

import java.util.Set;

import org.clt.repository.pojo.Account;
import org.springframework.stereotype.Service;

@Service
public interface AccountService extends GenericService<Account, String> {
	
	Account findByEnabledById(Set<String> fields, String id);
}
