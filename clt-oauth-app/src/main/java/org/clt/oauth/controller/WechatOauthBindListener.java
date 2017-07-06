package org.clt.oauth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bind")
public class WechatOauthBindListener {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping("/wuser")
	public String bindWUser() {
		return null;
	}
	
	@RequestMapping("/redirectWO")
	public String getQR() {
		String result = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx085888a3e559f8ac&redirect_uri=https%3A%2F%2F47.93.254.177%2Fclt%2Fbind%2Fwuser&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
		
		return "forward:" + result;
	}
}
