package org.clt.service;

import java.util.List;
import java.util.UUID;

import org.clt.repository.dao.BasicConfigDao;
import org.clt.repository.pojo.BasicConfig;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BasicConfigService {
	
	@Autowired
	private BasicConfigDao basicConfigDao;
	
	public BasicConfig saveWithoutButtons(BasicConfig bc) {
		return null;
	}
	
	public BasicConfig save(BasicConfig bc) {
		bc.setId(String.valueOf(UUID.randomUUID()));
		return basicConfigDao.save(bc);
	}
	
	public Boolean updateWechatAccessToken(String wechatAccount, String wechatAccessToken) {
		return (this.basicConfigDao.updateWechatAccessTokenByWechatAccount(wechatAccount, wechatAccessToken) == 1 ? true : false);
	}
	
	public List<BasicConfig> findAll() {
		return this.basicConfigDao.findAll();
	}
	
	public String getAll() {
		JSONObject result = new JSONObject();
		JSONArray arr = new JSONArray();
		List<BasicConfig> bcL = this.basicConfigDao.findAllByRefreshByOthers(true);
		
		for(BasicConfig bc : bcL) {
			JSONObject o = new JSONObject();
			o.put("appId", bc.getWechatAppId());
			o.put("appSecret", bc.getWechatAppSecret());
			o.put("accountId", bc.getWechatAccount());
			arr.put(o);
		}
		
		result.put("url", "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={0}&secret={1}");
		result.put("data", arr);
		
		return JSONObject.valueToString(result);
	}
}
