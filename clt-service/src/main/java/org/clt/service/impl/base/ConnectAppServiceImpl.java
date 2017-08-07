package org.clt.service.impl.base;

import org.clt.repository.dao.ConnectAppDao;
import org.clt.repository.pojo.ConnectApp;
import org.clt.service.base.ConnectAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConnectAppServiceImpl extends GenericService<ConnectApp, String> implements ConnectAppService {
	
	@Autowired
	public ConnectAppServiceImpl(ConnectAppDao connectAppDao) {
		super(connectAppDao);
		// TODO Auto-generated constructor stub
	}
	
}
