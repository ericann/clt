package org.clt.service.base;

import java.util.List;
import java.util.Map;

import org.clt.repository.pojo.UserApp;
import org.springframework.stereotype.Service;

@Service
public interface UserAppService extends GenericService<UserApp, String> {
	
	List<Map<String, Object>> findByContactIdAndName(String conId, String name);
}
