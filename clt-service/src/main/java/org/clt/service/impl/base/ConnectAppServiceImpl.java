package org.clt.service.impl.base;

import java.util.List;

import org.clt.repository.dao.ConnectAppDao;
import org.clt.repository.pojo.ConnectApp;
import org.clt.service.base.ConnectAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConnectAppServiceImpl extends GenericServiceImpl<ConnectApp, String> implements ConnectAppService {
	
	private ConnectAppDao connectAppDao;
	
	@Autowired
	public ConnectAppServiceImpl(ConnectAppDao connectAppDao) {
		super(connectAppDao);
		this.connectAppDao = connectAppDao;
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<ConnectApp> findByContactIdAndName(String conId, String name) {
		return this.connectAppDao.findByContactIdAndName(conId, name);
	}
	
	
}
