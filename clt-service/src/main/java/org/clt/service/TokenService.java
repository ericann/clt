package org.clt.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.clt.util.Token;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;

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
	
	public Map<String, Object> isValid(String token) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			Map<String, Object> accessToken = this.getAccessTokenInfo(token);
			long expried = (Integer) accessToken.get("exp");
			if(((expried * 1000 - new Date().getTime()) / 1000 > 0)) {
				result.put("code", 0);
				result.put("expire_in", (expried * 1000 - new Date().getTime()) / 1000);
				result.put("msg", "Success.");
				result.put("conId", accessToken.get("jti"));
			} else {
				result.put("code", 1032);
				result.put("msg", "Token is expired.");
			}
		} catch(SignatureException ex) {
			result.put("code", 1033);
			result.put("msg", "Unknown token.");
		} catch(MalformedJwtException me) {
			result.put("code", 1034);
			result.put("msg", "Invalid format token.");
		} catch(IllegalArgumentException ex) {
			result.put("code", 1035);
			result.put("msg", "Empty token.");
		} catch(Exception ex) {
			result.put("code", -1);
			result.put("msg", "Error token.");
		}
		
		return result;
	}
}
