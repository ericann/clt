package org.clt.service.base;

import org.clt.repository.pojo.ChatMessage;
import org.springframework.stereotype.Service;

@Service
public interface ChatMessageService extends GenericService<ChatMessage, String> {

}
