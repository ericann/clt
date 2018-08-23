package org.clt.service.base;

import org.clt.repository.pojo.Contact;
import org.springframework.stereotype.Service;

@Service
public interface ContactService extends GenericService<Contact, String> {
	
	Contact findByMobileAndEmail(String mobile, String email);
}
