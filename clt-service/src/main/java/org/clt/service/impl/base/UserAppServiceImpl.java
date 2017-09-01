package org.clt.service.impl.base;

import org.clt.repository.dao.GenericDao;
import org.clt.repository.dao.UserAppDao;
import org.clt.repository.pojo.UserApp;
import org.clt.service.base.UserAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAppServiceImpl extends GenericServiceImpl<UserApp, String> implements UserAppService {

	@Autowired
	public UserAppServiceImpl(UserAppDao genericDao) {
		super(genericDao);
		// TODO Auto-generated constructor stub
	}
	
}
