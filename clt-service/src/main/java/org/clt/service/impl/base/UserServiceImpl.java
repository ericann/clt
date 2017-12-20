package org.clt.service.impl.base;

import org.clt.repository.dao.ContactDao;
import org.clt.repository.pojo.Contact;
import org.clt.service.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends GenericServiceImpl<Contact, String> implements UserService {

	@Autowired
	public UserServiceImpl(ContactDao genericDao) {
		super(genericDao);
		// TODO Auto-generated constructor stub
	}
	
}
