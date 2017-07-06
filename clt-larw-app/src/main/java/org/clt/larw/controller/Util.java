package org.clt.larw.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import org.clt.util.DefaultMsg;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/util")
public class Util {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RestTemplate http;
	
	
	
	private ResponseEntity<String> getUserConfirm(String url, HttpMethod type) {
		return http.exchange(url, type, null, String.class);
	}
}
