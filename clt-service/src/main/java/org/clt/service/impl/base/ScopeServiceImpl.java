package org.clt.service.impl.base;

import java.util.List;

import org.clt.repository.dao.ScopeDao;
import org.clt.repository.pojo.Scope;
import org.clt.service.base.ScopeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScopeServiceImpl extends GenericServiceImpl<Scope, String> implements ScopeService {

	private ScopeDao scopeDao;
	
	@Autowired
	public ScopeServiceImpl(ScopeDao scopeDao) {
		super(scopeDao);
		this.scopeDao = scopeDao;
	}

	@Override
	public List<Scope> findAllByContactId(String conId) {
		// TODO Auto-generated method stub
		return this.scopeDao.findAllByContactId(conId);
	}
	
}
