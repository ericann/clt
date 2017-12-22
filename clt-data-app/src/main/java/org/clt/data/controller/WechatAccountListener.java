package org.clt.data.controller;

import org.clt.data.generic.DataListenerImpl;
import org.clt.repository.pojo.WechatAccount;
import org.clt.service.base.WechatAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/data-api/WechatAccount")
public class WechatAccountListener extends DataListenerImpl<WechatAccount, String> {
	
	@Autowired
    public WechatAccountListener(WechatAccountService wechatAccountService) {
        super(wechatAccountService);
    }
}
