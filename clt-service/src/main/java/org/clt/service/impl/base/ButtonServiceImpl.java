package org.clt.service.impl.base;

import org.clt.repository.dao.ButtonDao;
import org.clt.repository.pojo.Button;
import org.clt.service.base.ButtonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ButtonServiceImpl extends GenericServiceImpl<Button, String> implements ButtonService {
	
	@Autowired
	public ButtonServiceImpl(ButtonDao buttonDao) {
		super(buttonDao);
		// TODO Auto-generated constructor stub
	}
	
}
