package org.clt.service.impl.base;

import org.clt.repository.dao.FunctionObjectDao;
import org.clt.repository.pojo.FunctionObject;
import org.clt.service.base.FunctionObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FunctionObjectServiceImpl extends GenericServiceImpl<FunctionObject, String> implements FunctionObjectService {

	@Autowired
	public FunctionObjectServiceImpl(FunctionObjectDao genericDao) {
		super(genericDao);
		// TODO Auto-generated constructor stub
	}
	
}
