package org.clt.data.controller;

import org.clt.data.generic.DataListenerImpl;
import org.clt.repository.pojo.Sfdc;
import org.clt.service.base.SfdcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/data-api/Sfdc")
public class SfdcListener extends DataListenerImpl<Sfdc, String> {
	
	@SuppressWarnings("unused")
	private SfdcService sfdcService;
	
	@Autowired
    public SfdcListener(SfdcService sfdcService) {
        super(sfdcService);
        this.sfdcService = sfdcService;
    }
	
}
