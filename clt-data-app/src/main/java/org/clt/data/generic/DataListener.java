package org.clt.data.generic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public interface DataListener<T, PK> {
	
	public @ResponseBody String findAll(@PathVariable("conId") String conId);
	
	public @ResponseBody String findById(@PathVariable("id") String id);
	
}
