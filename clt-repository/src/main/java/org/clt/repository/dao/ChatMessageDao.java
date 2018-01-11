package org.clt.repository.dao;

import java.util.List;

import org.clt.repository.pojo.ChatMessage;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface ChatMessageDao extends GenericDao<ChatMessage, String> {
	
	@Query("SELECT cm FROM ChatMessage cm WHERE cm.openId=:openId AND cm.valid=:valid")
	public ChatMessage findByOpenIdAndValid(@Param("openId") String openId, @Param("valid") Boolean isValid);
	
	@Modifying
	@Transactional
	@Query("DELETE ChatMessage WHERE openId=:openId")
	public Integer deleteByOpenId(@Param("openId") String openId);
	
	@Modifying
	@Query("UPDATE ChatMessage SET valid=:valid WHERE openId=:openId")
	public Integer updateByOpenId(@Param("valid") Boolean isValid, @Param("openId") String openId);
	
	@Query("SELECT count(Id) FROM ChatMessage cm WHERE cm.button.buttonId=:buttonId")
	public Integer findCountByButtonId(@Param("buttonId") String buttonId);
	
	//@Query("SELECT count(Id) FROM ChatMessage cm WHERE cm.buttonId=:buttonId")
	//public Integer findCountByOrgId(@Param("buttonId") String buttonId);
	
	@Override
	@Query("SELECT cm FROM ChatMessage cm WHERE cm.liveagent.account.id=(SELECT con.account.id FROM Contact con WHERE con.id=:conId)")
	List<ChatMessage> findAllByContactId(@Param("conId") String conId);
}
