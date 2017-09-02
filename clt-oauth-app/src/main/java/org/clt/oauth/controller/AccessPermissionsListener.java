package org.clt.oauth.controller;

import javax.websocket.server.PathParam;

import org.clt.service.AccessService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/security")
public class AccessPermissionsListener {
	
	@Autowired
	private AccessService accessService;
	
	@RequestMapping("/accesstoken/{ticket}")
	public @ResponseBody String login(@PathParam("ticket") String ticket) {
		//accessService.
		return null;
	}
	
	@RequestMapping(value="/accesstoken/{conId}/{ticket}", method=RequestMethod.POST)
	public @ResponseBody String queryBindInfo(@PathVariable("conId") String conId, 
			@PathVariable("ticket") String ticket) {
		return JSONObject.valueToString(this.accessService.confirmBind(conId, ticket));
	}
	
	@RequestMapping(value="/getQR/{conId}", method=RequestMethod.GET)
	public @ResponseBody String getQRImgByConId(@PathVariable("conId") String conId) {
		return this.accessService.getQRInfoShort(conId);
	}
	
	@RequestMapping(value="/getQR", method=RequestMethod.GET)
	public @ResponseBody String getQRImg() {
		return this.accessService.getQRInfoShort(null);
	}
}