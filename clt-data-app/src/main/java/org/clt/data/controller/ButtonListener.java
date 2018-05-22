package org.clt.data.controller;

import org.clt.data.generic.DataListenerImpl;
import org.clt.repository.pojo.Button;
import org.clt.service.base.ButtonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/data-api/Button")
public class ButtonListener extends DataListenerImpl<Button, String> {
	
	@SuppressWarnings("unused")
	private ButtonService buttonService;
	
	@Autowired
    public ButtonListener(ButtonService buttonService) {
        super(buttonService);
        this.buttonService = buttonService;
    }
}
