package org.clt.data.controller;

import org.clt.data.generic.DataListenerImpl;
import org.clt.repository.pojo.WechatUser;
import org.clt.service.base.WechatUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/data-api/WechatUser")
public class WechatUserListener extends DataListenerImpl<WechatUser, String> {
	
	@SuppressWarnings("unused")
	private WechatUserService wechatUserService;
	
	@Autowired
    public WechatUserListener(WechatUserService wechatUserService) {
        super(wechatUserService);
    }
	
}
