package org.clt.data.controller;

import org.clt.data.generic.DataListenerImpl;
import org.clt.repository.pojo.Contact;
import org.clt.service.base.ContactService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/data-api/Contact")
public class ContactListener extends DataListenerImpl<Contact, String> {
	
	private ContactService contactService;
	
	@Autowired
    public ContactListener(ContactService contactService) {
        super(contactService);
        this.contactService = contactService;
    }
	
	@RequestMapping(value = "/userInfor", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String findById(@RequestHeader("CLT-ACCESS-TOKEN") String token) {
		super.token = token;
		return new JSONObject(this.contactService.findById(super.getUserId())).toString();
	}
	
}
