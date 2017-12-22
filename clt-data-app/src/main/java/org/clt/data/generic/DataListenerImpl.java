package org.clt.data.generic;

import java.io.Serializable;

import org.clt.service.base.GenericService;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public abstract class DataListenerImpl<T extends Serializable, PK extends Serializable> implements DataListener<T, PK> {
	
	protected GenericService<T, PK> genericService;
	
	protected String userId;
	
	public DataListenerImpl(GenericService<T, PK> genericService) {
		this.genericService = genericService;
	}
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String findAll(@RequestParam("conId") String userId) {
		return JSONObject.valueToString(this.genericService.findAllByContactId(userId));
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String findById(@PathVariable("id") String id) {
		//@PathVariable("object") String objectName, 
		return JSONObject.valueToString(this.genericService.findById(id));
	}
	
}
