package org.clt.oauth.controller;

import java.util.UUID;

import org.clt.service.AccessService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/security")
public class AccessPermissionsListener {
	
	@Autowired
	private AccessService accessService;
	
	@RequestMapping("/accesstoken/{ticket}")
	public @ResponseBody String login(@PathVariable("ticket") String ticket) {
		return JSONObject.valueToString(this.accessService.loginByWechat(ticket));
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
	
	@RequestMapping(value="/QR/{ticket}", method=RequestMethod.GET)
	public @ResponseBody String qrExpried(@PathVariable("ticket") String ticket) {
		return this.accessService.setQRExpried(ticket);
	}
	
	@RequestMapping(value="/profile", method=RequestMethod.GET)
	public @ResponseBody String getProfile(@RequestHeader("CLT-Access-Token") String access_token) {
		return (String)this.accessService.getAccessTokenInfo(access_token).get("jti");
	}
	
	@RequestMapping(value="/getUUID", method=RequestMethod.GET)
	public @ResponseBody String getUUID() {
		return UUID.randomUUID().toString();
	}
	
	@RequestMapping(value="/getUUIDs/{count}", method=RequestMethod.GET)
	public @ResponseBody String getUUIDs(@PathVariable("count") Integer count) {
		String[] uuids = new String[count];
		for(Integer i = 0; i < count; i++) {
			String uuid = UUID.randomUUID().toString();
			uuids[i] = uuid;
		}
		
		return JSONObject.valueToString(uuids);
	}
}
