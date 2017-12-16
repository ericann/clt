package org.clt.data.controller;

import java.io.Serializable;

import org.clt.service.base.GenericService;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public abstract class DataListenerImpl<T extends Serializable, PK extends Serializable> implements DataListener<T, PK> {
	
	protected GenericService<T, PK> genericService;
	
	public DataListenerImpl(GenericService<T, PK> genericService) {
		this.genericService = genericService;
	}
	
	@RequestMapping("/")
	public @ResponseBody String findAll(@PathVariable("conId") String conId) {
		return JSONObject.valueToString(this.genericService.findAllByContactId(conId));
	}
	
	@RequestMapping("/{id}")
	public @ResponseBody String findById(@PathVariable("id") String id) {
		//@PathVariable("object") String objectName, 
		return JSONObject.valueToString(this.genericService.findById(id));
	}
	
}
