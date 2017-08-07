package org.clt.service.impl.base;

import org.clt.repository.dao.ChatMessageDao;
import org.clt.repository.pojo.ChatMessage;
import org.clt.service.base.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatMessageServiceImpl extends GenericService<ChatMessage, String> implements ChatMessageService {
	
	@Autowired
	public ChatMessageServiceImpl(ChatMessageDao chatMessageDao) {
		super(chatMessageDao);
		// TODO Auto-generated constructor stub
	}
	
}
