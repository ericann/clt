package org.clt.service;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.print.attribute.standard.DateTimeAtCompleted;

import org.clt.repository.dao.AccountDao;
import org.clt.repository.dao.ContactDao;
import org.clt.repository.pojo.Contact;
import org.clt.util.DefaultMsg;
import org.clt.util.Token;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private final Integer EXPIRES_IN = 3600;
	
	@Autowired
	private AccountDao accDao;
	
	@Autowired
	private ContactDao conDao;
	
	public String login(String openId, String url) {
		String accessToken = Token.generateAccessToken(EXPIRES_IN, openId);
		return accessToken;
	}
	
	public String getSessionId(String conId){
		Map<String, Object> result = DefaultMsg.initErrorResult("E_1");
		
		Contact con = this.conDao.findById(conId);
		if((con.getSessionUpdateTime().getTime() - (new Date()).getTime()) > Integer.valueOf(DefaultMsg.get("SESSION_EXPIRE"))) {
			
		}
		
		return null;
	}
	
	private Contact updateSessionId(String conId) {
		return this.conDao.updateBySessionIdAndId(UUID.randomUUID().toString(), conId);
	}
	
	private Map<String, Object> checkSessionValid(String sessionId) {
		Map<String, Object> result = DefaultMsg.initErrorResult("E_1");
		try {
			Contact con = this.conDao.findBySessionId(sessionId);
			
			if(con == null) {
				
			} else {
				if((con.getSessionUpdateTime().getTime() - (new Date()).getTime()) > Integer.valueOf(DefaultMsg.get("SESSION_EXPIRE"))) {
					
				}
			}
		} catch(Exception ex) {
			
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		
	}
	
}
