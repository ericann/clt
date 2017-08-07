package org.clt.service.impl.base;

import org.clt.repository.dao.GenericDao;
import org.clt.repository.dao.ScopeDao;
import org.clt.repository.pojo.Scope;
import org.clt.service.base.ScopeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScopeServiceImpl extends GenericService<Scope, String> implements ScopeService {

	@Autowired
	public ScopeServiceImpl(ScopeDao genericDao) {
		super(genericDao);
		// TODO Auto-generated constructor stub
	}
	
}
