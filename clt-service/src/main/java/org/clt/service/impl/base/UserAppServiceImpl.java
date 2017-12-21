package org.clt.service.impl.base;

import java.util.List;
import java.util.Map;

import org.clt.repository.dao.UserAppDao;
import org.clt.repository.pojo.UserApp;
import org.clt.service.base.UserAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAppServiceImpl extends GenericServiceImpl<UserApp, String> implements UserAppService {

	private UserAppDao userAppDao;
	
	@Autowired
	public UserAppServiceImpl(UserAppDao userAppDao) {
		super(userAppDao);
		this.userAppDao = userAppDao;
	}

	@Override
	public List<Map<String, Object>> findByContactIdAndName(String conId, String name) {
		return this.userAppDao.findByContactIdAndName(conId, name);
	}

	@Override
	public List<Map<String, Object>> findByContactId(String conId) {
		// TODO Auto-generated method stub
		return this.userAppDao.findByContactId(conId);
	}
	
}
