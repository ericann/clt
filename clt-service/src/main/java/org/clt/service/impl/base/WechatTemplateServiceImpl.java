package org.clt.service.impl.base;

import org.clt.repository.dao.WechatTemplateDao;
import org.clt.repository.pojo.WechatTemplate;
import org.clt.service.base.WechatTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WechatTemplateServiceImpl extends GenericServiceImpl<WechatTemplate, String> implements WechatTemplateService {
	
	private WechatTemplateDao wechatTemplateDao;
	
	@Autowired
	public WechatTemplateServiceImpl(WechatTemplateDao wechatTemplateDao) {
		super(wechatTemplateDao);
		this.wechatTemplateDao = wechatTemplateDao;
	}

	@Override
	public WechatTemplate findByWechatAccountAndName(String wechatAccount, String name) {
		// TODO Auto-generated method stub
		return this.wechatTemplateDao.findByWechataccountAndName(wechatAccount, name);
	}
	
}
