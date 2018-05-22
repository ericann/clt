package org.clt.service;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.clt.repository.pojo.Account;
import org.clt.repository.pojo.Button;
import org.clt.repository.pojo.Contact;
import org.clt.repository.pojo.LiveAgent;
import org.clt.repository.pojo.Sfdc;
import org.clt.repository.pojo.WechatAccount;
import org.clt.service.base.AccountService;
import org.clt.service.base.SfdcService;

import static org.clt.util.DefaultMsg.NO_AGENTS_ALERT;
import static org.clt.util.DefaultMsg.ORG_C_COUNT;
import static org.clt.util.DefaultMsg.BUTTON_C_COUNT;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class LARWInforService {
	
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SfdcService sfdcService;
	
	@Autowired
	private AccountService accService;
	
	public String addAccountCascade(String json) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 0);
		result.put("msg", "success");
		
		try {
			JSONObject o = new JSONObject(URLDecoder.decode(json, "utf-8"));
			Account acc = new Account();
			acc.setName(o.getString("name"));
			
			String conString = o.getJSONObject("contacts").toString();
			Contact con = new ObjectMapper().readValue(conString, Contact.class);
			acc.addContact(con);
			
			this.accService.save(acc);
			result.put("id", acc.getId());
		} catch(Exception ex) {
			result.put("code", 1);
			result.put("msg", "failed");
		}
		return JSONObject.valueToString(result);
	}
	
	public String addSfdcCascade(String json) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 0);
		result.put("msg", "success");
		
		try {
			JSONObject o = new JSONObject(URLDecoder.decode(json, "utf-8"));
			final ObjectMapper MAPPER = new ObjectMapper();
			
			Sfdc sfdc = new Sfdc();
			sfdc.setOrgId(o.getString("orgId"));
			sfdc.setDomain(o.getString("domain"));
			
			JSONObject liveagents = o.getJSONObject("liveagents");
			Account acc = MAPPER.readValue(liveagents.getJSONObject("account").toString(), Account.class);
			
			LiveAgent la = new LiveAgent();
			la.setDeploymentId(liveagents.getString("deploymentId"));
			la.setEndPoint(liveagents.getString("endPoint"));
			la.setAccount(acc);
			
			WechatAccount wa = MAPPER.readValue(liveagents.getJSONObject("wechataccounts").toString(), WechatAccount.class);
			wa.setAccount(acc);
			wa.setLimitCount(Integer.parseInt(ORG_C_COUNT));
			
			//Support Single now
			Button b = MAPPER.readValue(liveagents.getJSONObject("buttons").toString(), Button.class);
			b.setLimitCount(Integer.parseInt(BUTTON_C_COUNT));
			b.setDisplayInfo(NO_AGENTS_ALERT);
			
			la.addButton(b);
			la.addWechataccount(wa);
			sfdc.addLiveagent(la);
			
			this.sfdcService.save(sfdc);
			result.put("id", sfdc.getId());
		} catch(DataIntegrityViolationException ex) {
			result.put("code", 1);
			result.put("msg", "Confirm your input informaction currectly.");
		} catch(Exception ex) {
			result.put("code", -1);
			result.put("msg", "Unknown Error.");
		}
		
		return JSONObject.valueToString(result);
	}
	
}
