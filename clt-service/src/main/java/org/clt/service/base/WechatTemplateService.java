package org.clt.service.base;

import org.clt.repository.pojo.WechatTemplate;
import org.springframework.stereotype.Service;

@Service
public interface WechatTemplateService extends GenericService<WechatTemplate, String> {
	
	WechatTemplate findByWechatAccountAndName(String wechatAccount, String name);
}
