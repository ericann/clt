package org.clt.data.generic;

import java.io.Serializable;

import org.clt.service.TokenService;
import org.clt.service.base.GenericService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public abstract class DataListenerImpl<T extends Serializable, PK extends Serializable> implements DataListener<T, PK> {
	
	@Autowired
	private TokenService tokenService;
	
	protected GenericService<T, PK> genericService;
	
	protected String userId;
	
	protected String token;
	
	public DataListenerImpl(GenericService<T, PK> genericService) {
		this.genericService = genericService;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String findAll(@RequestHeader("CLT-ACCESS-TOKEN") String token) {
		this.token = token;
		return JSONObject.valueToString(this.genericService.findAllByContactId(userId));
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String findById(@RequestHeader("CLT-ACCESS-TOKEN") String token, 
			@PathVariable("id") String id) {
		return JSONObject.valueToString(this.genericService.findById(id));
	}
	
	public String getUserId() {
		return userId != null ? userId : (String)tokenService.getTokenInfo(token, "iat");
	}
	
}
