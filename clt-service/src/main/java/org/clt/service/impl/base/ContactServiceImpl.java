package org.clt.service.impl.base;

import java.util.List;

import org.clt.repository.dao.ContactDao;
import org.clt.repository.pojo.Contact;
import org.clt.service.base.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl extends GenericServiceImpl<Contact, String> implements ContactService {
	
	private ContactDao contactDao;
	
	@Autowired
	public ContactServiceImpl(ContactDao contactDao) {
		super(contactDao);
		// TODO Auto-generated constructor stub
		this.contactDao = contactDao;
	}

	//@Override
	public List<Contact> findByContactId(String conId) {
		// TODO Auto-generated method stub
		return this.contactDao.findAllByContactId(conId);
	}
	
	@Override
	public Contact findByMobileAndEmail(String mobile, String email) {
		
		return this.contactDao.findByMobileAndEmail(mobile, email);
	}
	
}
