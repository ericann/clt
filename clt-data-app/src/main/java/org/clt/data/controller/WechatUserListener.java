package org.clt.data.controller;

import org.clt.repository.pojo.WechatUser;
import org.clt.service.base.WechatUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/data-api/{conId}/WechatUser")
public class WechatUserListener extends DataListenerImpl<WechatUser, String> {
	
	@Autowired
    public WechatUserListener(WechatUserService wechatUserService) {
        super(wechatUserService);
    }
}
