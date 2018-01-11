package org.clt.service;

import java.util.Date;
import java.util.Map;

import org.clt.util.Token;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
	
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public String getAccessToken(Map<String, Object> accessSort) {
		String access_token = Token.generateAccessToken(3600 * 1000, new JSONObject(accessSort).toString());
		return access_token;
	}
	
	public Map<String, Object> getAccessTokenInfo(String access) {
		return Token.parse(access);
	}
	
	public Object getTokenInfo(String accessToken, String key) {
		return Token.parse(accessToken).get(key);
	}
	
	public Object getTokenInfo(Map<String, Object> accessToken, String key) {
		return accessToken.get(key);
	}
	
	public String refreshAccessToken(String token) {
		Map<String, Object> accessToken_old = this.getAccessTokenInfo(token);
		return Token.generateAccessToken(3600 * 1000, new JSONObject(accessToken_old.get("jti")).toString());
	}
	
	public Boolean isValid(String token) {
		return this.isValid(this.getAccessTokenInfo(token));
	}
	
	public Boolean isValid(Map<String, Object> accessToken) {
		Long expried = (Long) accessToken.get("exp");
		return expried - new Date().getTime() > 0 ? true : false;
	}
}
