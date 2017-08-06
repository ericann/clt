package org.clt.service;

import java.util.List;
import java.util.UUID;

import org.clt.repository.dao.WechatAccountDao;
import org.clt.repository.pojo.WechatAccount;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BasicConfigService {
	
	@Autowired
	private WechatAccountDao waDao;
	
	public WechatAccount save(WechatAccount wa) {
		wa.setId(String.valueOf(UUID.randomUUID()));
		return this.waDao.save(wa);
	}
	
	public Boolean updateWechatAccessToken(String wechatAccount, String wechatAccessToken) {
		return (this.waDao.updateWechatAccessTokenByWechatAccount(wechatAccount, wechatAccessToken) == 1 ? true : false);
	}
	
	public List<WechatAccount> findAll() {
		return this.waDao.findAll();
	}
	
	public String getAll() {
		JSONObject result = new JSONObject();
		JSONArray arr = new JSONArray();
		List<WechatAccount> waL = this.waDao.findAllByRefreshByUs(true);
		
		for(WechatAccount wa : waL) {
			JSONObject o = new JSONObject();
			o.put("appId", wa.getWechatAppId());
			o.put("appSecret", wa.getWechatAppSecret());
			o.put("accountId", wa.getWechatAccount());
			arr.put(o);
		}
		
		result.put("url", "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={0}&secret={1}");
		result.put("data", arr);
		
		return JSONObject.valueToString(result);
	}
}
