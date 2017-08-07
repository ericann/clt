package org.clt.service;

import org.clt.repository.pojo.Contact;
import org.clt.service.base.GenericService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends GenericService<Contact, String> {
	
}
