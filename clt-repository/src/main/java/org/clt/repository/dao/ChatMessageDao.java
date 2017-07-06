package org.clt.repository.dao;

import org.clt.repository.pojo.ChatMessage;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface ChatMessageDao extends Repository<ChatMessage, String> {
	
	public ChatMessage save(ChatMessage cm);
	
	@Query("SELECT cm FROM ChatMessage cm WHERE cm.openId=:openId")
	public ChatMessage findByOpenId(@Param("openId") String openId);
	
	@Modifying
	@Transactional
	@Query("DELETE ChatMessage WHERE openId=:openId")
	public int deleteByOpenId(@Param("openId") String openId);

	@Query("SELECT count(Id) FROM ChatMessage cm WHERE cm.buttonId=:buttonId")
	public Integer findCountByButtonId(@Param("buttonId") String buttonId);
	
	//@Query("SELECT count(Id) FROM ChatMessage cm WHERE cm.buttonId=:buttonId")
	//public Integer findCountByOrgId(@Param("buttonId") String buttonId);
}
