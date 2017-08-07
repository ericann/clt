package org.clt.oauth.controller;

import javax.websocket.server.PathParam;

import org.clt.service.AccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/security")
public class AccessPermissionsListener {
	
	@Autowired
	private AccessService accessService;
	
	@RequestMapping("/accesstoken/{ticket}")
	public String login(@PathParam("ticket") String ticket) {
		//accessService.
		return null;
	}
	
	@RequestMapping("/getQR")
	public String getQRImg() {
		return this.accessService.getQRInfoShort();
	}
}
