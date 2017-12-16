package org.clt.service.impl.base;

import java.util.List;

import org.clt.repository.dao.SfdcDao;
import org.clt.repository.pojo.Sfdc;
import org.clt.service.base.SfdcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SfdcServiceImpl extends GenericServiceImpl<Sfdc, String> implements SfdcService {

	private SfdcDao sfdcDao;
	
	@Autowired
	public SfdcServiceImpl(SfdcDao sfdcDao) {
		super(sfdcDao);
		this.sfdcDao = sfdcDao;
	}
	
	public List<Sfdc> findByContactIdAndName(String conId) {
		return this.sfdcDao.findAllByContactId(conId);
	}
	
}
