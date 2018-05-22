package org.clt.data.controller;

import java.util.HashSet;
import java.util.Set;

import org.clt.data.generic.DataListenerImpl;
import org.clt.repository.pojo.Account;
import org.clt.service.base.AccountService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/data-api/Account")
public class AccountListener extends DataListenerImpl<Account, String> {
	
	private AccountService accountService;
	
	@Autowired
    public AccountListener(AccountService accountService) {
        super(accountService);
        this.accountService = accountService;
    }
	
	@RequestMapping("/{id}")
	public @ResponseBody String findById(@PathVariable("id") String id) {
		//@PathVariable("object") String objectName, 
		Set<String> fields = new HashSet<String>();
		fields.add("name");
		fields.add("createTime");
		return JSONObject.valueToString(this.accountService.findByEnabledById(fields, id));
	}
	
}
