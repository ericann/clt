package org.clt.data.controller;

import org.clt.data.generic.DataListenerImpl;
import org.clt.repository.pojo.LiveAgent;
import org.clt.service.base.LiveAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/data-api/LiveAgent")
public class LiveAgentListener extends DataListenerImpl<LiveAgent, String> {
	
	@Autowired
    public LiveAgentListener(LiveAgentService liveAgentService) {
        super(liveAgentService);
    }
}
