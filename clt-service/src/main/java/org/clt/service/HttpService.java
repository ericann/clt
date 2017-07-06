package org.clt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HttpService {
	
	@Autowired
	private RestTemplate http;
	
	public void post() {
		//http.execute(url, method, requestCallback, responseExtractor);
	}
}
