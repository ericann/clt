package org.clt.oauth.controller;

import org.clt.service.AccessService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AccessService accessService;
	
	@RequestMapping("/accesstoken/{ticket}")
	public @ResponseBody String login(@PathVariable("ticket") String ticket) {
		return JSONObject.valueToString(this.accessService.loginByWechat(ticket));
	}
	
	@RequestMapping("/accesstoken/free")
	public @ResponseBody String free() {
		return this.accessService.getAccessToken(null);
	}
	
	@RequestMapping("/accesstoken/verify/")
	public @ResponseBody String checkAccessToken(@RequestHeader("CLT-ACCESS-TOKEN") String token) {
		return JSONObject.valueToString(this.accessService.check(token));
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
	public @ResponseBody String getProfile(@RequestHeader("CLT-ACCESS-TOKEN") String access_token) {
		return (String)this.accessService.getProfile(access_token);
	}
	
}
