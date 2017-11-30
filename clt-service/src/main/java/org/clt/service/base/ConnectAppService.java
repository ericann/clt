package org.clt.service.base;

import java.util.List;

import org.clt.repository.pojo.ConnectApp;
import org.springframework.stereotype.Service;

@Service
public interface ConnectAppService extends GenericService<ConnectApp, String> {
	
	List<ConnectApp> findByContactIdAndName(String conId, String name);
}
