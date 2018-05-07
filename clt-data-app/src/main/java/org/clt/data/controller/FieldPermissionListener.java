package org.clt.data.controller;

import org.clt.data.generic.DataListenerImpl;
import org.clt.repository.pojo.FieldPermission;
import org.clt.service.base.FieldPermissionService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/metadata-api/FieldPermission")
public class FieldPermissionListener extends DataListenerImpl<FieldPermission, String> {
	
	private FieldPermissionService fieldPermissionService;
	
	@Autowired
    public FieldPermissionListener(FieldPermissionService fieldPermissionService) {
        super(fieldPermissionService);
        this.fieldPermissionService = fieldPermissionService;
    }
	
	@RequestMapping(value = "/{opId}/all", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String findById(@RequestHeader("CLT-ACCESS-TOKEN") String token, 
			@PathVariable("opId") String opId) {
		return JSONObject.valueToString(this.fieldPermissionService.findAllByOpId(opId));
	}
}
