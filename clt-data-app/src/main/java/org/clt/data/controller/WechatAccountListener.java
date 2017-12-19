package org.clt.data.controller;

import org.clt.repository.pojo.WechatAccount;
import org.clt.service.base.WechatAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/data-api/{conId}/WechatAccount")
public class WechatAccountListener extends DataListenerImpl<WechatAccount, String> {
	
	@Autowired
    public WechatAccountListener(WechatAccountService wechatAccountService) {
        super(wechatAccountService);
    }
}
