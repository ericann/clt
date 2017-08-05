package org.clt.repository.dao;

import org.clt.repository.pojo.Button;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ButtonDao extends GenericDao<Button, String> {
	
	@Query("SELECT b FROM Button b WHERE b.isDefault=:isDefault AND b.WechatAccount.id=:wechatAccountId")
	public Button findByIsDefaultAndwechatAccountId(@Param("isDefault") Boolean isDefault, @Param("wechatAccountId") String wechatAccountId);
	
	@Query("SELECT b FROM Button b WHERE b.buttonId=:buttonId")
	public Button findByButtonId(@Param("buttonId") String buttonId);
	
}
