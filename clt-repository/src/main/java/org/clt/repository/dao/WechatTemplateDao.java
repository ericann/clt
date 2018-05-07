package org.clt.repository.dao;

import java.util.List;

import org.clt.repository.pojo.WechatTemplate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WechatTemplateDao extends GenericDao<WechatTemplate, String> {
	
	@Query("SELECT wt FROM WechatTemplate wt WHERE wt.wechataccount.wechatAccount=:wechatAccount AND wt.name=:name")
	WechatTemplate findByWechataccountAndName(@Param("wechatAccount") String wechatAccount, @Param("name") String name);

	@Query("SELECT wt FROM WechatTemplate wt WHERE wt.id=:conId")
	List<WechatTemplate> findAllByContactId(@Param("conId") String conId);
}
