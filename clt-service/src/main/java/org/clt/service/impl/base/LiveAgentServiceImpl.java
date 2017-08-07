package org.clt.service.impl.base;

import org.clt.repository.dao.GenericDao;
import org.clt.repository.dao.LiveAgentDao;
import org.clt.repository.pojo.LiveAgent;
import org.clt.service.base.LiveAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LiveAgentServiceImpl extends GenericService<LiveAgent, String> implements LiveAgentService {

	@Autowired
	public LiveAgentServiceImpl(LiveAgentDao genericDao) {
		super(genericDao);
		// TODO Auto-generated constructor stub
	}
	
}
