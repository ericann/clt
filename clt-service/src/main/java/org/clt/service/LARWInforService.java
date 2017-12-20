package org.clt.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.clt.repository.dao.AccountDao;
import org.clt.repository.dao.ButtonDao;
import org.clt.repository.dao.ContactDao;
import org.clt.repository.dao.LiveAgentDao;
import org.clt.repository.dao.WechatAccountDao;
import org.clt.repository.pojo.Account;
import org.clt.repository.pojo.Button;
import org.clt.repository.pojo.Contact;
import org.clt.repository.pojo.LiveAgent;
import org.clt.repository.pojo.Sfdc;
import org.clt.repository.pojo.WechatAccount;

import static org.clt.util.DefaultMsg.NO_AGENTS_ALERT;
import static org.clt.util.DefaultMsg.ORG_C_COUNT;
import static org.clt.util.DefaultMsg.BUTTON_C_COUNT;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class LARWInforService {
	
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WechatAccountDao waDao;
	
	@Autowired
	private LiveAgentDao laDao;
	
	@Autowired
	private ButtonDao bDao;
	
	@Autowired
	private AccountDao accDao;
	
	@Autowired
	private ContactDao conDao;
	
	@Autowired
	private WechatTokenService wtService;
	
	public String addAccAndCon(String json) throws JSONException, UnsupportedEncodingException {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 0);
		result.put("msg", "success");
		
		try {
			JSONObject o = new JSONObject(URLDecoder.decode(json, "utf-8"));
			
			Account acc = new Account();
			acc.setId(UUID.randomUUID().toString());
			acc.setName(o.getString("companyname"));
			
			this.accDao.save(acc);
			
			Contact con = new Contact();
			con.setAccount(acc);
			con.setEmail(o.getString("email"));
			con.setMobile(o.getString("mobile"));
			con.setName(o.getString("yourname"));
			con.setId(UUID.randomUUID().toString());
			
			this.conDao.save(con);
			
			Map<String, String> data = new HashMap<String, String>();
			data.put("conId", con.getId());
			data.put("accId", acc.getId());
			result.put("data", data);
		} catch(Exception ex) {
			result.put("code", 1);
			result.put("msg", "failed");
		}
		return JSONObject.valueToString(result);
	}
	
	public String addBasicConfigAndButton(String json) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 0);
		result.put("msg", "success");
		
		try {
			JSONObject o = new JSONObject(URLDecoder.decode(json, "utf-8"));
			Account acc = new Account();
			acc.setId(o.getString("accId"));
			
			String wechatAccessToken = o.getBoolean("refreshnow") ? 
					this.wtService.getByAppIdAndSecret(o.getString("appid"), o.getString("appsecret")) : null;

			LiveAgent la = new LiveAgent();
			Sfdc sfdc = new Sfdc();
			sfdc.setOrgId(o.getString("organizationid"));
			sfdc.setOrgId(UUID.randomUUID().toString());
			
			la.setId(UUID.randomUUID().toString());
			la.setDeploymentId(o.getString("deploymentid"));
			la.setEndPoint(o.getString("endpoint"));
			la.setSfdc(sfdc);
			la.setAccount(acc);
			
			this.laDao.save(la);
			
			WechatAccount wa = new WechatAccount();
			wa.setWechatAccessToken(wechatAccessToken);
			wa.setId(UUID.randomUUID().toString());
			wa.setFirstTimeRefresh(o.getBoolean("refreshnow"));
			wa.setWechatAccount(o.getString("accountid"));
			wa.setWechatAppId(o.getString("appid"));
			wa.setWechatAppSecret(o.getString("appsecret"));
			wa.setWechatToken(o.getString("token"));
			wa.setAccount(acc);
			wa.setLiveagent(la);
			wa.setLimitCount(Integer.parseInt(ORG_C_COUNT));
			
			this.waDao.save(wa);
			
			JSONArray arr = o.getJSONArray("buttonid");
			
			//Support Single now
			Button b = new Button();
			b.setLiveagent(la);
			b.setButtonId(arr.getString(0));
			b.setId(UUID.randomUUID().toString());
			b.setIsDefault(true);
			b.setDisplayInfo(NO_AGENTS_ALERT);
			b.setLimitCount(Integer.parseInt(BUTTON_C_COUNT));
			
			this.bDao.save(b);
			
			//Support Muti
//			for(Integer i = 0; i < arr.length(); i++) {
//				Button b = new Button();
//				b.setBasicconfig(bc);
//				b.setButtonId(arr.getString(i));
//				b.setId(UUID.randomUUID().toString());
//				
//				this.bDao.save(b);
//			}
			
			result.put("errCode", 0);
			result.put("errMsg", "success");
		} catch(DataIntegrityViolationException ex) {
			result.put("errCode", 1);
			result.put("errMsg", "Confirm your input informaction currectly.");
		} catch(Exception ex) {
			System.out.println("-- ex: " + ex.getMessage());
			System.out.println("-- ex: " + ex.getCause());
		}
		
		return result.toString();
	}
	
	public String findInfor(String accId, String json) {
		JSONObject result = new JSONObject();
		result.put("errCode", "0");
		result.put("errMsg", "success.");
		
//		Account acc = null;
//		Contact con = null;
//		LiveAgent la = null;
//		WechatAccount wa = null;
//		Button b = null;
		
		return result.toString();
	}
	
	public String findInforByPwd(String json) {
		JSONObject result = new JSONObject();
		result.put("errCode", "0");
		result.put("errMsg", "success.");
//		
//		Account acc = null;
//		Contact con = null;
//		BasicConfig bc = null;
//		Button b = null;
//		
//		try {
//			JSONObject o = new JSONObject(json);
//			String mobile = o.getString("mobile");
//			String password = o.getString("password");
//			
//			con = this.conDao.findByMobileAndPassword(mobile, password);
//			
//			if(con != null) {
//				acc = this.accDao.findById(con.getAccount().getId());
//				bc = this.bcDao.findByAccountId(con.getAccount().getId());
//				
//				if(bc != null) {
//					b = this.bDao.findByIsDefaultAndBasicConfigId(true, bc.getId());
//				}
//			}
//			
//			result.put("data", initData(acc, con, bc, b));
//		} catch(Exception ex) {
//			logger.debug("LARWService findInforByPwd ex: " + ex.getMessage());
//		}
		
		return result.toString();
	}
	
	public JSONObject initData(Account acc, Contact con, WechatAccount wa, LiveAgent la, Button b) {
		JSONObject result = new JSONObject();
		
//		JSONObject accO = new JSONObject();
//		//Map<String, JSONObject> m_acc = new HashMap<String, JSONObject>();
//		accO.put("accountId", acc.getId());
//		accO.put("companyName", acc.getName());
//		//m_acc.put("account", accO);
//		result.put("account", accO);
//		
//		JSONObject conO = new JSONObject();
//		//Map<String, JSONObject> m_con = new HashMap<String, JSONObject>();
//		conO.put("contactId", con.getId());
//		conO.put("companyName", con.getName());
//		conO.put("email", con.getEmail());
//		conO.put("mobile", con.getMobile());
//		//m_con.put("contact", conO);
//		//arr.put(m_con);
//		result.put("contact", conO);
//		
//		JSONObject bcO = new JSONObject();
//		//Map<String, JSONObject> m_bc = new HashMap<String, JSONObject>();
//		bcO.put("bcId", bc.getId());
//		bcO.put("createTime", bc.getCreateTime());
//		bcO.put("orgId", bc.getLiveAgentOrgId());
//		bcO.put("deploymentId", bc.getLiveAgentDeploymentId());
//		bcO.put("endPoint", bc.getLiveAgentEndPoint());
//		bcO.put("wechatAccount", bc.getWechatAccount());
//		bcO.put("appId", bc.getWechatAppId());
//		bcO.put("appSecret", bc.getWechatAppSecret());
//		bcO.put("appSecret", bc.getWechatToken());
//		//m_bc.put("contact", bcO);
//		//arr.put(m_bc);
//		result.put("basicConfig", bcO);
//		
//		JSONObject bO = new JSONObject();
//		//Map<String, JSONObject> m_b = new HashMap<String, JSONObject>();
//		bO.put("bid", b.getId());
//		bO.put("buttonId", b.getButtonId());
//		bO.put("displayInfo", b.getDisplayInfo());
//		//m_b.put("contact", bO);
//		//arr.put(m_b);
//		result.put("button", bO);
//		
		return result;
	}
}
