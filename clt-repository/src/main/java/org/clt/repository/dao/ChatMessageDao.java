package org.clt.repository.dao;

import java.util.List;

import org.clt.repository.pojo.Button;
import org.clt.repository.pojo.ChatMessage;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface ChatMessageDao extends GenericDao<ChatMessage, String> {
	
	@Query("SELECT cm FROM ChatMessage cm WHERE cm.openId=:openId")
	public ChatMessage findByOpenId(@Param("openId") String openId);
	
	@Modifying
	@Transactional
	@Query("DELETE ChatMessage WHERE openId=:openId")
	public int deleteByOpenId(@Param("openId") String openId);
	
	@Query("SELECT count(Id) FROM ChatMessage cm WHERE cm.button.buttonId=:buttonId")
	public Integer findCountByButtonId(@Param("buttonId") String buttonId);
	
	//@Query("SELECT count(Id) FROM ChatMessage cm WHERE cm.buttonId=:buttonId")
	//public Integer findCountByOrgId(@Param("buttonId") String buttonId);
	
	@Override
	@Query("SELECT cm FROM ChatMessage cm WHERE cm.liveagent.account.id=(SELECT con.account.id FROM Contact con WHERE con.id=:conId)")
	List<ChatMessage> findAllByContactId(@Param("conId") String conId);
}
