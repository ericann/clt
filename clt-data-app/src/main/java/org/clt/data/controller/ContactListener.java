package org.clt.data.controller;

import org.clt.data.generic.DataListenerImpl;
import org.clt.repository.pojo.Contact;
import org.clt.service.base.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/data-api/Contact")
public class ContactListener extends DataListenerImpl<Contact, String> {
	
	@Autowired
    public ContactListener(ContactService contactService) {
        super(contactService);
    }
}
