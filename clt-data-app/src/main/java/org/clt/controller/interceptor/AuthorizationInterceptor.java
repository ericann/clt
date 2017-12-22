package org.clt.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		String access_token = request.getHeader("CLT-Access-Token");
		
		if(access_token == null || access_token.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setContentType("application/json");
			response.getWriter().append("{\"errCode\": 1001, \"errMsg\": \"Invalid Token.\"}");
		} else {
			request.setAttribute("conId", "61118512-daaa-431f-8595-f9bbb21a1179");
		}
		
		return access_token == null || access_token.isEmpty() ? false : true;
	}
	
}
