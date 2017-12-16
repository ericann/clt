package org.clt.data.controller;

import org.clt.repository.pojo.Account;
import org.clt.service.base.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/data-api/{conId}/Account")
public class AccountListener extends DataListenerImpl<Account, String> {
	
	@Autowired
    public AccountListener(AccountService accountService) {
        super(accountService);
    }
}
