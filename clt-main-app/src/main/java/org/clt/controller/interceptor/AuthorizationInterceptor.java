package org.clt.controller.interceptor;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.clt.service.TokenService;
import org.clt.service.base.UserAppService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserAppService userAppService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		String access_token = request.getHeader("CLT-ACCESS-TOKEN");
		Map<String, Object> result = this.tokenService.isValid(access_token);
		String conId = (String) result.get("conId");
		Integer isValid = (Integer) result.get("code");
		String objName = request.getRequestURI().split("/")[3];
		
		//跳过FieldPermission的检查
		if(!objName.equals("FieldPermission") && isValid == 0 && (conId == null || conId.isEmpty())) {
			List<Map<String, Object>> objs = userAppService.findObjectsByName("FreeLogin");
			
			if(!objs.isEmpty()) {
				Boolean flag = false;
				for(Map<String, Object> obj : objs) {
					if(((String) obj.get("op_name")).equals(objName)) {
						flag = true;
						break;
					}
				}
				
				if(!flag) {
					isValid = -1;
					result.put("code", 4);
					result.put("msg", "You have no permissions.");
					logger.debug("-- result: " + result);
				}
			}
		}
			
		if(isValid != 0) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setContentType("application/json");
			response.getWriter().append(JSONObject.valueToString(result));
		}
		
		return isValid != 0 ? false : true;
	}
	
}
