package org.clt.data.generic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public interface DataListener<T, PK> {
	
	public @ResponseBody String findAll(@RequestHeader("CLT-ACCESS-TOKEN") String token);
	
	public @ResponseBody String findById(@RequestHeader("CLT-ACCESS-TOKEN") String token, @PathVariable("id") String id);
	
	public @ResponseBody String save(@RequestHeader("CLT-ACCESS-TOKEN") String token, @RequestBody T json);
}
