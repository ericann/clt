package org.clt.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.clt.data.generic.DataListenerImpl;
import org.clt.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import io.jsonwebtoken.SignatureException;

@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private TokenService tokenService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		String access_token = request.getHeader("CLT-ACCESS-TOKEN");
		
		try {		
			if(access_token == null || access_token.isEmpty() || !tokenService.isValid(access_token)) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response.setContentType("application/json");
				response.getWriter().append("{\"errCode\": 1001, \"errMsg\": \"Invalid Token.\"}");
			} else {
	
			}
		} catch(SignatureException se) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setContentType("application/json");
			response.getWriter().append("{\"errCode\": 1001, \"errMsg\": \"Error Token.\"}");
			System.out.println("se: " + se.getMessage());
		}
		
		return access_token == null || access_token.isEmpty() || !tokenService.isValid(access_token) ? false : true;
	}
	
}
